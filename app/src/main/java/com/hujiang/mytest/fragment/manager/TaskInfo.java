package com.hujiang.mytest.fragment.manager;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;


/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public class TaskInfo {
    private volatile boolean isCompleted;//当前task是否执行完成
    private volatile boolean isRunning;
    private LibInitiation mLibInitiation;
    private Application mApplication;

    private CopyOnWriteArrayList<TaskInfo> mParentTaskList;
    private CopyOnWriteArrayList<TaskInfo> mChildTaskList;


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

    private CopyOnWriteArrayList<TaskInfo> getParentTaskList() {
        if (null == mParentTaskList) {
            mParentTaskList = new CopyOnWriteArrayList<>();
        }
        return mParentTaskList;
    }

    public CopyOnWriteArrayList<TaskInfo> getChildTaskList() {
        if (null == mChildTaskList) {
            mChildTaskList = new CopyOnWriteArrayList<>();
        }
        return mChildTaskList;
    }


    //开始执行当前任务
    public void startExecute(ExecutorService asyncExecutor) {
        if (!isExecutable() || isCompleted || isRunning) return;//不能执行
        isRunning = true;
        if (this.mLibInitiation.isSyncExecute()) {
            startSyncExecute();
        } else {
            startAsyncExecute(asyncExecutor);
        }
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

    private void startAsyncExecute(ExecutorService asyncExecutor) {
        asyncExecutor.execute(new Runnable() {
            @Override
            public void run() {
                startSyncExecute();
            }
        });
    }

    //同步执行
    private void startSyncExecute() {
        this.mLibInitiation.libInitiationStart(mApplication);
        this.isCompleted = true;
    }


    //判断当前task是否可执行。（一个task可有多个parent，如果parent未执行完成，此task需等待所有的parent执行完成）
    public boolean isExecutable() {
        if (null == mParentTaskList || mParentTaskList.isEmpty()) return true;
        for (TaskInfo taskInfo : mParentTaskList) {
            if (!taskInfo.isCompleted) {
//                Log.i("LibInitiation", "isExecutable===false");
                return false;
            }
        }
        return true;
    }

    public LibInitiation getLibInitiation() {
        return mLibInitiation;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (!(o instanceof String)) return false;
        return mLibInitiation.getClass().getSimpleName().equals(o);
    }

}
