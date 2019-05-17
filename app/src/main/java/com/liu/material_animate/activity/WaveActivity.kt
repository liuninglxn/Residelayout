package com.liu.material_animate.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import com.liu.material_animate.R
import kotlinx.android.synthetic.main.activity_waveview.*


class WaveActivity : FragmentActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waveview)
        setStatusBarColor(Color.TRANSPARENT, true)

        record.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    wavaview.startAnim()
                }
                MotionEvent.ACTION_MOVE -> {
                    wavaview.startAnim()
                }
                MotionEvent.ACTION_UP -> {
                    wavaview.stopAnim()
                }
                MotionEvent.ACTION_CANCEL -> {
                    wavaview.stopAnim()
                }
            }
            true
        }
    }

    private fun setStatusBarColor(color: Int, lightStatusBar: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = window
            var visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            if (lightStatusBar) {
                visibility = visibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.decorView.systemUiVisibility = visibility
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }
}
