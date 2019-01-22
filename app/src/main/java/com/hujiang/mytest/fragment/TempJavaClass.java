package com.hujiang.mytest.fragment;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * author：jianglong on  2019/1/17
 *
 * @desc
 */
public class TempJavaClass extends Activity {
    public TempJavaClass() {
        GestureDetector.SimpleOnGestureListener listener=new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetector.SimpleOnGestureListener listener=new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        };
        return super.onTouchEvent(event);

    }
}
