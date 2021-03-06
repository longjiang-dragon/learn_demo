package com.hujiang.mytest.fragment.manager.test;

import android.app.Application;
import android.util.Log;

import com.hujiang.mytest.fragment.manager.LibInitiation;

/**
 * @author jianglong
 * @desc
 * @date 2018/4/2
 */
public class LibInitiation3 implements LibInitiation {
    @Override
    public void libInitiationStart(Application application) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("LibInitiation","LibInitiation3  "+Thread.currentThread().getName());
    }

    @Override
    public boolean isRunMainThread() {
        return false;
    }
}
