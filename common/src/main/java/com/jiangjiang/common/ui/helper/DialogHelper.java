package com.jiangjiang.common.ui.helper;


import androidx.annotation.StringRes;

/**
 *页面通用的dialog
 */
public interface DialogHelper {
	void showLoadingDialog();

	void dismissLoadingDialog();

	void showLockableLoadingDialog(@StringRes int msgId);

	void showLockableLoadingDialog();

	void showLoadingDialog(boolean isCancelable, @StringRes int msgId);

	void showLoadingDialog(@StringRes int msgId);

	boolean isLoadingDialogShowing();

}
