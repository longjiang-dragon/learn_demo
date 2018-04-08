package com.hujiang.mytest.fragment.manager.Executor;

import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jianglong
 * @desc  满足任务需要串行执行，且任务一但执行完成，后续就不会再执行
 * @date 2018/4/4
 */
public class AsyncThreadExecutor extends ThreadPoolExecutor {

    public AsyncThreadExecutor() {
        super(0, 1, 30L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        return new Thread(r, "lib-initiation");
                    }
                });
    }
}
