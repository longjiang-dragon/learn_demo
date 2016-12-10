package com.hujiang.mytest.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * @author yuefeng
 * @version 3.3.1
 * @date 15/12/26
 */
public class RoundImageDrawable extends Drawable {
    private Paint mPaint;
    private RectF mRectF;
    private Bitmap mBitmap;

    public RoundImageDrawable(Bitmap pBitmap) {
        mBitmap = pBitmap;
        BitmapShader _bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(_bitmapShader);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(mRectF,30,30,mPaint);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRectF = new RectF(left, top, right, bottom);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return  mBitmap.getWidth();
    }
}
