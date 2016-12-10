package com.hujiang.mytest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.hujiang.mytest.fragment.aidlFragment.R;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 16/3/28
 */
public class TestMeasure extends LinearLayout {
	public TestMeasure(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI();
	}

	private void initUI() {
		LayoutInflater.from(getContext()).inflate(R.layout.after_follew_item, this);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.e("info", MeasureSpec.getMode(widthMeasureSpec) + "   EXACTLY===" + MeasureSpec.EXACTLY);
		Log.e("info", MeasureSpec.getSize(widthMeasureSpec) + "   宽度");
		Log.i("info", MeasureSpec.getSize(heightMeasureSpec) + "   高度 ");
		Log.i("info", MeasureSpec.getMode(heightMeasureSpec) + "   EXACTLY===" + MeasureSpec.AT_MOST);

		Log.i("info", "高度====" + getChildAt(0).getMeasuredWidth());
//		getChildAt(0).measure(widthMeasureSpec, heightMeasureSpec);
		Log.i("info", "测量后的宽度====" + getChildAt(0).getMeasuredWidth());
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);


	}
}
