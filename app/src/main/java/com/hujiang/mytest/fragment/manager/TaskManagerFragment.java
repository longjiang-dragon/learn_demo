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
        new TaskManagerBuild(getActivity().getApplication())
                .addNode(new LibInitiation1())
                .addNode(new LibInitiation2(),LibInitiation1.class.getSimpleName())
                .addNode(new LibInitiation3(),LibInitiation1.class.getSimpleName())
                .addNode(new LibInitiation4(),LibInitiation3.class.getSimpleName())
                .addNode(new LibInitiation5(),LibInitiation3.class.getSimpleName())
                .addNode(new LibInitiation6(),LibInitiation5.class.getSimpleName())
                .addNode(new LibInitiation7(),LibInitiation6.class.getSimpleName())
                .addNode(new LibInitiation8(),LibInitiation5.class.getSimpleName())
                .addNode(new LibInitiation9())

                .startInit();
    }
}
