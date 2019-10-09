package com.kevin.nativedemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Created by Kevin on 2019-10-09<br/>
 * Describe:<br/>
 */
class TabFragment : Fragment() {
    private var mContainer: LinearLayout? = null
    private var mText: TextView? = null

    companion object {
        fun newInstance(bundle: Bundle): TabFragment {
            var fragment = TabFragment()
            var b = Bundle()
            val bg = bundle.getInt("backgroundColor")
            val tc = bundle.getInt("textColor")
            b.putInt("backgroundColor", bg)
            b.putInt("textColor", tc)
            fragment.arguments = b
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        mContainer = view.findViewById(R.id.ll_container)
        mText = view.findViewById(R.id.tv_text)
        val bundle = arguments
        val bg = bundle!!.getInt("backgroundColor")
        val tc = bundle!!.getInt("textColor")
        mContainer!!.setBackgroundColor(ContextCompat.getColor(activity!!,R.color.white))
        mText!!.setTextColor(ContextCompat.getColor(activity!!,tc))
        return view
    }
}