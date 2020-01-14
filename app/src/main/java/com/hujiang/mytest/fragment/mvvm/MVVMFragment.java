package com.hujiang.mytest.fragment.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.fragment.mvvm.google.reddit.ui.MVVMMainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Date:  2020-01-13
 * Time:  17:52
 * Author: jianglong
 * -----------------------------
 * mvvm测试
 */
public class MVVMFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        return inflater.inflate(R.layout.fragment_mvvm_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick ({R.id.btn_mvvm_test})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mvvm_test:
                MVVMMainActivity.Companion.launch(getContext());
                break;
        }

    }
}
