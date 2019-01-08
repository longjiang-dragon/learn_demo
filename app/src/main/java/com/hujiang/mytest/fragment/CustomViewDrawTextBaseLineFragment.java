package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.fragment.app.Fragment;


/**
 * @author yuefeng
 * @version 3.3.1
 * @date 15/12/23
 * 1、 自定义View中，在固定的区域内怎样让文字居中
 * 2、在draw test文字之前，可以通过Paint.measureText()拿到文字所占像素
 */
public class CustomViewDrawTextBaseLineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.custom_view_draw_text_base_line_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
