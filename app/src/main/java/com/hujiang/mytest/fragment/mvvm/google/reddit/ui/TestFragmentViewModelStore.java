package com.hujiang.mytest.fragment.mvvm.google.reddit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Date:  2020-01-15
 * Time:  14:26
 * Author: jianglong
 * -----------------------------
 * 测试fragment中的ViewModelStore是不是同一个
 */
public class TestFragmentViewModelStore extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getViewModelStore();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
