package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hujiang.mytest.fragment.aidlFragment.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yuefeng
 * @version 3.3.1
 * @date 15/12/24
 */
public class HandlerLearnFragment extends Fragment {

    @Bind(R.id.tv)
    TextView mTv;
    private Handler mHandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.handler_learn_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        Toast.makeText(HandlerLearnFragment.this.getActivity(), "从主线程给子线程发消息,通过handler ", Toast.LENGTH_LONG).show();
                        Log.i("info", Looper.myLooper().getThread().getName());
                        return false;
                    }
                });
                Looper.loop();

            }
        }).start();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.button)
    public void onClick(View pView) {
        Message _message = Message.obtain();
//        _message.sendToTarget();
        mHandler.sendEmptyMessage(1);
//        mHandler.sendMessage(_message);
    }
}
