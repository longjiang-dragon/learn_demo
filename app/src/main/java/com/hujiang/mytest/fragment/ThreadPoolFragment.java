package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.Fragment;

/**
 * @author jianglong
 * @desc 线程池相关
 * @date 2018/1/2
 */
public class ThreadPoolFragment extends Fragment implements View.OnClickListener {
    private Executor mExecutor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thread_tool_layout, container, false);
        view.findViewById(R.id.tv).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
//        useSynchronousQueue();
        userLinkedBlockingQueue();

    }

    private void userLinkedBlockingQueue() {
        //3+7==10
        mExecutor = new ThreadPoolExecutor(
                2, 3, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(7),
                new RecorderThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            mExecutor.execute(new myRunnable(i + ""));
        }
    }

    //同步线程队列
    private void useSynchronousQueue() {
        mExecutor = new ThreadPoolExecutor(
                2, 3, 30, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new RecorderThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            mExecutor.execute(new myRunnable(i + ""));
        }
    }

    public class RecorderThreadFactory implements ThreadFactory {
        int threadNum = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            final Thread result = new Thread(runnable, "thread-number===" + threadNum) {
                @Override
                public void run() {
                    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                    super.run();
                }
            };
            threadNum++;
            return result;
        }
    }


    public class myRunnable implements Runnable {
        private String mThreadName;

        public myRunnable(String threadName) {
            this.mThreadName = threadName;
        }

        @Override
        public void run() {
            Log.i("thread_info=====", Thread.currentThread().getName() + "    " + mThreadName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
