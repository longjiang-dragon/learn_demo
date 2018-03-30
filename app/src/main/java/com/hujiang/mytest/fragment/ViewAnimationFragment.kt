package com.hujiang.mytest.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.hujiang.mytest.fragment.aidlFragment.R

/**
 * @author jianglong
 * @desc http://blog.csdn.net/yanzi1225627/article/details/47850471  关于属性动画和view动画
 * @date 2018/1/13
 */

class ViewAnimationFragment : Fragment(), View.OnClickListener {
    private var mTextView1: TextView? = null
    private var mTextView2: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_view_animation, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.mTextView1 = view!!.findViewById(R.id.tv_1) as TextView
        this.mTextView1!!.setOnClickListener(this)
        this.mTextView2 = view.findViewById(R.id.tv_2) as TextView
        this.mTextView2!!.setOnClickListener(this)
    }

    /**
     * 1，setTranslationX改变了view的位置，但没有改变view的LayoutParams里的margin属性；
     * 2，它改变的是android:translationX 属性，也即这个参数级别是和margin平行的。
     */

    override fun onClick(view: View) {
        Log.i("ViewAnimationFragment", "TranslationX===" + view.translationX)
        getLocationInWindow(view)//获取left和top值
        getLocationOnScreen(view)//获取left和top值
        getLocalVisibleRect(view)//相对于view自身
        getGlobalVisibleRect(view)//相对于整个屏幕

        Log.i("ViewAnimationFragment", "getBottom===" + view.bottom)
    }

    private fun getGlobalVisibleRect(view: View) {
        val rect = Rect()
        view.getGlobalVisibleRect(rect)
        Log.i("ViewAnimationFragment", "getGlobalVisibleRect===" + rect.left + "   " + rect.top + "  " + rect.right + "  " + rect.bottom)

    }

    private fun getLocalVisibleRect(view: View) {
        val rect = Rect()
        view.getLocalVisibleRect(rect)
        Log.i("ViewAnimationFragment", "getLocalVisibleRect===" + rect.left + "   " + rect.top + "  " + rect.right + "  " + rect.bottom)

    }

    private fun getLocationOnScreen(view: View) {
        val location = IntArray(2)
        view.getLocationInWindow(location)
        Log.i("ViewAnimationFragment", "getLocationOnScreen===" + location[0] + "   " + location[1])
    }

    private fun getLocationInWindow(view: View) {
        val location = IntArray(2)
        view.getLocationInWindow(location)
        Log.i("ViewAnimationFragment", "getLocationInWindow===" + location[0] + "   " + location[1])

    }
}
