package com.hujiang.mytest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hujiang.mytest.fragment.aidlFragment.R

/**
 * @author jianglong
 * @desc
 * @date 2018/3/29
 */
class KotlinLearn : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_kotlin_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printLog(describe(this))
        rangeSample()
        collectionFilter()
    }

    fun parseInt(str: String): Int? {
        //?表示可以返回空值
        return str.toIntOrNull()
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            //is运算符，判断是否某类型的一个实例，如果是，在检测后的分支中可以直接当作该类型使用。
            return obj.length;
        }
        return null
    }

    fun forSample() {
        val items = listOf("apple", "banana", "kiwifruit");
        for (item in items) {
            println(item)
        }

        for (index in items.indices) {
            println("item at $index  is ${items[index]}")
        }
    }

    private fun whileSample() {
        val items = listOf("apple", "banana", "kiwifruit");
        var index = 0;
        while (index < items.size) {
            println("item at $index  is ${items[index]}")
            index++
        }
    }


    //这个有点吊啊
    private fun describe(obj: Any): String =
            when (obj) {
                1 -> "one"
                "Hello" -> "Hello String"
                else -> "default"
            }


    //检测某个数字是否在指定区间
    private fun inSample() {
        val x = 1;
        val y = 10;
        if (x in 1..y + 1) {
            printLog("kotlin")
        }
    }

    private fun rangeSample() {
        val i = 0;
        for (i in 1..10 step 2) {
            printLog(i);
        }
        printLog("-------------------")

        for (i in 1 until 10) {

            printLog(i);
        }
    }

    private fun collectionFilter() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits.filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach {
                    printLog(it)
                }
    }


    private fun printLog(msg: Any) {
        Log.i("kotlin_msg", msg.toString())
    }

    class Person public constructor(name: String) {
        constructor(name: String, parent: Person) : this(name) {
        }
    }
}