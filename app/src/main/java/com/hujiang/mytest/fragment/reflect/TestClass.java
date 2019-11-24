package com.hujiang.mytest.fragment.reflect;

import java.util.List;

import androidx.lifecycle.Observer;

/**
 * Date:  2019-11-16
 * Time:  17:28
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class TestClass {
    //没有下界
    private List<? extends UserInfo> strList;
//    //	没有指定的话，上边界默认是 Object ,下边界是 	String
//    private List<? super String> b;
////    Set<String> set1;
//    Class<?> clz;
//    List<UserInfo> list;
    private int count;

    public Observer<List<? extends UserInfo>> getStrList() {
        return null;
    }

    public int getCount() {
        return count;
    }
}
