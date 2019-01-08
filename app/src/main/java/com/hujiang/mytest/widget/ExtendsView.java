package com.hujiang.mytest.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author jianglong
 * @version 3.4.3
 * @desc
 * @date 2017/11/18
 */
public class ExtendsView  extends View{
    protected Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    public ExtendsView(Context context) {
        super(context);
    }

    public ExtendsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
