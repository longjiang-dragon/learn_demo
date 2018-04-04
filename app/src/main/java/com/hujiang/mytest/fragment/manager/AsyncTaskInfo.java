package com.hujiang.mytest.fragment.manager;

import android.app.Application;

/**
 * @author jianglong
 * @desc 在异步线程中运行的场景
 * @date 2018/4/4
 */
public class AsyncTaskInfo extends TaskInfo {
    public AsyncTaskInfo(LibInitiation libInitiation, Application application) {
        super(libInitiation, application);
    }


    //开始执行当前任务
    public void startExecute() {
        this.mLibInitiation.libInitiationStart(mApplication);
    }


    protected void createNewTaskManager() {
        new TaskManager().initAsync(AsyncTaskInfo.this);
    }

}