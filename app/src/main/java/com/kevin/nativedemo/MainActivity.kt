package com.kevin.nativedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.animation.ValueAnimator
import android.animation.ObjectAnimator
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.Color
import android.os.Looper
import android.os.MessageQueue
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var oneToTwo: AnimatedColor? = null
    private var twoToThree: AnimatedColor? = null
    var statusBarView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        oneToTwo = AnimatedColor(
            ContextCompat.getColor(this, R.color.colorPrimary),
            ContextCompat.getColor(this, R.color.colorAccent)
        )
        twoToThree = AnimatedColor(
            ContextCompat.getColor(this, R.color.colorAccent),
            ContextCompat.getColor(this, R.color.colorPrimaryDark)
        )

        btn.setOnClickListener { animateStatusBar() }
        btnGradient.setOnClickListener {
            Utils.darkenStatusBar(this, R.color.green)
        }
        btnNext.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    NextActivity::class.java
                )
            )
        }
        btnTheme.setOnClickListener { startActivity(Intent(this@MainActivity,ThemeActivity::class.java)) }
        btnTest.setOnClickListener { startActivity(Intent(this@MainActivity,TestActivity::class.java)) }


//        Looper.myQueue().addIdleHandler(object : MessageQueue.IdleHandler {
//            override fun queueIdle(): Boolean {
//                initStatusBar()
//                window.decorView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
//                    override fun onLayoutChange(
//                        v: View?,
//                        left: Int,
//                        top: Int,
//                        right: Int,
//                        bottom: Int,
//                        oldLeft: Int,
//                        oldTop: Int,
//                        oldRight: Int,
//                        oldBottom: Int
//                    ) {
//                        initStatusBar()
//                    }
//
//                })
//                return false
//            }
//
//        })
    }

    /**
     * 设置状态栏颜色
     */
    fun setStatusBarColor() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#2196f3")
    }

    fun initStatusBar() {
        if (statusBarView == null) {
            val identifier = resources.getIdentifier("statusBarBackground", "id", "android")
            statusBarView = window.findViewById(identifier)
        }
        statusBarView!!.setBackgroundResource(R.drawable.gradient_drawable_two)
    }

    /**
     * http://myhexaville.com/2017/06/14/android-animated-status-bar-color/
     */
    private fun animateStatusBar() {
        val animator = ObjectAnimator.ofFloat(0f, 1f).setDuration(1000)
        animator.addUpdateListener { animation ->
            val v = animation.animatedValue as Float
            window.statusBarColor = oneToTwo!!.with(v)
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val animator2 = ObjectAnimator.ofFloat(0f, 1f).setDuration(1000)
                animator2.addUpdateListener { animation ->
                    val v = animation.animatedValue as Float
                    window.statusBarColor = twoToThree!!.with(v)
                }
                animator2.start()
            }
        })
        animator.start()
    }

}
