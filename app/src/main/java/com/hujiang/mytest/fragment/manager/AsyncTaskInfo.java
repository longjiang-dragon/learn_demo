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
        startAsyncExecute();
    }


    protected void startAsyncExecute() {
        this.mLibInitiation.libInitiationStart(mApplication);
        this.isCompleted = true;

    }

    //UI线程中执行
    protected void startMainThreadExecute() {
        new TaskManager().initAsync(AsyncTaskInfo.this);
    }

    @Override
    public void run() {
        startMainThreadExecute();
    }
}
