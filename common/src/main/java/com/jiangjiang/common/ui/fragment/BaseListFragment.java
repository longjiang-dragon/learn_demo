package com.jiangjiang.common.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jiangjiang.common.R;
import com.jiangjiang.common.ui.helper.DialogHelper;
import com.jiangjiang.common.ui.helper.DialogHelperImpl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Date:  2020-01-19
 * Time:  16:25
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public abstract class BaseListFragment extends Fragment implements DialogHelper {

    protected FrameLayout rootView;
    private DialogHelper mDialogHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (FrameLayout) inflater.inflate(R.layout.common_base_fragment_layout, container, false);
        getLayoutInflater().inflate(getLayoutRes(), rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDialogHelper();
        initView();
    }


    protected abstract int getLayoutRes();

    protected void initView() {

    }

    protected void initDialogHelper() {
        mDialogHelper = new DialogHelperImpl(getActivity());
    }


    @Override
    public void showLoadingDialog() {
        mDialogHelper.showLoadingDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        mDialogHelper.dismissLoadingDialog();
    }

    @Override
    public void showLockableLoadingDialog(int msgId) {
        mDialogHelper.showLockableLoadingDialog(msgId);
    }

    @Override
    public void showLockableLoadingDialog() {
        mDialogHelper.showLockableLoadingDialog();
    }

    @Override
    public void showLoadingDialog(boolean isCancelable, int msgId) {
        mDialogHelper.showLoadingDialog(isCancelable, msgId);
    }

    @Override
    public void showLoadingDialog(int msgId) {
        mDialogHelper.showLoadingDialog(msgId);
    }

    @Override
    public boolean isLoadingDialogShowing() {
        return mDialogHelper.isLoadingDialogShowing();
    }
}
