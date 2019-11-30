package com.hujiang.mytest.fragment.lock;

import android.util.Log;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date:  2019-11-30
 * Time:  17:09
 * Author: jianglong
 * -----------------------------
 * 生产者消费者模型:
 * 1、生产者：向缓冲区中写入，当缓冲区满时等待；当缓冲区有新的消费者后，通知消费者。
 * 2、消费者：从缓冲区获取，当缓冲区为空时待待；并通知生产者
 * 3、缓冲区：存储数据，这里是LinkedList
 */
public class JavaLockDemo {
    private static final String TAG = JavaLockDemo.class.getCanonicalName();
    private static final int MAX_SIZE = 5;

    private final LinkedList<Integer> factory = new LinkedList<>();

    private Lock lock = new ReentrantLock(false);
    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    private void producer2(int number) {
        lock.lock();
        try {
            while (factory.size() == MAX_SIZE) {
                try {

                    printLog("-- factory is full --");
                    producer.await();
                    Thread.sleep(Math.round(Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printLog("produce product  == " + number);
            factory.add(number);
            consumer.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void consumer2() {
        lock.lock();
        try {
            while (factory.size() == 0) {
                try {
                    printLog("-- factory is empty --");
                    consumer.await();
                    Thread.sleep(Math.round(Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printLog("consume product ==  " + factory.removeLast());
            producer.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void run2() {
        final JavaLockDemo javaLockDemo = new JavaLockDemo();
        Thread pThread = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    javaLockDemo.producer2(i);
                }
            }

        };
        Thread cThread = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    javaLockDemo.consumer2();
                }
            }
        };
        pThread.start();
        cThread.start();
    }

    private void printLog(String msg) {
        Log.i(TAG, msg);
    }

}
