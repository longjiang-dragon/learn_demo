package com.hujiang.mytest.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Date:  2019-12-11
 * Time:  00:16
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class ClassLoaderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ClassLoader loader = getClass().getClassLoader();
        while (loader != null) {
            Log.i("classLoader", loader.toString());
            loader = loader.getParent();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
