package com.hujiang.mytest.fragment.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * Date:  2019-12-22
 * Time:  22:29
 * Author: jianglong
 * -----------------------------
 */
public class CustomGridLayout extends ViewGroup {
    /**
     * 列数
     */
    private int mSpan = 3;
    /**
     * Item 水平之间的间距
     */
    private int mHorizontalSpace = 0;
    /**
     * Item 垂直之间的间距
     */
    private int mVerticalSpace = 0;
    /**
     * 最大的Item数量
     */
    private int mMaxItem = 9;

    public CustomGridLayout(Context context) {
        super(context);
    }

    public CustomGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取控件的宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 计算单个子View的宽度
        int itemWidth = (width - getPaddingLeft() - getPaddingRight() - mHorizontalSpace * (mSpan - 1)) / mSpan;
        // 测量子View的宽高
        int childCount = getChildCount();
        // 计算一下最大的条目数量
        childCount = Math.min(childCount, mMaxItem);
        if (childCount <= 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int itemSpec = MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY);
            measureChild(child, itemSpec, itemSpec);
        }
        int height = itemWidth * (childCount % mSpan == 0 ? (childCount / mSpan) : (childCount / mSpan + 1))
                + mVerticalSpace * ((childCount - 1) / mSpan);
        // 指定自己的宽高
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        // 计算一下最大的条目数量
        childCount = Math.min(childCount, mMaxItem);
        if (childCount <= 0) {
            return;
        }
        int cl = getPaddingLeft();
        int ct = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            child.layout(cl, ct, cl + width, ct + height);
            // 累加宽度
            cl += width + mHorizontalSpace;
            // 如果是换行
            if ((i + 1) % mSpan == 0) {
                // 重置左边的位置
                cl = getPaddingLeft();
                // 叠加高度
                ct += height + mVerticalSpace;
            }
        }
    }
}
