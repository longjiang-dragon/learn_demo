package com.hujiang.mytest.fragment.jet.pack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hujiang.mytest.fragment.aidlFragment.R

/**
 * author：jianglong on  2019/1/15
 * @desc
 */
class JetPackTestFragment : Fragment(), View.OnClickListener {


    private lateinit var mNameViewModel: NameViewModel
    lateinit var textView1: TextView;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_jet_pack, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView1 = view.findViewById(R.id.tv_1)
        view.setOnClickListener(this)
        mNameViewModel = ViewModelProviders.of(this).get(NameViewModel::class.java)
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            textView1.text = newName;
        }
        mNameViewModel.currentName.observe(this, nameObserver)
    }

    override fun onClick(v: View?) {
        mNameViewModel.currentName.postValue("fadfdfd")
    }

}