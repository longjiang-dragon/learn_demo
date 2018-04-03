package com.hujiang.mytest.fragment.manager;

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
    private Queue<TaskInfo> taskQueue = new LinkedList<>();

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(0, CPU_COUNT, 10L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "lib-initiation");
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());
    }


    public void init(TaskInfo rootTaskInfo) {
        TaskInfo currentTask;
        taskQueue.offer(rootTaskInfo);
        while (!taskQueue.isEmpty()) {
            currentTask = taskQueue.poll();
            currentTask.startExecute(THREAD_POOL_EXECUTOR);
            if (currentTask.isCompleted()) {
                Log.i("queueSize","   "+currentTask.isCompleted());
                //添加子任务到队列
                addChildTaskToQueue(currentTask, taskQueue);
            }else {
                //任务没有执行完成重新加入队列
                taskQueue.offer(currentTask);
            }
            Log.i("queueSize","   "+taskQueue.size());
        }
    }



    private void addChildTaskToQueue(TaskInfo currentTask, Queue<TaskInfo> taskQueue) {
        List<TaskInfo> childTaskList = currentTask.getChildTaskList();
        if (null == childTaskList || childTaskList.isEmpty()) return;
        for (TaskInfo taskInfo : childTaskList) {
            taskQueue.offer(taskInfo);
        }

    }


}
