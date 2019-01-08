package com.hujiang.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @author jianglong
 * @version 3.4.3
 * @desc
 * @date 2017/10/30
 */
public class BigImageActivity extends FragmentActivity {
    public static void launch(Context pContext) {
        Intent starter = new Intent(pContext, BigImageActivity.class);
        pContext.startActivity(starter);
    }

    private static final String IMAGE_URL = "http://pic1.win4000.com/wallpaper/f/541f9b4dce419.jpg";
    private ImageView mImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_big_image);
        mImageView = (ImageView) findViewById(R.id.iv);
//        Glide.with(this).load(IMAGE_URL).into(mImageView);
    }
}
