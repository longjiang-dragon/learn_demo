//package com.jiangjiang.common.ui.helper;
//
//
//import android.view.View;
//
//
//import androidx.annotation.DrawableRes;
//import androidx.annotation.LayoutRes;
//
///**
// * Date:  2020-01-19
// * Time:  16:14
// * Author: jianglong
// * -----------------------------
// * 通过UI处理逻辑
// */
//public interface UIHelper {
//
//    void showErrorView(RestError pRestError, boolean pIsUseNewUI);
//
//    void showErrorView( String pErrorMes, @DrawableRes int pErrorIconRes);
//
//
//    /**
//     * @param pErrorMes 异常消息
//     *                  下一版本把异常统一下（消息的行数,现在有显示一行的，也有两行的）
//     *                  这里再加一个参数，DrawableRes
//     */
//    void showErrorView(RestError pErrorMes);
//
//    /**
//     * 显示页面内进度条
//     */
//    void showInPageProgressView(boolean isUseNewUI);
//
//    void showInPageProgressView();
//
//
//    void commonExceptionDispose(RestError pRestError);
//
//    void showContent();
//
//    /**
//     * @param pExceptionView 错误提示的layout
//     * @param pErrorMes      错误提示文字
//     * @param pErrorIconRes  错误提示图片
//     */
//    void showErrorView(View pExceptionView, String pErrorMes, @DrawableRes int pErrorIconRes);
//
//    /**
//     * @param pExceptionLayoutId 错误提示的layout id(此layout中的控件id有特殊要求，符合才能显示)
//     * @param pErrorMes          错误提示文字
//     * @param pErrorIconRes      错误提示图片
//     */
//    void showErrorView(@LayoutRes int pExceptionLayoutId, String pErrorMes, @DrawableRes int pErrorIconRes);
//
//    /**
//     * @param pExceptionView 错误提示的layout
//     */
//    void showErrorView(View pExceptionView);
//
//
//    void setActivityBackground(String pBGUrl, View contentView);
//
//    //获取content view 中的scrollView
//    View getSlidingViewInContentView();
//
//    void onActivityDestroy();
//
//}
