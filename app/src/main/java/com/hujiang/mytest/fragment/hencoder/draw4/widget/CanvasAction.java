package com.hujiang.mytest.fragment.hencoder.draw4.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.widget.ExtendsView;

import androidx.annotation.Nullable;

/**
 * @author jianglong
 * @version 3.4.3
 * @desc
 * @date 2017/11/18
 */
public class CanvasAction extends ExtendsView {
    private Bitmap bitmap;
    private Camera camera;
    int picHeight,picWidth;


    public CanvasAction(Context context) {
        super(context);
    }

    public CanvasAction(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        camera = new Camera();
        picHeight=bitmap.getHeight();
        picWidth=bitmap.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        camera.save();
        camera.rotateY(45);
        camera.applyToCanvas(canvas);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        camera.restore();
        canvas.restore();


    }

}
