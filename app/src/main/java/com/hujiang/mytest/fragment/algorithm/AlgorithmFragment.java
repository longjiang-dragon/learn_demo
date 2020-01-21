package com.hujiang.mytest.fragment.algorithm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;
import com.hujiang.mytest.fragment.algorithm.avl.AVLTree;
import com.hujiang.mytest.fragment.algorithm.bfs.BFSAlgorithm;
import com.hujiang.mytest.fragment.algorithm.dfs.DFSAlgorithm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick ({R.id.tv_bfs, R.id.tv_dfs, R.id.avl_tree,R.id.avl_print})
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
            case R.id.avl_tree:
                //平衡二叉树
                AVLTree<String, Integer> tree = new AVLTree();
                tree.add("1", 1);
                tree.add("2", 1);
                tree.add("3", 1);
                break;
            case R.id.avl_print:
                //回形打印二维数组
                int [][] arr = new int[4][5];
                travel(arr, 4, 5, 0, 0);
                print(arr, 4, 5);
                break;
        }
    }
    /**
     *
     * 0   1   2   3
     * 13  14  15  4
     * 12  19  16  5
     * 11  18  17  6
     * 10  9   8   7
     *
     * @param arr  数组
     * @param row  行
     * @param col  列
     * @param level 层数
     * @param count 计数
     */
    public void travel(int [][] arr, int row, int col, int level, int count) {
        //递归出口
        if (row - 2 * level == 0 || col - 2 * level == 0) {
            return;
        }

        if (row - 2 * level == 1) {
            //只有一行了,不用处理四条边的逻辑
            for (int i = level; i < col -level; ++i) {
                arr[level][i] = count++;
            }
            return;
        }

        if (col - 2 * level == 1) {
            //只有一列的场景,不用处理四条边的逻辑
            for (int i = level; i < row -level; ++i) {
                arr[i][level] = count++;
            }
            return;
        }

        //上边
        for (int i = level; i < col - level; ++i) {
            arr[level][i] = count++;
        }

        //右边
        for (int i = level + 1; i < row - 1 - level; ++i) {
            arr[i][col - 1 - level] = count++;
        }
        //下边
        for (int i = col - 1 - level; i >= level; --i){
            arr[row - 1- level][i] = count++;
        }
        //左边
        for (int i = row - 2 - level; i >= level+1; --i){
            arr[i][level] = count++;
        }
        // 递归
        travel(arr, row, col, level + 1, count);
    }


    /**
     * 打印
     * @param arr
     * @param row
     * @param col
     */
    public void print(int[][] arr, int row, int col){
        for (int i = 0; i < row; ++i){
            for (int j = 0; j < col; ++j){
                System.out.print(arr[i][j] + "    ");
            }
            System.out.println();
        }
    }

}
