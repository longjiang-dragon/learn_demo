package com.jiangjiang.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Window;
import android.widget.TextView;

import com.jiangjiang.common.R;

import androidx.annotation.NonNull;

/**
 * Date:  2019-06-06
 * Time:  14:54
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class LoadingProgressDialog extends Dialog {

    public LoadingProgressDialog(@NonNull Context context) {
        this(context, 0);
    }

    public LoadingProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setCancelable(false);
        setContentView(R.layout.ui_loading_layout);
    }

    public void setMeg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ((TextView) findViewById(R.id.tv_hint_text)).setText(msg);
        }
    }


}
