package com.hujiang.mytest.fragment.algorithm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.fragment.algorithm.bfs.BFSAlgorithm;
import com.hujiang.mytest.fragment.algorithm.dfs.DFSAlgorithm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.OnClick;

/**
 * Date:  2020-01-10
 * Time:  11:52
 * Author: jianglong
 * -----------------------------
 * 常用算法
 */
public class AlgorithmFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.algorithm_fragment_layout, container, false);
    }

    @OnClick ({R.id.tv_bfs,R.id.tv_dfs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bfs:
                //树  广度
                BFSAlgorithm.BFSByQueue(null);
                break;
            case R.id.tv_dfs:
                //树 深度
                DFSAlgorithm.DFSByRecursion(null);
                break;
        }


    }
}
