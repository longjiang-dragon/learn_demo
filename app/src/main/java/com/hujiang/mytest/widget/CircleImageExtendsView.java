package com.hujiang.mytest.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.hujiang.mytest.fragment.aidlFragment.R;

/**
 * @author yuefeng
 * @version 3.4.1
 * @date 16/2/1
 * 继承自View的圆形图片
 */
public class CircleImageExtendsView extends View {
    private Bitmap mBitmap;
    private int mWidth, mHeigth;

    public CircleImageExtendsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray _TypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleImageExtendsView, 0, 0);
        int _Count = _TypedArray.getIndexCount();
        for (int i = 0; i < _Count; i++) {
            int _arr = _TypedArray.getIndex(i);
            switch (_arr) {
                case R.styleable.CircleImageExtendsView_src:
                    mBitmap = BitmapFactory.decodeResource(getResources(), _TypedArray.getResourceId(_arr, 0));
                    break;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int _min = Math.min(mHeigth, mWidth);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, _min, _min, false);
        canvas.drawBitmap(createCircleImage(mBitmap, _min), 0, 0, null);
    }

    private Bitmap createCircleImage(Bitmap pBitmapSource, int min) {
        Paint _Paint = new Paint();
        _Paint.setAntiAlias(true);
        Bitmap _target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas _Canvas = new Canvas(_target);
        _Canvas.drawCircle(min / 2, min / 2, min / 2, _Paint);
        _Paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        _Canvas.drawBitmap(pBitmapSource, 0, 0, _Paint);
        return _target;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int _SpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int _SpecSize = MeasureSpec.getSize(widthMeasureSpec);
        if (_SpecMode == MeasureSpec.EXACTLY) {
            mWidth = _SpecSize;
        } else {
            int _desireByImg = getPaddingLeft() + getPaddingRight() + mBitmap.getWidth();
            if (_SpecMode == MeasureSpec.AT_MOST) {

                mWidth = Math.min(_SpecSize, _desireByImg);
            }
            mWidth = _desireByImg;
        }
        _SpecMode = MeasureSpec.getMode(heightMeasureSpec);
        _SpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (_SpecMode == MeasureSpec.EXACTLY) {
            mHeigth = _SpecSize;
        } else {
            int _desireByImg = getPaddingTop() + getPaddingBottom() + mBitmap.getHeight();
            if (_SpecMode == MeasureSpec.AT_MOST) {

                mHeigth = Math.min(_desireByImg, _SpecSize);
            }
            mHeigth = _desireByImg;
        }
        setMeasuredDimension(mWidth, mHeigth);
    }
}
