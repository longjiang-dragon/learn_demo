package com.hujiang.mytest.fragment.lock;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.util.PrintUtil;

import java.util.concurrent.locks.LockSupport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Date:  2019-11-30
 * Time:  17:00
 * Author: jianglong
 * -----------------------------
 * 生产者消费者模型
 */
public class JavaLockFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        JavaLockDemo.run2();
//        ExclusiveLock.run();
//        TwinsLock.run();



        //关于线程挂起的测试
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                PrintUtil.printLog(" LockSupport.park(this)start;");
                LockSupport.park(JavaLockFragment.this);
                PrintUtil.printLog(" LockSupport.park(this);");
            }
        });
        thread.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LockSupport.unpark(thread);
            }
        }, 10000);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
