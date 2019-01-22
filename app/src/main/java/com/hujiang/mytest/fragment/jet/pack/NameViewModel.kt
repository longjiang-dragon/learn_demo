package com.hujiang.mytest.fragment.jet.pack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * author：jianglong on  2019/1/15
 * @desc
 */
class NameViewModel : ViewModel() {
    // Create a LiveData with a String
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}