package com.hujiang.mytest;

import android.os.Looper;
import android.util.Log;

/**
 * Date:  2019-12-18
 * Time:  22:49
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public abstract class TestCode {
    public static abstract  class aa{}
    public  int aaaaa(){
        StringBuilder builder=new StringBuilder();
        StackTraceElement[] stackTraceElements=Looper.getMainLooper().getThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
               builder.append(stackTraceElements.toString()).append("\n");
        }
        Log.i("stackTrace",builder.toString());
        
        return 9;
    }
}
