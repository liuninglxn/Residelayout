package com.liu.material_animate.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import com.liu.material_animate.R
import com.liu.material_animate.fragment.MainFragment
import com.liu.material_animate.fragment.MainFragmentActivity
import com.liu.residelibrary.view.ResideLayout
import kotlinx.android.synthetic.main.activity_home.*


open class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setStatusBarColor(Color.TRANSPARENT, true)
        ResideLayout.setAttribute(false, false)
        button.setOnClickListener {
            val actIntent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(actIntent)
        }
        button2.setOnClickListener {
            val actIntent = Intent(this@MainActivity, MainFragment::class.java)
            startActivity(actIntent)
        }
        button3.setOnClickListener {
            val actIntent = Intent(this@MainActivity, MainFragmentActivity::class.java)
            startActivity(actIntent)
        }
        waveview.setOnClickListener {
            val actIntent = Intent(this@MainActivity, WaveActivity::class.java)
            startActivity(actIntent)
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
