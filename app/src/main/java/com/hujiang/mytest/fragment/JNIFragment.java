package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hujiang.mytest.MainTwoActivity;
import com.hujiang.mytest.fragment.aidlFragment.R;


/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 2017/5/23
 */
public class JNIFragment extends Fragment implements View.OnClickListener {
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jni_layout, container, false);
        initView(view);
        setDataToView();
        runTestCode();
        return view;
    }

    private void initView(View view) {
        mTextView = (TextView) view.findViewById(R.id.tv);
        mTextView.setOnClickListener(this);
    }


    private void setDataToView() {
        mTextView.setText(stringFromJNI());
        javaToNative(JNIFragment.class.getSimpleName());
    }

    public native String stringFromJNI();

    public native void javaToNative(String className);

    public native void runTestCode();

    static {
        System.loadLibrary("jni_first_test");
    }

    @Override
    public void onClick(View v) {
        //作测试excludeFromRecents用
        MainTwoActivity.launch(getActivity());
    }
}
