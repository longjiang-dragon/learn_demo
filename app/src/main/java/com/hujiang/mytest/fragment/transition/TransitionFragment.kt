package com.hujiang.mytest.fragment.transition

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.hujiang.mytest.fragment.aidlFragment.R
import kotlinx.android.synthetic.main.fragment_basic_transitions.*

/**
 * author：jianglong on  2019/1/22
 * @desc
 */
class TransitionFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basic_transitions, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        begin.setOnClickListener(this)
        reset.setOnClickListener(this)
        AddTarget.setOnClickListener(this)
        custom_transition.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when {
            v!!.id == R.id.begin -> {
                val scene = Scene.getSceneForLayout(rootView, R.layout.scene2, this.context!!);
                TransitionManager.go(scene, ChangeScroll())
            }
            v.id == R.id.reset -> {
                val scene2 = Scene.getSceneForLayout(rootView, R.layout.scene1, this.context!!);
                TransitionManager.go(scene2, ChangeBounds())
            }
            v.id == R.id.AddTarget -> {
                val scene3 = Scene.getSceneForLayout(rootView, R.layout.scene1, this.context!!);
                val transitionSet = TransitionSet()
                transitionSet.addTransition(ChangeBounds()).addTarget(R.id.image1)
                TransitionManager.go(scene3, transitionSet)
            }
            v.id == R.id.custom_transition -> {
                Handler().post {
                    TransitionManager.beginDelayedTransition(view_custom, ChangeColor())
                    view_custom.setBackgroundColor(Color.BLUE)
                }
            }
        }
    }
}