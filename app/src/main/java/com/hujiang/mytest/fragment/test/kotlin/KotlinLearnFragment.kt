package com.hujiang.mytest.fragment.test.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hujiang.mytest.fragment.aidlFragment.R

/**
 * @author jianglong
 * @desc
 * @date 2018/3/29
 */
class KotlinLearnFragment @SuppressLint("ValidFragment")
private constructor() : Fragment() {
    val title: String? = null


    companion object {
        private val ARG_PARAM = "extra_key"
        public fun newInstance(name: String): KotlinLearnFragment {
            val fragment = KotlinLearnFragment()
            val args = Bundle()
            args.putString(ARG_PARAM, name)
            fragment.arguments = args
            return fragment
        }
    }

    init {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_kotlin_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        printLog(describe(this))
//        rangeSample()
//        collectionFilter()
        lambdaCode()
        nullSafe();
    }

    private fun nullSafe() {
        //?:如果左侧表达式非空，则返回左则表达式值，否则返回右侧表达式值
        val b=title?.length?:-1;
    }

    fun <T, R> Collection<T>.fold(
            initial: R,
            combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }


    private fun lambdaCode() {
        val items = listOf(1, 2, 4, 45, 4)
        items.fold(0, { acc: Int, i: Int ->
            val resut = acc + i
            resut
        })

    }

    fun parseInt(str: String): Int? {
        //?:表示可以返回空值
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

    interface MyInterface {
        //接口中的属性要么是提象的，要么是提借访问器实现的
        val prop: Int  //抽象的
        val propertyWithImplementation: String
            get() = "aaa"

        fun foo() {
            //接口中可以有方法的实现,这java是不能有的
        }

    }

    class Child : MyInterface {
        override val prop: Int = 29 //所以这里需要有实现
    }

    ///////////////扩展声明为成员///////////////
    open class D {
    }

    class D1 : D() {}
    open class C {
        open fun D.foo() {
            println("D.foo in C")
        }

        open fun D1.foo() {}
        fun caller(d: D) {
            println("D1.foo in C")
        }
    }

    class C1 : C() {
        override fun D.foo() {
            println("D.foo in c1")
        }

        override fun D1.foo() {
            println("D1.foo in c1")
        }
    }

    fun testCode1() {
    }


}