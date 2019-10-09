package com.kevin.nativedemo

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * Created by Kevin on 2019-10-09<br/>
 * Describe:<br/>
 */
class ViewPagerAdapter(
    var fm: FragmentManager,
    var tabList: MutableList<String>,
    var fragment: MutableList<Fragment>,
    var context: Context
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return  fragment[position]
    }

    override fun getCount(): Int {
        return tabList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabList[position]
    }

}