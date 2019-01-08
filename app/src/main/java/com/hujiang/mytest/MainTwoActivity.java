package com.hujiang.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 2017/6/14
 */
public class MainTwoActivity extends AppCompatActivity {
    public static void launch(Context pContext) {
        Intent starter = new Intent(pContext, MainTwoActivity.class);
        pContext.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_main_layout);
    }
}
