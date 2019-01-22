package com.hujiang.mytest;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hujiang.mytest.fragment.aidlFragment.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author jianglong
 * @version 3.4.3
 * @desc
 * @date 2017/10/30
 */
public class BigImageActivity extends AppCompatActivity {
    public static void launch(Context pContext) {
        Intent starter = new Intent(pContext, BigImageActivity.class);
        ((Activity)pContext).startActivity(starter,ActivityOptions.makeSceneTransitionAnimation((Activity)pContext).toBundle());
    }

    private static final String IMAGE_URL = "http://pic1.win4000.com/wallpaper/f/541f9b4dce419.jpg";
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        mImageView = (ImageView) findViewById(R.id.iv);
        Glide.with(this).load(IMAGE_URL).into(mImageView);
    }

}
