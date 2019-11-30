package com.hujiang.mytest.fragment.reflect;

/**
 * Date:  2019-11-16
 * Time:  17:37
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class UserInfo {
    private String mBookName;
    public int mPageCount;

    private String getBookName(int index) {
        return "getBookName 调用";
    }

}
