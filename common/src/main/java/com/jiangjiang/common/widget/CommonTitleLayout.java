package com.jiangjiang.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiangjiang.common.R;
import com.jiangjiang.common.util.ViewUtil;
import com.jiangjiang.common.widget.badgeview.QBadgeView;

import androidx.annotation.ColorRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;

public class CommonTitleLayout extends ConstraintLayout {

    private U51CropImageView mBGView;
    private ImageView mIconLeft; // 左侧按钮（返回或者叉）
    private TextView mTextTitle; // 主标题
    private TextView mTextSubTitle; // 副标题
    private TextView mTextRightText; //右则文字
    private ImageView mRightFirstIcon; //右则第一张图
    private ImageView mRightSecondIcon; //右则第二张图
    // 导航栏底部阴影
    private View mShadowView;

    private int titleColor;


    public CommonTitleLayout(Context context) {
        this(context, null);
    }

    public CommonTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        setTitleColor(titleColor);
        refreshIconColor();
        setRightTextColor(titleColor);
        initShadow();

    }

    private String getActivityTitleStr() {
        if (getContext() instanceof Activity) {
            return (String) ((Activity) getContext()).getTitle();
        }
        return "";
    }

    private void initShadow() {
        GradientDrawable shadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0x44333333, Color.TRANSPARENT});
        mShadowView.setBackgroundDrawable(shadow);
        setShadowVisible(true);
    }

    private void initView() {
        mBGView = findViewById(R.id.title_layout_bg);
        mIconLeft = findViewById(R.id.iv_left_icon);
        mTextTitle = findViewById(R.id.tv_title);
        mTextSubTitle = findViewById(R.id.tv_sub_title);
        mTextRightText = findViewById(R.id.tv_right_text);
        mRightFirstIcon = findViewById(R.id.iv_right_first_icon);
        mRightSecondIcon = findViewById(R.id.iv_right_second_icon);
        mShadowView = findViewById(R.id.TitleLayout_Shadow);
        setTitle(getActivityTitleStr());

    }


    /**
     * 隐藏或显示导航栏左侧icon（一般是X或者返回键）
     */
    public void hideLeftIcon(boolean hide) {
        getLeftIcon().setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    /// 设置主标题

    public void setTitle(int title) {
        setTitle(getContext().getResources().getText(title));
    }

    public void setTitle(CharSequence title) {
        mTextTitle.setVisibility(View.VISIBLE);
        mTextTitle.setText(title);
    }

    /// 设置副标题

    public void setSubTitle(int title) {
        setSubTitle(getContext().getResources().getText(title));
    }

    public void setSubTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            mTextSubTitle.setVisibility(View.VISIBLE);
            mTextSubTitle.setText(title);
        } else {
            mTextSubTitle.setVisibility(GONE);
        }
    }

    /**
     * 配置标题颜色（主标题&副标题）
     *
     * @param color
     */
    public void setTitleColor(int color) {
        titleColor = color;
        mTextTitle.setTextColor(color);
        mTextSubTitle.setTextColor(color);
    }

    /**
     * 配置标题颜色（主标题&副标题）
     *
     * @param id
     */
    public void setTitleColorRes(@ColorRes int id) {
        titleColor = getResources().getColor(id);
        mTextTitle.setTextColor(titleColor);
        mTextSubTitle.setTextColor(titleColor);
    }

    /**
     * 设置主标题大小
     *
     * @param size
     */
    public void setTitleSize(int size) {
        mTextTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    /**
     * 隐藏主标题
     */
    public void hideTitle() {
        mTextTitle.setVisibility(GONE);
    }

    /**
     * 隐藏副标题
     */
    public void hideSubTitle() {
        mTextSubTitle.setVisibility(GONE);
    }

    /**
     * 给图标着色(图标显示才给图标着色)
     */
    private void refreshIconColor() {
        if (mIconLeft.getVisibility() == View.VISIBLE) {
            Drawable leftDrawable = mIconLeft.getDrawable();
            setLeftIcon(leftDrawable);
        }

        if (mRightFirstIcon.getVisibility() == View.VISIBLE) {
            Drawable rightFirstDrawable = mRightFirstIcon.getDrawable();
            setRightFirstIcon(rightFirstDrawable);
        }

        if (mRightSecondIcon.getVisibility() == View.VISIBLE) {
            Drawable rightSecondDrawable = mRightSecondIcon.getDrawable();
            setRightSecondIcon(rightSecondDrawable);
        }

        setRightTextColor(titleColor);
    }

    /**
     * Right Text
     */
    public void setRightText(int right) {
        mTextRightText.setVisibility(View.VISIBLE);
        mTextRightText.setText(right);
    }

    public void setRightText(CharSequence right) {
        mTextRightText.setVisibility(View.VISIBLE);
        mTextRightText.setText(right);
    }

    public void setRightTextColor(int color) {
        mTextRightText.setTextColor(color);
    }


    // 设置左侧图标并着色

    public void setLeftIcon(int left) {
        setLeftIcon(getResources().getDrawable(left));
    }

    public void setLeftIcon(Drawable left) {
        if (left == null) {
            return;
        }
        mIconLeft.setVisibility(View.VISIBLE);
        DrawableCompat.setTint(left, getIconColor());
        mIconLeft.setImageDrawable(left);

        /* 上下填满 */
        int expand = (getResources().getDimensionPixelSize(R.dimen.ui_navigation_bar_height) - getResources().getDimensionPixelSize(R.dimen.title_layout_icon_size)) / 2;
        ViewUtil.expandViewTouchDelegate(mIconLeft, expand, expand, 0, 0);
    }

    // 设置右侧第一个图标并着色

    public void setRightFirstIcon(int icon) {
        setRightFirstIcon(getResources().getDrawable(icon));
    }

    public void setRightFirstIcon(Drawable icon) {
        if (icon == null) {
            return;
        }
        mRightFirstIcon.setVisibility(View.VISIBLE);
        DrawableCompat.setTint(icon, getIconColor());
        mRightFirstIcon.setImageDrawable(icon);

        int expand = ViewUtil.dip2px(getContext(), 8);
        ViewUtil.expandViewTouchDelegate(mRightFirstIcon, expand, expand, 0, 0);
    }

    // 设置右侧第二个图标并着色

    public void setRightSecondIcon(int icon) {
        setRightSecondIcon(getResources().getDrawable(icon));
    }

    public void setRightSecondIcon(Drawable icon) {
        if (icon == null) {
            return;
        }

        mRightSecondIcon.setVisibility(View.VISIBLE);
        DrawableCompat.setTint(icon, getIconColor());
        mRightSecondIcon.setImageDrawable(icon);

        int expand = ViewUtil.dip2px(getContext(), 8);
        ViewUtil.expandViewTouchDelegate(mRightSecondIcon, expand, expand, 0, 0);
    }

    public int getIconColor() {
        int color = titleColor;
        if (titleColor != 0xFFFFFFFF && titleColor != 0xFFFFFF) {
            color = 0xFF999999;
        }
        return color;
    }


    private void showQBadgeView(View view, QBadgeView qBadgeView, int count, int bgColor, int textColor, int offsetX, int offsetY) {
        int padding = 3;
        if (count < 0) { // 负数为红点
            padding = 4;
            offsetX = 12;
            offsetY = 12;
        } else if (count == 0) {
            // 设置0即hide badge
        } else if (count < 10) { // 0<x<10
            offsetX = 8;
            offsetY = 12;
        } else if (count < 100) { // 10<x<100
            offsetX = 8;
            offsetY = 12;
        } else {
            offsetX = 4;
            offsetY = 12;
        }

        qBadgeView.setBadgeBackgroundColor(bgColor);
        qBadgeView.setBadgeTextColor(textColor);
        qBadgeView.setBadgePadding(padding, true);
        qBadgeView.setBadgeTextSize(10, true);
        qBadgeView.setShowShadow(false);
        qBadgeView.setBadgeGravity(Gravity.TOP | Gravity.END);
        qBadgeView.setGravityOffset(offsetX, offsetY, true);
        qBadgeView.bindTarget(view).setBadgeNumber(count);
    }


    public void setOnRightFirstClickListener(OnClickListener onClickListener) {
        findViewById(R.id.iv_right_first_icon).setOnClickListener(onClickListener);
    }

    public void setOnRightSecondClickListener(OnClickListener onClickListener) {
        findViewById(R.id.iv_left_icon).setOnClickListener(onClickListener);
    }

    /// 获取控件引用

    public ImageView getLeftIcon() {
        return mIconLeft;
    }


    public TextView getTitle() {
        return mTextTitle;
    }

    public TextView getSubTitle() {
        return mTextSubTitle;
    }

    public ImageView getRightFirstIcon() {
        return mRightFirstIcon;
    }

    public ImageView getRightSecondIcon() {
        return mRightSecondIcon;
    }


    public TextView getRightText() {
        return mTextRightText;
    }

    public void setGradientBg(GradientDrawable.Orientation orientation, int colors[]) {
        setBackgroundImage(null);
        GradientDrawable bg = new GradientDrawable(orientation, colors);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(bg);
        } else {
            setBackground(bg);
        }
    }

    /**
     * 设置导航栏背景透明
     */
    public void setTransparentBg() {
        setBackgroundImage(null);
        setBackgroundColor(Color.TRANSPARENT);
    }

    /**
     * 导航栏重置
     */
    public void reset() {
        setBackgroundImage(null);
    }

    /**
     * 设置导航栏背景图片
     *
     * @param drawable 图片
     */
    public void setBackgroundImage(Drawable drawable) {
        mBGView.setImageDrawable(drawable);
    }

    public void setShadowVisible(boolean visible) {
        mShadowView.setVisibility(visible ? VISIBLE : GONE);
    }


}
