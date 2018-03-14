package com.hujiang.mytest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 16/3/28
 */
public class TestMeasureTextView extends TextView {
	private static final String KEY_lOG = TestMeasureTextView.class.getSimpleName();
	public TestMeasureTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.i(KEY_lOG,getMeasuredWidth()+"");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
