package com.hujiang.mytest.fragment.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.fragment.manager.test.LibInitiation1;
import com.hujiang.mytest.fragment.manager.test.LibInitiation2;
import com.hujiang.mytest.fragment.manager.test.LibInitiation3;
import com.hujiang.mytest.fragment.manager.test.LibInitiation4;
import com.hujiang.mytest.fragment.manager.test.LibInitiation5;
import com.hujiang.mytest.fragment.manager.test.LibInitiation6;
import com.hujiang.mytest.fragment.manager.test.LibInitiation7;
import com.hujiang.mytest.fragment.manager.test.LibInitiation8;
import com.hujiang.mytest.fragment.manager.test.LibInitiation9;

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
//        test1();
        test2();
    }

    private void test1() {
        new TaskManagerBuild(getActivity().getApplication())
                .addLib(new LibInitiation1())
                .addLib(new LibInitiation2(), LibInitiation8.class.getSimpleName())//测试添加不存的父节点
                .addLib(new LibInitiation3())
                .addLib(new LibInitiation4())
                .addLib(new LibInitiation5())
                .addLib(new LibInitiation6())
                .addLib(new LibInitiation7())
                .addLib(new LibInitiation8(), LibInitiation7.class.getSimpleName(), LibInitiation2.class.getSimpleName(), LibInitiation3.class.getSimpleName())
                .addLib(new LibInitiation9(), LibInitiation4.class.getSimpleName(), LibInitiation1.class.getSimpleName())
                .startInit();
    }


    //测试添加多个相同的任务
    private void test2() {
        new TaskManagerBuild(getActivity().getApplication())
                .addLib(new LibInitiation1())
                .addLib(new LibInitiation1())
                .addLib(new LibInitiation1())
                .addLib(new LibInitiation1())
                .addLib(new LibInitiation2())
                .addLib(new LibInitiation3(),LibInitiation1.class.getSimpleName())
                .addLib(new LibInitiation2())
                .addLib(new LibInitiation5())
                .addLib(new LibInitiation5())
                .startInit();
    }
}
