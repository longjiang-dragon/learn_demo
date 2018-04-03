package com.hujiang.mytest.fragment.manager;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public class TaskInfo<T extends LibInitiation> {
    private boolean isCompleted;//当前task是否执行完成
    private T mLibInitiation;
    private Application mApplication;

    private CopyOnWriteArrayList<TaskInfo<T>> mParentTaskList;
    private CopyOnWriteArrayList<TaskInfo<T>> mChildTaskList;


    public TaskInfo(T libInitiation, Application application) {
        this.mLibInitiation = libInitiation;
        this.mApplication = application;
    }

    public void addToChildTaskList(TaskInfo<T> childTaskInfo) {
        if (null == childTaskInfo) return;
        if (null == mChildTaskList) {
            mChildTaskList = new CopyOnWriteArrayList<>();
        }
        childTaskInfo.getParentTaskList().add(this);
        mChildTaskList.add(childTaskInfo);
    }

    public void addToParentTaskList(TaskInfo<T> parentTaskInfo) {
        if (null == parentTaskInfo) return;
        if (null == mParentTaskList) {
            mParentTaskList = new CopyOnWriteArrayList<>();
        }
        childAndParentLink(parentTaskInfo, getFirstParentTask());
        childAndParentLink(this, parentTaskInfo);
    }


    //将两个node  建立关联
    private void childAndParentLink(TaskInfo<T> childTask, TaskInfo<T> parentTask) {
        if (null == parentTask) return;
        parentTask.getChildTaskList().add(childTask);
        childTask.getParentTaskList().add(parentTask);
    }

    private TaskInfo<T> getFirstParentTask() {
        if (null == mParentTaskList || mParentTaskList.isEmpty()) return null;
        return mParentTaskList.get(0);
    }

    private CopyOnWriteArrayList<TaskInfo<T>> getParentTaskList() {
        if (null == mParentTaskList) {
            mParentTaskList = new CopyOnWriteArrayList<>();
        }
        return mParentTaskList;
    }

    public CopyOnWriteArrayList<TaskInfo<T>> getChildTaskList() {
        if (null == mChildTaskList) {
            mChildTaskList = new CopyOnWriteArrayList<>();
        }
        return mChildTaskList;
    }



    //开始执行当前任务
    public void startExecute() {
        if (!isExecutable()) return;//不能执行
        if (this.mLibInitiation.isSyncExecute()) {
            startSyncExecute();
        } else {
            startAsyncExecute();
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
            for (TaskInfo<T> taskInfo : mChildTaskList) {
                stringBuilder.append(taskInfo.getLibInitiation().getClass().getSimpleName()+"     ");
            }
        }
        Log.e("LibInitiation", stringBuilder.toString());
    }

    //异步执行，但现在的异步执行并不是等异步执行完成后，再执行下一个任务
    private void startAsyncExecute() {
        // TODO: 2018/3/30 这里待实现
        this.mLibInitiation.libInitiationStart(mApplication);
        this.isCompleted = true;
    }

    //同步执行
    private void startSyncExecute() {
        this.mLibInitiation.libInitiationStart(mApplication);
        this.isCompleted = true;
    }


    //判断当前task是否可执行。（一个task可有多个parent，如果parent未执行完成，此task需等待所有的parent执行完成）
    public boolean isExecutable() {
        if (null == mParentTaskList || mParentTaskList.isEmpty()) return true;
        for (TaskInfo<T> taskInfo : mParentTaskList) {
            if (!taskInfo.isCompleted) {
//                Log.i("LibInitiation", "isExecutable===false");
                return false;
            }
        }
        return true;
    }

    public T getLibInitiation() {
        return mLibInitiation;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof String)) return false;
        return mLibInitiation.getClass().getSimpleName().equals(o);
    }

}
