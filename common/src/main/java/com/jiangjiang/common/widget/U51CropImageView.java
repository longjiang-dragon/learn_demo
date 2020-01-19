package com.jiangjiang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.jiangjiang.common.R;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * top crop
 * bottom crop
 */
public class U51CropImageView extends AppCompatImageView {

    private MatrixCropType mMatrixType = MatrixCropType.TOP_CROP; // default

    private enum MatrixCropType {
        TOP_CROP(0),
        BOTTOM_CROP(1);

        private int mValue;

        private MatrixCropType(int value) {
            mValue = value;
        }

        public static MatrixCropType fromValue(int value) {
            for (MatrixCropType matrixCropType : values()) {
                if (matrixCropType.mValue == value) {
                    return matrixCropType;
                }
            }

            // default
            return MatrixCropType.TOP_CROP;
        }
    }

    public U51CropImageView(Context context) {
        this(context, null);
    }

    public U51CropImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public U51CropImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // get attributes
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CommonCropImageView, 0, 0);
            try {
                mMatrixType = MatrixCropType.fromValue(a.getInteger(R.styleable.CommonCropImageView_matrixType, 0));
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    protected boolean setFrame(int frameLeft, int frameTop, int frameRight, int frameBottom) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            float frameWidth = frameRight - frameLeft;
            float frameHeight = frameBottom - frameTop;

            float originalImageWidth = (float) getDrawable().getIntrinsicWidth();
            float originalImageHeight = (float) getDrawable().getIntrinsicHeight();

            float usedScaleFactor = 1; /* 用来等比缩放，填充整个ImageView */
            if ((frameWidth > originalImageWidth) || (frameHeight > originalImageHeight)) {
                /* If frame is bigger than image => Crop it, keep aspect ratio and position it at the bottom and center horizontally */
                float fitHorizontallyScaleFactor = frameWidth / originalImageWidth;
                float fitVerticallyScaleFactor = frameHeight / originalImageHeight;

                usedScaleFactor = Math.max(fitHorizontallyScaleFactor, fitVerticallyScaleFactor);
            }

            float newImageWidth = originalImageWidth * usedScaleFactor;
            float newImageHeight = originalImageHeight * usedScaleFactor;

            Matrix matrix = getImageMatrix();
            matrix.setScale(usedScaleFactor, usedScaleFactor, 0, 0);

            /* 缩放图片的宽度和高度都不固定，可能大于等于或小于 */

            switch (mMatrixType) {
                case TOP_CROP:
                    matrix.postTranslate((frameWidth - newImageWidth) / 2, 0);
                    break;
                case BOTTOM_CROP:
                    matrix.postTranslate((frameWidth - newImageWidth) / 2, frameHeight - newImageHeight);
                    break;

                default:
                    break;
            }

            setImageMatrix(matrix);
        }
        return super.setFrame(frameLeft, frameTop, frameRight, frameBottom);
    }

}