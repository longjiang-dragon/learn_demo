package com.jiangjiang.common.ui;

import android.os.Bundle;

import com.jiangjiang.common.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Date:  2020-01-16
 * Time:  16:21
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base_activity_layout);
        mViewModel = getViewModel();
    }

    public abstract VM getViewModel();
}
