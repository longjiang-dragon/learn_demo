package com.hujiang.mytest.fragment.architecture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @author yuefeng
 * @version 3.4.3
 * @desc
 * @date 2017/9/7
 */
public class UserProfileViewModel extends ViewModel {
    private String userId;
    private LiveData<String> name;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {

        return userId;
    }

    public LiveData<String> getName() {
        return name;
    }
}
