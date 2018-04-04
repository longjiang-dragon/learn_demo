package com.hujiang.mytest.fragment.manager;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public class TaskManager {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService THREAD_POOL_EXECUTOR;
    public static final MainThreadExecutor MAIN_THREAD_EXECUTOR;
    private Queue<TaskInfo> taskQueue = new LinkedList<>();

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(0, CPU_COUNT, 10L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "lib-initiation");
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());
        MAIN_THREAD_EXECUTOR = new MainThreadExecutor();
    }


    public void init(TaskInfo rootTaskInfo) {
        TaskInfo currentTask;
        taskQueue.offer(rootTaskInfo);
        while (!taskQueue.isEmpty()) {
            currentTask = taskQueue.poll();
            startExecute(currentTask);
            Log.i("queueSize", "   " + currentTask.isCompleted());
            //将子任务添加到队列
            if (isNeedAddToQueue(currentTask)) {
                addChildTaskToQueue(currentTask, taskQueue);
            }
//            Log.i("queueSize", "init   " + taskQueue.size()  +"  currentTask=="+currentTask.getLibInitiation().getClass().getSimpleName());
        }
    }

    private void startExecute(TaskInfo currentTask) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (currentTask.isRunMainThread()) {
                currentTask.startExecute();
            } else {
                currentTask.setCompleted(true);
                THREAD_POOL_EXECUTOR.execute(currentTask);
            }
        } else {
            if (currentTask.isRunMainThread()) {
                currentTask.setCompleted(true);
                MAIN_THREAD_EXECUTOR.execute(currentTask);
            } else {
                currentTask.startExecute();
            }
        }
    }


    //非UI线调用
    public void initAsync(TaskInfo rootTaskInfo) {
        TaskInfo currentTask;
        taskQueue.offer(rootTaskInfo);
        while (!taskQueue.isEmpty()) {
            currentTask = taskQueue.poll();
            startExecute(currentTask);
            Log.i("queueSize", "   " + currentTask.isCompleted());
            //将子任务添加到队列
            if (!isNeedAddToQueue(currentTask)) {
                addChildTaskToQueue(currentTask, taskQueue);
            }
//            Log.i("queueSize", "initAsync   " + taskQueue.size()  +"  currentTask=="+currentTask.getLibInitiation().getClass().getSimpleName());
        }
    }

    private boolean isNeedAddToQueue(TaskInfo parentTaskInfo) {
        return parentTaskInfo.isRunMainThread();
    }


    private void addChildTaskToQueue(TaskInfo currentTask, Queue<TaskInfo> taskQueue) {
        List<TaskInfo> childTaskList = currentTask.getChildTaskList();
        if (null == childTaskList || childTaskList.isEmpty()) return;
        for (TaskInfo taskInfo : childTaskList) {
            taskQueue.offer(taskInfo);
        }

    }


}
