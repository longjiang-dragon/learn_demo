package com.hujiang.mytest.fragment.custom.view.learn1.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author jianglong
 * @version 3.4.3
 * @desc
 * @date 2017/11/12
 */
public class CustomView1_1 extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomView1_1(Context context) {
        super(context);
    }

    public CustomView1_1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(100, 100, 98, mPaint);

        //
        canvas.drawOval(300, 0, 400, 200, mPaint);
        drawLine(canvas);
        //圆角
        canvas.drawRoundRect(10,400,400,500,10,10,mPaint);

    }

    private void drawLine(Canvas canvas) {
        canvas.save();
        canvas.translate(0,210);
        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
        canvas.drawLines(points, mPaint);
        canvas.restore();

    }
}
