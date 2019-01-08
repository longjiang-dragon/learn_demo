package com.hujiang.mytest.fragment.drag.helper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.fragment.app.Fragment;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 2017/2/24
 */
public class LearnViewDragHelperFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.learn_drag_helper_fragment, container, false);
    }
}
