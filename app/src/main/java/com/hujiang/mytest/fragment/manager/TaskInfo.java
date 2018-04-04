package com.hujiang.mytest.fragment.manager;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public class TaskInfo implements Runnable {
    protected LibInitiation mLibInitiation;
    protected Application mApplication;

    private List<TaskInfo> mParentTaskList;
    private List<TaskInfo> mChildTaskList;


    public TaskInfo(LibInitiation libInitiation, Application application) {
        this.mLibInitiation = libInitiation;
        this.mApplication = application;
    }

    public void addToChildTaskList(TaskInfo childTaskInfo) {
        if (null == childTaskInfo) return;
        if (null == mChildTaskList) {
            mChildTaskList = new CopyOnWriteArrayList<>();
        }
        childTaskInfo.getParentTaskList().add(this);
        mChildTaskList.add(childTaskInfo);
    }

    public void addToParentTaskList(TaskInfo parentTaskInfo) {
        if (null == parentTaskInfo) return;
        if (null == mParentTaskList) {
            mParentTaskList = new CopyOnWriteArrayList<>();
        }
        childAndParentLink(parentTaskInfo, getFirstParentTask());
        childAndParentLink(this, parentTaskInfo);
    }


    //将两个node  建立关联
    private void childAndParentLink(TaskInfo childTask, TaskInfo parentTask) {
        if (null == parentTask) return;
        parentTask.getChildTaskList().add(childTask);
        childTask.getParentTaskList().add(parentTask);
    }

    private TaskInfo getFirstParentTask() {
        if (null == mParentTaskList || mParentTaskList.isEmpty()) return null;
        return mParentTaskList.get(0);
    }

    private List<TaskInfo> getParentTaskList() {
        if (null == mParentTaskList) {
            mParentTaskList = new ArrayList<>();
        }
        return mParentTaskList;
    }

    public List<TaskInfo> getChildTaskList() {
        if (null == mChildTaskList) {
            mChildTaskList = new ArrayList<>();
        }
        return mChildTaskList;
    }


    //开始执行当前任务
    public void startExecute() {
        this.mLibInitiation.libInitiationStart(mApplication);
//        printLog();
    }

    private void printLog() {
        StringBuilder stringBuilder = new StringBuilder("info====");
        if (null != mParentTaskList) {
            stringBuilder.append("parentSize=");
            stringBuilder.append(mParentTaskList.size());
        }
        if (null != mChildTaskList) {
            stringBuilder.append("childSize=");
            stringBuilder.append(mChildTaskList.size());
            stringBuilder.append("   ");
            for (TaskInfo taskInfo : mChildTaskList) {
                stringBuilder.append(taskInfo.getLibInitiation().getClass().getSimpleName() + "     ");
            }
        }
        Log.e("LibInitiation", stringBuilder.toString());
    }

    protected void createNewTaskManager() {
        new TaskManager().initAsync(TaskInfo.this);
    }

    public LibInitiation getLibInitiation() {
        return mLibInitiation;
    }


    public boolean isRunMainThread() {
        return mLibInitiation.isRunMainThread();
    }


    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (!(o instanceof String)) return false;
        return mLibInitiation.getClass().getSimpleName().equals(o);
    }

    @Override
    public void run() {
        createNewTaskManager();
    }

}
