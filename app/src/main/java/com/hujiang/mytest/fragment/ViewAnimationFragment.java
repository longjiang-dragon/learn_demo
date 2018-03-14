package com.hujiang.mytest.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hujiang.mytest.fragment.aidlFragment.R;

/**
 * @author jianglong
 * @desc http://blog.csdn.net/yanzi1225627/article/details/47850471  关于属性动画和view动画
 * @date 2018/1/13
 */

public class ViewAnimationFragment extends Fragment implements View.OnClickListener {
    private TextView mTextView1, mTextView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_animation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mTextView1 = (TextView) view.findViewById(R.id.tv_1);
        this.mTextView1.setOnClickListener(this);
        this.mTextView2 = (TextView) view.findViewById(R.id.tv_2);
        this.mTextView2.setOnClickListener(this);
    }

    /**
     * 1，setTranslationX改变了view的位置，但没有改变view的LayoutParams里的margin属性；
     * 2，它改变的是android:translationX 属性，也即这个参数级别是和margin平行的。
     */

    @Override
    public void onClick(View view) {
        Log.i("ViewAnimationFragment", "TranslationX===" + view.getTranslationX());
        getLocationInWindow(view);//获取left和top值
        getLocationOnScreen(view);//获取left和top值
        getLocalVisibleRect(view);//相对于view自身
        getGlobalVisibleRect(view);//相对于整个屏幕

        Log.i("ViewAnimationFragment", "getBottom===" + view.getBottom());
    }

    private void getGlobalVisibleRect(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        Log.i("ViewAnimationFragment", "getGlobalVisibleRect===" + rect.left + "   " + rect.top + "  " + rect.right + "  " + rect.bottom);

    }

    private void getLocalVisibleRect(View view) {
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        Log.i("ViewAnimationFragment", "getLocalVisibleRect===" + rect.left + "   " + rect.top + "  " + rect.right + "  " + rect.bottom);

    }

    private void getLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        Log.i("ViewAnimationFragment", "getLocationOnScreen===" + location[0] + "   " + location[1]);
    }

    private void getLocationInWindow(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        Log.i("ViewAnimationFragment", "getLocationInWindow===" + location[0] + "   " + location[1]);

    }
}
