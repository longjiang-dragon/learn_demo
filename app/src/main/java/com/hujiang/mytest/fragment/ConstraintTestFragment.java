package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * @author jianglong
 * @version 3.4.3
 * @desc
 * @date 2017/10/31
 */
public class ConstraintTestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_constraint_test, container, false);
        ButterKnife.bind(this, _view);
        return _view;
    }
}
