package com.jiangjiang.common.ui.helper;

import android.app.Activity;

import com.jiangjiang.common.R;
import com.jiangjiang.common.widget.LoadingProgressDialog;

import androidx.annotation.StringRes;

/**
 * 页面通用的dialog
 */
public class DialogHelperImpl implements DialogHelper {
    protected Activity mActivity;
    protected LoadingProgressDialog mLoadingDialog;

    public DialogHelperImpl(Activity pActivity) {
        mActivity = pActivity;
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog(true, R.string.default_loading_message);
    }

    @Override
    public void dismissLoadingDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showLockableLoadingDialog(@StringRes int msgId) {
        showLoadingDialog(false, msgId);

    }

    public boolean isLoadingDialogShowing() {
        return mLoadingDialog != null && mLoadingDialog.isShowing();
    }

    @Override
    public void showLockableLoadingDialog() {
        showLoadingDialog(false, R.string.default_loading_message);

    }

    @Override
    public void showLoadingDialog(boolean isCancelable, @StringRes int msgId) {
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingProgressDialog(mActivity, msgId);
        }
        mLoadingDialog.setCancelable(isCancelable);
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void showLoadingDialog(@StringRes int msgId) {
        showLoadingDialog(true, msgId);
    }
}
