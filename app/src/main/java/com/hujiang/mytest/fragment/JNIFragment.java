package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;


/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 2017/5/23
 */
public class JNIFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jni_layout, container, false);
    }

    public native String  stringFromJNI();

    static {
        System.loadLibrary("jni_frist_test");
    }

}
