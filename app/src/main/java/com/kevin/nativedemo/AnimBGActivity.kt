package com.kevin.nativedemo

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Kevin on 2019-12-16<br/>
 * Blog:http://student9128.top/
 * 公众号：竺小竹
 * Describe:<br/>无限滚动背景页
 */
class AnimBGActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_anim_bg)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels
        var llContainer: LinearLayout = findViewById(R.id.ll_container)
        var imageV = ImageView(this)
        var imageVV = ImageView(this)
        val layoutParams = LinearLayout.LayoutParams(widthPixels, heightPixels)
        imageV.layoutParams = layoutParams
        imageVV.layoutParams = layoutParams
        imageV.setImageResource(R.drawable.ic_login)
        imageVV.setImageResource(R.drawable.ic_login)
        imageV.scaleType=ImageView.ScaleType.CENTER_CROP
        imageVV.scaleType=ImageView.ScaleType.CENTER_CROP
        llContainer.addView(imageV)
        llContainer.addView(imageVV)
        showAnim(imageV, heightPixels)
        showAnim(imageVV, heightPixels)
    }
    private fun showAnim(image: ImageView, heightPixels: Int) {
        val animator =
            ObjectAnimator.ofFloat(image, "translationY", 0f, (0 - heightPixels).toFloat())
        animator.duration = 10000
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = Animation.INFINITE
        animator.start()
    }
}