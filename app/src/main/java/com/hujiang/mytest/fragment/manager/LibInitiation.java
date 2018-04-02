package com.hujiang.mytest.fragment.manager;

import android.app.Application;

/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public interface LibInitiation {
    void libInitiationStart(Application application);

    boolean isSyncExecute();
}
