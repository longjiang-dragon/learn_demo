package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 16/4/15
 */
public class HookFragment extends Fragment {

	@BindView (R.id.webview)
	public WebView mWebView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		mWebView.setWebViewClient(new WebViewClient(){
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				view.loadUrl(url);
//				return true;
//			}
//		});
//		mWebView.getSettings().setLoadsImagesAutomatically(true);
//		mWebView.loadUrl("http://www.duiba.com.cn/autoLogin/autologin?uid=3691497&credits=690&sign=e6e201231592f2e139122a12ba9cd799&appKey=3NEM33Hnk1XK9dBbk1aU2cjYMYCV&vip=1&timestamp=1460948000129&");
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View _view = inflater.inflate(R.layout.hook_fragment_layout, container, false);
		ButterKnife.bind(this, _view);
		return _view;
	}
}
