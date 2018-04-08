package com.hujiang.mytest.fragment.manager;

import android.os.Looper;

import com.hujiang.mytest.fragment.manager.Executor.AsyncThreadExecutor;
import com.hujiang.mytest.fragment.manager.Executor.MainThreadExecutor;
import com.hujiang.mytest.fragment.manager.task.TaskInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

/**
 * @author jianglong
 * @desc 如当前任务与所在线程与任务申明的线程不相同时（当前任务的child也不会加入到当前队列），在目标线程中新创建一个taskManager,再执行当前任务
 * @date 2018/3/30
 */
public class TaskManager {
    public static final ExecutorService ASYNC_THREAD_EXECUTOR;
    public static final MainThreadExecutor MAIN_THREAD_EXECUTOR;
    private Queue<TaskInfo> taskQueue = new LinkedList<>();

    static {
        ASYNC_THREAD_EXECUTOR = new AsyncThreadExecutor();
        MAIN_THREAD_EXECUTOR = new MainThreadExecutor();
    }

    public void init(TaskInfo rootTaskInfo) {
        if (null == rootTaskInfo) return;
        TaskInfo currentTask;
        taskQueue.offer(rootTaskInfo);
        while (!taskQueue.isEmpty()) {
            currentTask = taskQueue.poll();
            startExecute(currentTask);
            //将子节点添加到队列
            if (isNeedAddChildToQueue(currentTask)) {
                addChildTaskToQueue(currentTask, taskQueue);
            }
        }
    }


    private void startExecute(TaskInfo currentTask) {
        if (isOnMainThread()) {
            if (currentTask.isRunMainThread()) {
                currentTask.startExecute();
            } else {
                //需要先切换线程，再执行初始化
                ASYNC_THREAD_EXECUTOR.execute(currentTask);
            }
        } else {
            if (currentTask.isRunMainThread()) {
                //需要先切换线程，再执行初始化
                MAIN_THREAD_EXECUTOR.execute(currentTask);
            } else {
                currentTask.startExecute();
            }
        }
    }

    //只有当 当前线程与当前任务所申明线程相同时，才会把子任务添加到队列中
    private boolean isNeedAddChildToQueue(TaskInfo parentTaskInfo) {
        if (isOnMainThread()) {
            return parentTaskInfo.isRunMainThread();
        } else {
            return !parentTaskInfo.isRunMainThread();
        }
    }

    private boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }


    private void addChildTaskToQueue(TaskInfo currentTask, Queue<TaskInfo> taskQueue) {
        List<TaskInfo> childTaskList = currentTask.getChildTaskList();
        if (null == childTaskList || childTaskList.isEmpty()) return;
        for (TaskInfo taskInfo : childTaskList) {
            taskQueue.offer(taskInfo);
        }

    }


}
