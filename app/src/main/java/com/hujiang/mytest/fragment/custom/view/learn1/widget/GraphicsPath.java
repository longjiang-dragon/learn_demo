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
 * @date 2017/11/13
 */
public class GraphicsPath extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GraphicsPath(Context context) {
        super(context);
    }

    public GraphicsPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        canvas.translate(0, 20);
        drawPath1(canvas);//关于path
        testFillType(canvas);        //path FillType

    }


    private void testFillType(Canvas canvas) {
        canvas.translate(0, 120);

        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.addCircle(100, 100, 98, Path.Direction.CW);//cw 顺时针
        path.addCircle(230, 100, 98, Path.Direction.CW);
        canvas.drawPath(path, mPaint);
    }


    private void drawPath1(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        Path path = new Path();
        path.lineTo(100, 100);//由当前位置 (0, 0) 向 (100, 100) 画一条直线
        path.rLineTo(100, 0);//由当前位置 (100, 100) 向正右方 100 像素的位置画一条直线
        path.close();

        path.moveTo(200, 0);// 作用于下一次
        path.lineTo(300, 100);
        canvas.drawPath(path, mPaint);
    }
}
