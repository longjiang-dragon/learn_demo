package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.3.1
 * @date 15/12/25
 */
public class ThreadLocalFragment extends Fragment {
    private static final String RESULT = "REsult";
    @BindView (R.id.tv)
    TextView mTv;
    private ThreadLocal<Integer> mIntegerThreadLocal = new InheritableThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return new Integer(0);
        }
    };
    private StringBuffer result = new StringBuffer();


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mTv.setText(msg.getData().getString(RESULT));
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thread_local_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Runnable _runnable = new Runnable() {
            @Override
            public void run() {
                int _temp = mIntegerThreadLocal.get();
                for (int i = 0; i < 5; i++) {
                    _temp++;
                }
                result.append(_temp).append("    ").append(Thread.currentThread().getName()).append("\n");
                Bundle _bundle=new Bundle();
                _bundle.putString(RESULT, result.toString());
                Message _message=Message.obtain(mHandler);
                _message.setData(_bundle);
                _message.sendToTarget();
            }
        };

        new Thread(_runnable,"Thread_1").start();
        new Thread(_runnable,"Thread_2").start();
        new Thread(_runnable,"Thread_3").start();
        new Thread(_runnable,"Thread_4").start();
        new Thread(_runnable,"Thread_5").start();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
