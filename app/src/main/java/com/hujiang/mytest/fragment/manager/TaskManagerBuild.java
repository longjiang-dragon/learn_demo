package com.hujiang.mytest.fragment.manager;

import android.app.Application;

/**
 * @author jianglong
 * @desc
 * @date 2018/4/2
 */
public class TaskManagerBuild {
    private TaskInfo mRootTaskInfo;
    private TaskInfo mCurrentTaskInfo;
    private TaskInfo mTempTaskInfo;
    private Application mApplication;

    public TaskManagerBuild(Application application) {
        this.mApplication = application;
    }

    public TaskManagerBuild addChildNode(LibInitiation libInitiation) {
        if (null == libInitiation) return this;
        this.mTempTaskInfo = generateTaskInfo(libInitiation);
        if (isAddRootNode()) {
            this.mRootTaskInfo = mTempTaskInfo;
        } else {
            this.mCurrentTaskInfo.addToChildTaskList(mTempTaskInfo);
        }
        this.mCurrentTaskInfo = mTempTaskInfo;
        return this;
    }

    public TaskManagerBuild addParentNode(LibInitiation libInitiation) {
        if (null == libInitiation) return this;
        this.mTempTaskInfo = generateTaskInfo(libInitiation);
        if (isAddRootNode()) {
            //不存在root节点的情况
            this.mRootTaskInfo = mTempTaskInfo;
        } else if (isReplaceRootNode()) {
            //替换root节点
            this.mRootTaskInfo.addToParentTaskList(this.mTempTaskInfo);
            this.mRootTaskInfo = mTempTaskInfo;
        } else {
            mCurrentTaskInfo.addToParentTaskList(mTempTaskInfo);
        }


        this.mCurrentTaskInfo = mTempTaskInfo;
        return this;
    }

    private TaskInfo generateTaskInfo(LibInitiation libInitiation) {
        return new TaskInfo(libInitiation, mApplication);
    }

    private boolean isAddRootNode() {
        return null == mRootTaskInfo;
    }

    public void build() {
        new TaskManager().init(mRootTaskInfo);
    }


    public boolean isReplaceRootNode() {
        return mRootTaskInfo == mCurrentTaskInfo;
    }
}
