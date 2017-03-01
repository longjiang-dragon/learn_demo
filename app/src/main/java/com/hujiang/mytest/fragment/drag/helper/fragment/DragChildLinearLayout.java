package com.hujiang.mytest.fragment.drag.helper.fragment;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 2017/2/24
 */
public class DragChildLinearLayout extends LinearLayout {
    private View mDragView1, mDragView2;
    private ViewDragHelper mViewDragHelper;

    public DragChildLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView1 = getChildAt(0);
        mDragView2 = getChildAt(1);
    }

    public class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d("DragLayout","clampViewPositionVertical " + top + "," + dy);
            final int topBound =getPaddingTop();
            final int bottomBound = getHeight()- mDragView1.getHeight();
            final int newTop =Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d("DragLayout","clampViewPositionHorizontal " + left + "," + dx);
            final int leftBound =getPaddingLeft();
            final int rightBound = getWidth() -mDragView1.getWidth();
            final int newLeft =Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            //从view的那个方向进入view,条件 是由setEdgeTrackingEnabled控制
            Log.d("DragLayout","onEdgeTouched " +edgeFlags);
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            //当从边界滑入时，会有回调
            mViewDragHelper.captureChildView(mDragView1,pointerId);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //滑动松开时调用
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            return true;
        }

    }
}
