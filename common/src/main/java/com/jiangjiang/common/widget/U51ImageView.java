package com.jiangjiang.common.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by ChuanzhiL on 2018/1/22.
 */

public class U51ImageView extends AppCompatImageView {
    private OnVisibilityListener onVisibilityListener;

    public void setOnVisibilityListener(OnVisibilityListener listener) {
        onVisibilityListener = listener;
    }

    interface OnVisibilityListener {
        void onVisibility(int visibility);
    }


    public U51ImageView(Context context) {
        super(context);
    }

    public U51ImageView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public U51ImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (onVisibilityListener != null) {
            onVisibilityListener.onVisibility(visibility);
        }
    }
}
