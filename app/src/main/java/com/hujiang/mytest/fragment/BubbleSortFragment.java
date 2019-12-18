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
public class BubbleSortFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int[] intArray = {433, 43, 234, 1, 35, 6, 6, 7, 7, 98, 33};
        int[] result = bubbleSort(intArray);
        for (int i : result) {
            Log.i(BubbleSortFragment.class.getCanonicalName(), i + " ");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private int[] bubbleSort(int[] arrInt) {
        if (arrInt.length <= 1) return arrInt;
        for (int i = 0; i < arrInt.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arrInt.length - i - 1; j++) {
                if (arrInt[j] > arrInt[j + 1]) {
                    //需要更换位置
                    int temp = arrInt[j];
                    arrInt[j] = arrInt[j + 1];
                    arrInt[j + 1] = temp;
                    flag = true;
                }
            }
            //没有数据交换，数组已经有序，退出排序
            if (!flag) break;
        }

        return arrInt;
    }
}
