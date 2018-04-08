package com.hujiang.mytest.fragment.manager.test;

import android.app.Application;
import android.util.Log;

import com.hujiang.mytest.fragment.manager.LibInitiation;

/**
 * @author jianglong
 * @desc
 * @date 2018/4/2
 */
public class LibInitiationMain7 implements LibInitiation {
    @Override
    public void libInitiationStart(Application application) {
        Log.i("LibInitiation", LibInitiationMain7.class.getSimpleName()+"   "+Thread.currentThread().getName());
    }

    @Override
    public boolean isRunMainThread() {
        return true;
    }
}
