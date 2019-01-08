package com.hujiang.mytest.fragment.open.udid.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 2017/2/15
 */
public class OpenUDIDFragment extends Fragment {
    @BindView (R.id.tv_get_udid)
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.open_udid_fragment, container, false);
        return _view;
    }

    @OnClick(R.id.tv_get_udid)
    public void onClick(View view) {
        OpenUDID_manager.sync(getContext());
        if (OpenUDID_manager.isInitialized()) {
            Log.i("udid", OpenUDID_manager.getOpenUDID());
        }
    }

}
