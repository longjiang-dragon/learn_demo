package com.hujiang.mytest.fragment.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Date:  2019-12-22
 * Time:  22:27
 * Author: jianglong
 * -----------------------------
 * 自定义一个viewGroup实现一个九宫格
 */
public class ViewLayoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_layout, container, false);
    }
}
