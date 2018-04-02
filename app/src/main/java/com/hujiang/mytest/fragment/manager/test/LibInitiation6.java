package com.hujiang.mytest.fragment.manager.test;

import android.app.Application;
import android.util.Log;

import com.hujiang.mytest.fragment.manager.LibInitiation;

/**
 * @author jianglong
 * @desc
 * @date 2018/4/2
 */
public class LibInitiation6 implements LibInitiation {
    @Override
    public void libInitiationStart(Application application) {
        Log.i("LibInitiation", "LibInitiation6");
    }

    @Override
    public boolean isSyncExecute() {
        return true;
    }
}
