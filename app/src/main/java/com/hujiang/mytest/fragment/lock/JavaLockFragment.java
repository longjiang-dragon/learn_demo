package com.hujiang.mytest.fragment.lock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Date:  2019-11-30
 * Time:  17:00
 * Author: jianglong
 * -----------------------------
 * 生产者消费者模型
 */
public class JavaLockFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        JavaLockDemo.run2();
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
