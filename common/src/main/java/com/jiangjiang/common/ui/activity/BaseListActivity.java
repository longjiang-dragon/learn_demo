package com.jiangjiang.common.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.jiangjiang.common.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Date:  2020-01-19
 * Time:  14:43
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public abstract class BaseListActivity extends AppCompatActivity {
    protected FrameLayout rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base_activity_layout);
        rootView = findViewById(R.id.fl_content_container);
        getLayoutInflater().inflate(getLayoutRes(), rootView);
        initView();
    }

    protected void initView() {
        initTitleLayout();
    }

    protected void initTitleLayout() {

    }


    protected abstract @LayoutRes
    int getLayoutRes();


}
