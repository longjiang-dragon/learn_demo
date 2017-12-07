package com.hujiang.mytest.fragment.hencoder.draw2.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.widget.ExtendsView;

/**
 * @author jianglong
 * @version 3.4.3
 * @desc
 * @date 2017/11/18
 */
public class CircleImageView extends ExtendsView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.eye);
        //Shader.TileMode.CLAMP 在图片比所绘图形小时，以最边缘的象素，填充不足部份
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawCircle(100, 100, 100, mPaint);

        plantA(canvas);
        ComposeShaderTest(canvas);

    }

    private void ComposeShaderTest(Canvas canvas) {
        canvas.save();
        canvas.translate(0,200);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //composeShader需要关闭硬件加速
        ComposeShader composeShader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(composeShader);
        canvas.drawCircle(200, 200, 200, mPaint);
        canvas.restore();
    }

    private void plantA(Canvas canvas) {
        canvas.save();
        canvas.translate(300, 0);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        Path path = new Path();
        path.lineTo(100, 800);
        path.rLineTo(600, 0);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.restore();
    }
}
