package com.hujiang.mytest.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.widget.CircleImageExtendsDrawable;
import com.hujiang.mytest.widget.RoundImageDrawable;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yuefeng
 * @version 3.3.1
 * @date 15/12/26
 */
public class CustomDrawableFragment extends Fragment {
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView (R.id.iv_two)
    ImageView mIvTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_drawable_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bitmap _bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.eye);
        mIv.setImageDrawable(new RoundImageDrawable(_bitmap));
        mIvTwo.setImageDrawable(new CircleImageExtendsDrawable(_bitmap));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
