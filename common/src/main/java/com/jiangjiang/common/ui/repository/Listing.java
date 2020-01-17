package com.jiangjiang.common.ui.repository;

import androidx.lifecycle.LiveData;

/**
 * Date:  2020-01-16
 * Time:  20:05
 * Author: jianglong
 * -----------------------------
 * 普通页面
 */
public class Listing<T> {
    LiveData<NetworkState> refreshState;
    LiveData<T> resp;
}
