package com.hujiang.mytest.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * @author yuefeng
 * @version 3.3.1
 * @date 15/12/27
 */
public class CircleImageExtendsDrawable extends Drawable {
    private Bitmap mBitmap;
    private RectF mRectF;
    private Paint mPaint;
    //圆直径
    private int mRadius;

    public CircleImageExtendsDrawable(Bitmap pBitmap) {
        mBitmap = pBitmap;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        BitmapShader _bitmapShader = new BitmapShader(pBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //通过矩阵来移动图片
        //http://www.cnblogs.com/tianzhijiexian/p/4298660.html
        Matrix _matrix=new Matrix();
        _matrix.setTranslate(-200,0);
        _bitmapShader.setLocalMatrix(_matrix);
        mPaint.setShader(_bitmapShader);
        mRadius = Math.min(pBitmap.getWidth(), pBitmap.getHeight()) / 2;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mRectF = new RectF(bounds);
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }


    @Override
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
