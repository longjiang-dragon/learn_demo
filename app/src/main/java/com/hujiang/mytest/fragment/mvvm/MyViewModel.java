package com.hujiang.mytest.fragment.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/**
 * Date:  2020-01-13
 * Time:  17:56
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> name;

    public LiveData<String> getName(){
        if (null==name){
            name=new MutableLiveData<>();
            initName();
        }
        return name;
    }
    private void initName() {
        name.setValue("你好呀");
    }
}
