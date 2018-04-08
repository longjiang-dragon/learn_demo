package com.hujiang.mytest.fragment.manager;

import android.app.Application;

/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public interface LibInitiation {
    /**
     * 执行具体的任务
     */
    void libInitiationStart(Application application);
    /**
     * 此任务是否需要在主线程中执行
     */
    boolean isRunMainThread();
}
