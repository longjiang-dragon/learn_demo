package com.hujiang.mytest.fragment.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.fragment.manager.test.LibInitiation2;
import com.hujiang.mytest.fragment.manager.test.LibInitiation4;
import com.hujiang.mytest.fragment.manager.test.LibInitiation6;
import com.hujiang.mytest.fragment.manager.test.LibInitiation8;
import com.hujiang.mytest.fragment.manager.test.LibInitiationMain1;
import com.hujiang.mytest.fragment.manager.test.LibInitiationMain3;
import com.hujiang.mytest.fragment.manager.test.LibInitiationMain5;
import com.hujiang.mytest.fragment.manager.test.LibInitiationMain7;
import com.hujiang.mytest.fragment.manager.test.LibInitiationMain9;

import androidx.fragment.app.Fragment;

/**
 * @author jianglong
 * @desc
 * @date 2018/3/30
 */
public class TaskManagerFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_task_manager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        test1();
//        test2();
    }

    /**
     * 1、    一个任务添加多个依赖
     * 2、异常
     */

    private void test1() {
        new TaskManagerBuild(getActivity().getApplication())
                .addLib(new LibInitiationMain1())
                .addLib(new LibInitiation2(), LibInitiation8.class.getSimpleName())//测试添加不存的父节点
                .addLib(new LibInitiationMain3())
                .addLib(new LibInitiation4())
                .addLib(new LibInitiationMain5(), LibInitiation4.class.getSimpleName(), LibInitiationMain3.class.getSimpleName())
                .addLib(new LibInitiation6())
                .addLib(new LibInitiationMain7(), LibInitiationMain3.class.getSimpleName())
                .addLib(new LibInitiation8(), LibInitiation2.class.getSimpleName())
                .addLib(new LibInitiationMain9(), LibInitiation2.class.getSimpleName())
                .startInit();
    }


    //测试添加多个相同的任务
    private void test2() {
        new TaskManagerBuild(getActivity().getApplication())
                .addLib(new LibInitiationMain1())
                .addLib(new LibInitiationMain1())
                .addLib(new LibInitiationMain1())
                .addLib(new LibInitiationMain1())
                .addLib(new LibInitiation2())
                .addLib(new LibInitiationMain3(), LibInitiationMain1.class.getSimpleName())
                .addLib(new LibInitiation2())
                .addLib(new LibInitiationMain5())
                .addLib(new LibInitiationMain5())
                .startInit();
    }
}
