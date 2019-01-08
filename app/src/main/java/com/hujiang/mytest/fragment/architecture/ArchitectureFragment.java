//package com.hujiang.mytest.fragment.architecture;
//
//import android.arch.lifecycle.LifecycleFragment;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.hujiang.mytest.fragment.aidlFragment.R;
//
///**
// * @author yuefeng
// * @desc 适用google新框架
// * @date 2017/9/7
// */
//public class ArchitectureFragment extends LifecycleFragment {
//    private static final String KEY_USER_ID = "USER_ID";
//    UserProfileViewModel userViewModel;
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        String userId = getArguments().getString(KEY_USER_ID);
//        userViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
//        userViewModel.setUserId(userId);
//
//        userViewModel.getName().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String name) {
//
//            }
//        });
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.architecture_layout, container);
//    }
//}
