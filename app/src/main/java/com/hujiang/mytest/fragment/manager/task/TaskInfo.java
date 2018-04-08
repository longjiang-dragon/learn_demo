package com.hujiang.mytest.fragment.manager.task;

import android.app.Application;
import android.util.Log;

import com.hujiang.mytest.fragment.manager.LibInitiation;
import com.hujiang.mytest.fragment.manager.TaskManager;

import java.util.ArrayList;
import java.util.List;


/**
 * @author jianglong
 * @desc 需要在主线中执行的任务
 * @date 2018/3/30
 */
public class TaskInfo implements Runnable {
    private volatile boolean isCompleted;//当前task是否执行完成
    private volatile boolean isRunning;
    private LibInitiation mLibInitiation;
    private Application mApplication;

    private List<TaskInfo> mParentTaskList;
    private List<TaskInfo> mChildTaskList;


    public TaskInfo(LibInitiation libInitiation, Application application) {
        this.mLibInitiation = libInitiation;
        this.mApplication = application;
    }

    public List<TaskInfo> getParentTaskList() {
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
        if (!isExecutable() || isCompleted || isRunning) return;//不能执行
        this.isRunning = true;
        this.mLibInitiation.libInitiationStart(mApplication);
        this.isCompleted=true;
        printLog();
    }


    //判断当前task是否可执行。（一个task可有多个parent，如果parent未执行完成，此task需等待所有的parent执行完成）
    public boolean isExecutable() {
        if (null == mParentTaskList || mParentTaskList.isEmpty()) return true;
        for (TaskInfo taskInfo : mParentTaskList) {
            if (!taskInfo.isCompleted) {
                return false;
            }
        }
        return true;
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

    private void createNewTaskManager() {
        new TaskManager().init(TaskInfo.this);
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
