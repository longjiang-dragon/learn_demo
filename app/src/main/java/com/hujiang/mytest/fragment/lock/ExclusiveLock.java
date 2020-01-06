package com.hujiang.mytest.fragment.lock;

import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * Date:  2019-12-28
 * Time:  23:33
 * Author: jianglong
 * -----------------------------
 * 独占式
 */
public class ExclusiveLock implements Lock {
    private static final String TAG = ExclusiveLock.class.getCanonicalName();

    private final Sync mSync = new Sync();

    @Override
    public void lock() {
        mSync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        mSync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return mSync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeOut, TimeUnit unit) throws InterruptedException {
        return mSync.tryAcquireNanos(1, unit.toNanos(timeOut));
    }

    @Override
    public void unlock() {
        mSync.release(1);
    }

    @Override
    public Condition newCondition() {
        return mSync.newCondition();
    }

    public boolean isLocked() {
        return mSync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return mSync.hasQueuedThreads();
    }

    public static void run() {
        ExclusiveLock lock = new ExclusiveLock();
        for (int i = 0; i < 3; i++) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    lock.lock();
                    printLog("begin:" + index);
                    try {
                        printLog("run:" + index);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        printLog("end:" + index);
                        lock.unlock();
                    }

                }
            }.start();

        }

    }


    private static class Sync extends AbstractQueuedSynchronizer {


        @Override
        protected boolean isHeldExclusively() {
            //count==1  说明同步器是在独占模式下
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }


        Condition newCondition() {
            return new ConditionObject();
        }

    }

    private static void printLog(String msg) {
        Log.i(TAG, msg);
    }
}
