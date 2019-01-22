package com.hujiang.mytest.fragment.dynamic.animation

import android.os.Bundle
import android.view.*
import androidx.dynamicanimation.animation.*
import androidx.fragment.app.Fragment
import com.hujiang.mytest.fragment.aidlFragment.R
import kotlinx.android.synthetic.main.fragment_dynamic_animation_layout.*

/**
 * author：jianglong on  2019/1/15
 * @desc
 */
class DynamicAnimationFragment : Fragment(), View.OnClickListener {
//    var aa: String;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dynamic_animation_layout, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addGestureDetector()
        tv_spring.setOnClickListener(this);
        tv_fling.setOnClickListener(this)
        tv_bounce.setOnClickListener(this)
    }

    private fun addGestureDetector() {
        root_view.setOnTouchListener { _, event ->
            val detector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                    springClick(velocityY)
                    return true
                }
            })
            detector.onTouchEvent(event)
        }
    }


    override fun onClick(view: View?) {
        when (view) {
            tv_spring -> {
                springClick(1000f)
            }
            tv_fling -> {
                flingClick()
            }
            tv_bounce -> {
                bounceClick();
            }

        }
    }

    private fun bounceClick() {
        val scale = object : FloatPropertyCompat<View>("scaleX") {

            override fun getValue(view: View): Float {
                return view.scaleX
            }

            override fun setValue(view: View, value: Float) {
                view.scaleX = value
                view.scaleY = value
            }
        }
        val springAnimation = SpringAnimation(icon, scale)
        springAnimation.setStartVelocity(5f)
        val springForce = SpringForce()
        springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY//弹性阻尼
        springForce.stiffness = SpringForce.STIFFNESS_VERY_LOW  //生硬度
        springForce.finalPosition = icon.x
        springAnimation.spring = springForce
        springAnimation.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE
        springAnimation.start()

    }

    private fun flingClick() {
        FlingAnimation(icon, DynamicAnimation.X).apply {
            setStartVelocity(500f)
            friction = 0.5f
            start()
        }
    }

    private fun springClick(startVelocity: Float) {
        val springAnimation = SpringAnimation(icon, DynamicAnimation.X)
        springAnimation.setStartVelocity(startVelocity)
        val springForce = SpringForce()
        springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY//弹性阻尼
        springForce.stiffness = SpringForce.STIFFNESS_VERY_LOW  //生硬度
        springForce.finalPosition = icon.x
        springAnimation.spring = springForce
        springAnimation.start()

    }

}


