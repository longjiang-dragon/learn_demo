//package com.jiangjiang.common.ui.helper;
//
//import android.app.Activity;
//import android.graphics.drawable.AnimationDrawable;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.jiangjiang.common.R;
//import com.jiangjiang.common.ui.activity.BaseActivity;
//
//import androidx.annotation.DrawableRes;
//import androidx.annotation.LayoutRes;
//
///**
// * @author yuefeng
// * @version 3.4.3
// * @desc 和fragment的一些共用处理
// * @date 16/3/15
// */
//public class UIHelperImpl implements UIHelper {
//    private Activity mActivity;
//    protected LayoutInflater mLayoutInflater;
//    protected LinearLayout mContainerLayout;
//    private ExceptionDispose mExceptionDispose;
//    private View mContentView;
//    private AnimationDrawable mLoadingAnimation;
//
//
//    public UIHelperImpl(LinearLayout pContainerLayout, Activity pActivity, View pContentView) {
//        mContainerLayout = pContainerLayout;
//        mLayoutInflater = pActivity.getLayoutInflater();
//        mActivity = pActivity;
//        this.mContentView = pContentView;
//    }
//
//    @Override
//    public void showErrorView(RestError pRestError, boolean pIsUseNewUI) {
//        if (null == pRestError) return;
//        stopLoadingAnimator();
//        mContainerLayout.removeAllViews();
//        mContainerLayout.addView(getErrorViewLayoutRes(pRestError, pIsUseNewUI));
//    }
//
//    private View getErrorViewLayoutRes(RestError pRestError, boolean pIsUseNewUI) {
//        switch (pRestError.getErrorType()) {
//            case ExceptionUtil.EXCEPTION_DIRTY_DATA:
//            case ExceptionUtil.SUCCESS:
//            case ExceptionUtil.EXCEPTION_BUSINESS:
//
//                return generateDirtyDataExceptionView(pRestError, pIsUseNewUI);
//            default:
//                return generateNetworkExceptionVieW(pRestError, pIsUseNewUI);
//        }
//    }
//
//    private View generateDirtyDataExceptionView(RestError pRestError, boolean pIsUseNewUI) {
//        View _view = mLayoutInflater.inflate(R.layout.exception_dirty_data_layout, mContainerLayout, false);
//        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
//        return _view;
//    }
//
//
//    private View generateNetworkExceptionVieW(RestError pRestError, boolean pIsUseNewUI) {
//        View _view = mLayoutInflater.inflate(R.layout.common_network_error_page, mContainerLayout, false);
//        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
//        return _view;
//    }
//
//
//    @Override
//    public void showErrorView(@Nullable String pErrorMes, @DrawableRes int pErrorIconRes) {
//        stopLoadingAnimator();
//        mContainerLayout.removeAllViews();
//        mLayoutInflater.inflate(R.layout.common_network_error_page, mContainerLayout);
//        ((ImageView) mContainerLayout.findViewById(R.id.iv_exception_icon)).setImageResource(pErrorIconRes);
//        if (!TextUtils.isEmpty(pErrorMes)) {
//            ((TextView) mContainerLayout.findViewById(R.id.tv_exception_tip)).setText(pErrorMes);
//        }
//
//    }
//
//    @Override
//    public void showErrorView(RestError pRestError) {
//        if (null == pRestError) return;
//        stopLoadingAnimator();
//        mContainerLayout.removeAllViews();
//        mContainerLayout.addView(getErrorViewLayoutRes(pRestError));
//
//    }
//
//    @Override
//    public void showInPageProgressView(boolean isUseNewUI) {
//        if (!isUseNewUI) {
//            showInPageProgressView();
//            return;
//        }
//        if (mContainerLayout.getChildCount() > 0) {
//            mContainerLayout.removeAllViews();
//        }
//        mLayoutInflater.inflate(R.layout.xk_new_ui_loading_view, mContainerLayout);
//        ImageView ivLoadingAnimation = (ImageView) mContainerLayout.findViewById(R.id.iv_loading);
//        if (ivLoadingAnimation == null) return;
//        mLoadingAnimation = (AnimationDrawable) ivLoadingAnimation.getDrawable();
//        if (mLoadingAnimation != null) {
//            mLoadingAnimation.start();
//        }
//
//    }
//
//    @Override
//    public void showInPageProgressView() {
//        //兼容新两老两套progress view
//        if (isUsePictureBackground()) {
//            showInPageProgressView(true);
//            return;
//        }
//
//        if (mContainerLayout.getChildCount() > 0) {
//            mContainerLayout.removeAllViews();
//        }
//        mLayoutInflater.inflate(R.layout.xk_loading_view, mContainerLayout);
//    }
//
//    private boolean isUsePictureBackground() {
//        if (mActivity instanceof BaseActivity) {
//            return ((BaseActivity) mActivity).usePictureBackground();
//        }
//        return false;
//
//
//    }
//
//    @Override
//    public void commonExceptionDispose(RestError pRestError) {
//        if (null == pRestError) return;
//        if (null == mExceptionDispose) {
//            this.mExceptionDispose = new ExceptionDispose(mActivity);
//        }
//        this.mExceptionDispose.dispose(pRestError);
//
//    }
//
//    @Override
//    public void showContent() {
//        stopLoadingAnimator();
//        if (mContainerLayout.getChildCount() > 0) {
//            mContainerLayout.removeAllViews();
//        }
//        mContainerLayout.addView(mContentView);
//
//
//    }
//
//
//    @Override
//    public void showErrorView(View pViewGroupParent, String pErrorMes, @DrawableRes int pErrorIconRes) {
//        if (null == pViewGroupParent) return;
//        stopLoadingAnimator();
//        mContainerLayout.removeAllViews();
//        mContainerLayout.addView(pViewGroupParent);
//        ImageView _imageView = (ImageView) mContainerLayout.findViewById(R.id.iv_exception_icon);
//        if (null != _imageView) {
//            _imageView.setImageResource(pErrorIconRes);
//        }
//        TextView _textView = (TextView) mContainerLayout.findViewById(R.id.tv_exception_tip);
//        if (null != _textView) {
//            _textView.setText(pErrorMes);
//        }
//
//    }
//
//    @Override
//    public void showErrorView(@LayoutRes int pExceptionLayoutId, String pErrorMes, @DrawableRes int pErrorIconRes) {
//        showErrorView(mLayoutInflater.inflate(pExceptionLayoutId, mContainerLayout, false), pErrorMes, pErrorIconRes);
//    }
//
//    @Override
//    public void showErrorView(View pExceptionView) {
//        if (null == pExceptionView) return;
//        stopLoadingAnimator();
//        mContainerLayout.removeAllViews();
//        mContainerLayout.addView(pExceptionView);
//    }
//
//
//    private View getErrorViewLayoutRes(RestError pRestError) {
//        switch (pRestError.getErrorType()) {
//            case ExceptionUtil.EXCEPTION_DIRTY_DATA:
//            case ExceptionUtil.SUCCESS:
//            case ExceptionUtil.EXCEPTION_BUSINESS:
//
//                return generateDirtyDataExceptionView(pRestError);
//            default:
//                return generateNetworkExceptionVieW(pRestError);
//        }
//    }
//
//    private View generateNetworkExceptionVieW(RestError pRestError) {
//        View _view = mLayoutInflater.inflate(R.layout.common_network_error_page, mContainerLayout, false);
//        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
//        return _view;
//    }
//
//    private View generateDirtyDataExceptionView(RestError pRestError) {
//        View _view = mLayoutInflater.inflate(R.layout.exception_dirty_data_layout, mContainerLayout, false);
//        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
//        return _view;
//    }
//
//
//    @Override
//    public void setActivityBackground(String pBGUrl, View contentView) {
//        contentView.setBackgroundResource(R.drawable.core_activity_back_default);
//        ImageConfig _imageConfig = new ImageConfig.Builder()
//                .placeholder(R.drawable.core_activity_back_default)
//                .errorImage(R.drawable.core_activity_back_default)
//                .build();
//        XKImageLoader.with(mActivity, _imageConfig).setBackground(pBGUrl, contentView);
//    }
//
//
//    @Override
//    public View getSlidingViewInContentView() {
//        return null;
//    }
//
//    @Override
//    public void onActivityDestroy() {
//        stopLoadingAnimator();
//        mContainerLayout.addView(mContentView);
//        mContainerLayout.removeAllViews();
//
//    }
//
//    private void stopLoadingAnimator() {
//        stopLottieAnimation();
//        stopBlackAnimation();
//    }
//
//    //黑色UI使用
//    private void stopBlackAnimation() {
//        if (mLoadingAnimation != null && mLoadingAnimation.isRunning()) {
//            mLoadingAnimation.stop();
//            mLoadingAnimation = null;
//        }
//    }
//
//    //蓝色ui使用
//    private void stopLottieAnimation() {
//        LottieAnimationView lottieAnimationView = (LottieAnimationView) mActivity.findViewById(R.id.lottie_animation);
//        if (null == lottieAnimationView) return;
//        lottieAnimationView.cancelAnimation();
//    }
//
//}
