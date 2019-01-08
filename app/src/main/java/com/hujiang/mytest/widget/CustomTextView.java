package com.hujiang.mytest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.core.content.ContextCompat;


/**
 * @author yuefeng
 * @version 3.3.1
 * @date 15/11/30
 */
public class CustomTextView extends View {
    String[] datas = new String[]{"字体占用宽度像素=="};
    private static final String TEXT_CONTENT = "字体占用宽度像素";
    private TextPaint mPaint;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas pCanvas) {
        super.onDraw(pCanvas);
        pCanvas.drawColor(Color.LTGRAY);
        drawDeformityText(pCanvas);
        drawNormalText(pCanvas);
        Log.i("info", getMeasuredHeight() + "   " + getMeasuredWidth());
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.density = getResources().getDisplayMetrics().density;
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
//        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(30);

        throughRoundRect(pCanvas);
        //圆角矩形
        int _left = 0;
        int _top = 0;
        int _right = 400;
        int _bottom = 40;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.CYAN);
        RectF _rectF = new RectF(_left, _top, _right, _bottom);
        pCanvas.drawRoundRect(_rectF, 20, 20, mPaint);
        //文字
        mPaint.setColor(Color.RED);

        int _startPointX = getContext().getResources().getDisplayMetrics().widthPixels - getMeasuredWidth();
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        //targetRect.centerY() - (FontMetrics.bottom - FontMetrics.top) / 2 - FontMetrics.top
        /**
         * 原本公式是这样的，现在使用的是优化后的版本
         *targetRect.centerY() - (FontMetrics.bottom - FontMetrics.top) / 2 - FontMetrics.top:基线位置
         *targetRect.centerY() :矩形垂直中点
         * (FontMetrics.bottom - FontMetrics.top) / 2:字体居中点的一半
         * targetRect.centerY() - (FontMetrics.bottom - FontMetrics.top) / 2：求FontMetrics.top到矩形上边的距离
         *FontMetrics.top(其是一个负值，是以基线为准。top在基线之上所以是负值 )
         */

        int _baseLine = (int) (_rectF.bottom + _rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        pCanvas.drawLine(0, _baseLine, 1024, _baseLine, mPaint);
        mPaint.setTextAlign(Paint.Align.CENTER);
        pCanvas.drawText(datas[0] + mPaint.measureText(datas[0])
                , _rectF.centerX(), _baseLine, mPaint);
//            pCanvas.drawText(TEXT_CONTENT, 0, getMeasuredHeight(), mPaint);

        test(pCanvas);
    }

    private void drawNormalText(Canvas canvas) {
        canvas.save();
        canvas.translate(100, 900);
        Rect targetRect = new Rect(0, 0, 800, 200);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_bright));
        //background
        canvas.drawRect(targetRect, paint);
        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        paint.setTextSize(80);
        paint.setStrokeWidth(3);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseLine = targetRect.centerY() - (fontMetrics.bottom + (-fontMetrics.top)) / 2 + (-fontMetrics.top);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(TEXT_CONTENT, targetRect.centerX(), baseLine, paint);
        canvas.restore();
    }

    private void drawDeformityText(Canvas canvas) {
        canvas.save();
        canvas.translate(100, 600);
        Rect targetRect = new Rect(0, 0, 800, 200);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_bright));
        //background
        canvas.drawRect(targetRect, paint);

        paint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        paint.setTextSize(80);
        paint.setStrokeWidth(3);
        canvas.drawText(TEXT_CONTENT, targetRect.left, targetRect.centerY(), paint);
        canvas.restore();
    }

    /**
     * 通过drawable画圆角距形
     */
    private void throughRoundRect(Canvas pCanvas) {
        Drawable _drawable = getResources().getDrawable(R.drawable.ddcx_shape_time_range);
        _drawable.setBounds(0, 500, 200, 540);
        _drawable.draw(pCanvas);
    }

    /**
     * 测试怎样才能让字体垂直居中
     *
     * @param pCanvas
     */
    private static final String STR = "猪上树了，快来看。";

    private void test(Canvas pCanvas) {
        Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _paint.setStrokeWidth(3);
        _paint.setTextSize(60);
        Paint.FontMetricsInt _fontMetricsInt = _paint.getFontMetricsInt();
        //用于获取第一个字符的位置
        Rect _bound1 = new Rect();
        _paint.getTextBounds(STR, 0, 1, _bound1);
        if (_bound1.width() == _paint.measureText(STR.substring(0, 1))) {
            Log.i("info", "两个方法获得字体所占相素相同都是===" + _bound1.width());
        } else {
            Log.i("info", "两个方法获得字体所占相素不相同");
            /**
             * 可以得出：
             * _paint.measureText(STR.substring(0,1)得到的是字体加左右间距呢
             */

        }
        Rect _bound2 = new Rect();
        _paint.getTextBounds(STR, 0, 6, _bound2);
        //x轴开始的位置
        int _x = 10;
        //下面这是基线
        int _y = 400;
        pCanvas.drawText(STR, _x, _y, _paint);
        //空心
        _paint.setStyle(Paint.Style.STROKE);
        pCanvas.save();
        //这里有translate。getTextBounds得到的矩形也是以baseline为基准的
        pCanvas.translate(_x, _y);
        _paint.setColor(Color.GREEN);
        pCanvas.drawRect(_bound1, _paint);
        pCanvas.restore();

        //bound2
        pCanvas.save();
        _paint.setColor(Color.MAGENTA);
        pCanvas.translate(_x, _y);
        pCanvas.drawRect(_bound2, _paint);
        pCanvas.restore();
        //baseline
        _paint.setColor(Color.RED);
        pCanvas.drawLine(_x, _y, 1024, _y, _paint);
        //ascent()
        _paint.setColor(Color.YELLOW);
        pCanvas.drawLine(_x, _y + _fontMetricsInt.ascent, 1024, _y + _fontMetricsInt.ascent, _paint);
        //descent
        _paint.setColor(Color.BLUE);
        pCanvas.drawLine(_x, _y + _fontMetricsInt.descent, 1024, _y + _fontMetricsInt.descent, _paint);
        //top
        _paint.setColor(Color.BLACK);
        pCanvas.drawLine(_x, _y + _fontMetricsInt.top, 1024, _y + _fontMetricsInt.top, _paint);

        //bottom
        _paint.setColor(Color.GREEN);
        pCanvas.drawLine(_x, _y + _fontMetricsInt.bottom, 1024, _y + _fontMetricsInt.bottom, _paint);

    }

}
