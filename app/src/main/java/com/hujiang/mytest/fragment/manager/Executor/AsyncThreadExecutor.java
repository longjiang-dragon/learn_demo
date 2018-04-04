package com.hujiang.mytest.fragment.manager.Executor;

import android.support.annotation.NonNull;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jianglong
 * @desc
 * @date 2018/4/4
 */
public class AsyncThreadExecutor extends ThreadPoolExecutor {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    public AsyncThreadExecutor() {

        super(0, CPU_COUNT, 10L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        return new Thread(r, "lib-initiation");
                    }
                }, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
