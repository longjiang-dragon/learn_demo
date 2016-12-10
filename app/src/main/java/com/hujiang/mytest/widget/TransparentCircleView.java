package com.hujiang.mytest.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yuefeng
 * @version 3.4.1
 * @date 16/2/1
 */
public class TransparentCircleView extends View {
    private Canvas temp;
    private Paint paint;
    private Paint p = new Paint( );
    private Paint transparentPaint;

    public TransparentCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Canvas pCanvas) {
        Bitmap _Bitmap = Bitmap.createBitmap(pCanvas.getWidth(), pCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        temp = new Canvas(_Bitmap);
        paint = new Paint( );
//        paint.setAntiAlias(true);
        paint.setColor(0xcc000000);
        transparentPaint = new Paint( );
//        transparentPaint.setAntiAlias(true);
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap _Bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        temp = new Canvas(_Bitmap);
        paint = new Paint();
        paint.setColor(0xcc000000);
        transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        temp.drawRect(0, 0, temp.getWidth(), temp.getHeight(), paint);
        temp.drawCircle(570, 450, 100, transparentPaint);
//        p.setAntiAlias(true);
        canvas.drawBitmap(_Bitmap, 0, 0, p);
    }
}
