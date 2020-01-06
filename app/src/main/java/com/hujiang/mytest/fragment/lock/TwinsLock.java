package com.hujiang.mytest.fragment.lock;

import com.hujiang.mytest.util.PrintUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * Date:  2019-12-28
 * Time:  23:56
 * Author: jianglong
 * -----------------------------
 * 共享式锁(与独占式获取最主要的区别在于 同一时刻能否有多个线程同时获取到同步状态。)
 */
public class TwinsLock implements Lock {
    private Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            setState(count);
        }

        //如果该方法的返回值大于0，表示能够获取到同步状态。
        @Override
        protected int tryAcquireShared(int reduceCount) {
            //为什么这里需要死循环呢？
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            //为什么这里需要死循环呢？
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    public static void run() {
        final TwinsLock lock = new TwinsLock();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread() {

                @Override
                public void run() {
                    lock.lock();
                    PrintUtil.printLog("begin:" + index);
                    try {
                        PrintUtil.printLog("run:" + index);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        PrintUtil.printLog("end:" + index);
                        lock.unlock();
                    }
                }

            }.start();
        }
    }
}
