package com.hujiang.mytest.fragment.manager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public class TaskManager {
    Queue<TaskInfo> taskQueue = new LinkedList<>();

    public void init(TaskInfo rootTaskInfo) {
        TaskInfo currentTask = null;
        taskQueue.offer(rootTaskInfo);
        while (!taskQueue.isEmpty()) {
            currentTask = taskQueue.poll();
            currentTask.startExecute();
            addChildTaskToQueue(currentTask, taskQueue);
        }
    }

    private void addChildTaskToQueue(TaskInfo currentTask, Queue<TaskInfo> taskQueue) {
        CopyOnWriteArrayList<TaskInfo> childTaskList = currentTask.getChildTaskList();
        if (null == childTaskList || childTaskList.isEmpty()) return;
        for (TaskInfo taskInfo : childTaskList) {
            taskQueue.offer(taskInfo);
        }

    }


}
