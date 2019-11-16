package com.hujiang.mytest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.gson.Gson


/**
 * Date:  2019-05-14
 * Time:  20:01
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
class GSONLearnFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val json_str = String(resources.assets.open("json_string.json").readBytes());
        var user = Gson().fromJson<User>(json_str);
        Log.i("GSONLearnFragment", user.toString())
    }

    //inline 的作用就是将函数插入到被调用处
    inline fun <reified T : Any> Gson.fromJson(json: String): T {
        return Gson().fromJson(json, T::class.java)
    }
}

data class User(val appVersion: String, val man: Man, val dataList: List<String>);
data class Man(val name: String, val age: Int,val test_boolean:Boolean);

