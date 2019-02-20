package com.hujiang.mytest;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * author：jianglong on  2019/2/19
 *
 * @desc
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
