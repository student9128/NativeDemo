package com.kevin.nativedemo

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_theme.*
import kotlin.math.floor
import kotlin.math.min

/**
 * Created by Kevin on 2019-10-09<br/>
 * Describe:<br/>
 * 参考链接:
 * <a href="https://stackoverflow.com/questions/33865107/how-to-change-the-color-of-the-toolbar-and-status-bar-colors-according-to-image">
 * https://stackoverflow.com/questions/33865107/how-to-change-the-color-of-the-toolbar-and-status-bar-colors-according-to-image</a>
 *
 */
class ThemeActivity : AppCompatActivity(), TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {

    private var mTabList = mutableListOf<String>()
    private var mFragments = mutableListOf<Fragment>()
    private var mAdapter: ViewPagerAdapter? = null

    companion object {
        val COLOR_PRIMARY = listOf<Int>(
            R.color.colorPrimary,
            R.color.lightBlue,
            R.color.red,
            R.color.purple,
            R.color.gray,
            R.color.yellow
        )
        val COLOR_PRIMARY_DARK = listOf<Int>(
            R.color.colorPrimaryDark,
            R.color.lightBlueDark,
            R.color.redDark,
            R.color.purpleDark,
            R.color.grayDark,
            R.color.yellowDark
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        initTabList()
        initFragments()
        mAdapter = ViewPagerAdapter(supportFragmentManager, mTabList, mFragments, this)
        view_pager.adapter = mAdapter
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
        tab_layout.addOnTabSelectedListener(this)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("Theme", "position=$position,positionOffset=$positionOffset")
                var p: Int = floor((position + positionOffset).toDouble()).toInt()
                var po = (((position + positionOffset) % 1) + 1) % 1
                updateBackground(p, po)
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    private fun initFragments() {
        mFragments.clear()
        var b1 = Bundle()
        b1.putInt("backgroundColor", R.color.colorPrimary)
        b1.putInt("textColor", R.color.white)
        mFragments.add(TabFragment.newInstance(b1))
        var b2 = Bundle()
        b2.putInt("backgroundColor", R.color.lightBlue)
        b2.putInt("textColor", R.color.white)
        mFragments.add(TabFragment.newInstance(b2))
        var b3 = Bundle()
        b3.putInt("backgroundColor", R.color.red)
        b3.putInt("textColor", R.color.white)
        mFragments.add(TabFragment.newInstance(b3))
        var b4 = Bundle()
        b4.putInt("backgroundColor", R.color.purple)
        b4.putInt("textColor", R.color.white)
        mFragments.add(TabFragment.newInstance(b4))
        var b5 = Bundle()
        b5.putInt("backgroundColor", R.color.gray)
        b5.putInt("textColor", R.color.white)
        mFragments.add(TabFragment.newInstance(b5))
        var b6 = Bundle()
        b6.putInt("backgroundColor", R.color.yellow)
        b6.putInt("textColor", R.color.white)
        mFragments.add(TabFragment.newInstance(b6))
    }

    private fun initTabList() {
        mTabList.clear()
        mTabList.add("Tab1")
        mTabList.add("Tab2")
        mTabList.add("Tab3")
        mTabList.add("Tab4")
        mTabList.add("Tab5")
        mTabList.add("Tab6")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    fun updateBackground(position: Int, positionOffset: Float) {
        @ColorInt
        var background: Int
        @ColorInt
        var backgroundNext: Int
        @ColorInt
        var backgroundDark: Int
        @ColorInt
        var backgroundDarkNext: Int
        if (position == mAdapter!!.count) {
            background = Color.TRANSPARENT
            backgroundNext = Color.TRANSPARENT
            backgroundDark = Color.TRANSPARENT
            backgroundDarkNext = Color.TRANSPARENT
        } else {
            background = ContextCompat.getColor(this, COLOR_PRIMARY[position])
            backgroundNext = ContextCompat.getColor(
                this,
                COLOR_PRIMARY[(min(position + 1, mAdapter!!.count - 1))]
            )
            backgroundDark = ContextCompat.getColor(this, COLOR_PRIMARY_DARK[position])
            backgroundDarkNext = ContextCompat.getColor(
                this,
                COLOR_PRIMARY_DARK[(min(position + 1, mAdapter!!.count - 1))]
            )
        }
        var colorEvaluator = ArgbEvaluator()
        background = colorEvaluator.evaluate(positionOffset, background, backgroundNext) as Int
        backgroundDark =
            colorEvaluator.evaluate(positionOffset, backgroundDark, backgroundDarkNext) as Int
        toolbar.setBackgroundColor(background)
        window.statusBarColor = backgroundDark
    }

}