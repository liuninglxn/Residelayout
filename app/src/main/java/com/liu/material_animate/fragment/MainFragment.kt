package com.liu.material_animate.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

import com.liu.material_animate.R
import kotlinx.android.synthetic.main.activity_fragment.*

class MainFragment : FragmentActivity(), View.OnClickListener {
    private var mHomeFragment: Fragment? = null
    private var mLessonFragment: Fragment? = null
    private var mMeFragment: Fragment? = null
    private var mReviewFragment: Fragment? = null
    private var mCurrentFragment: Fragment? = null
    private var findFragment: Fragment? = null//新加入的“发现”
    private val mColorNormal = Color.parseColor("#A7B1BE")
    private val mColorSelect = Color.parseColor("#FF0000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)

        llHome.setOnClickListener(this)
        llLesson.setOnClickListener(this)
        llMe.setOnClickListener(this)
        llReview.setOnClickListener(this)
        llFind.setOnClickListener(this)
        initFragment(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (mCurrentFragment != null)
            outState.putString("mCurrentFragment", mCurrentFragment!!.javaClass.simpleName)
        super.onSaveInstanceState(outState)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.llHome -> {
                showFragment(mHomeFragment)
                resetNavi()
                tvHome.setTextColor(mColorSelect)
                ivHome.setImageResource(R.drawable.tab_home_c)
            }
            R.id.llLesson -> {
                showFragment(mLessonFragment)
                resetNavi()
                tvLesson.setTextColor(mColorSelect)
                ivLesson.setImageResource(R.drawable.tab_lesson_c)
            }
            R.id.llReview -> {
                showFragment(mReviewFragment)
                resetNavi()
                tvReview.setTextColor(mColorSelect)
                ivReview.setImageResource(R.drawable.tab_review_c)
            }
            R.id.llMe -> {
                showFragment(mMeFragment)
                resetNavi()
                tvMe.setTextColor(mColorSelect)
                ivMe.setImageResource(R.drawable.tab_me_c)
            }
            R.id.llFind -> {
                showFragment(findFragment)
                resetNavi()
                tvFind.setTextColor(mColorSelect)
                ivFind.setImageResource(R.drawable.tab_find_c)
            }
        }
    }


    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {  // “内存重启”时调用
            mHomeFragment = supportFragmentManager.findFragmentByTag("HomeFragment")
            mLessonFragment = supportFragmentManager.findFragmentByTag("LessonFragment")
            mReviewFragment = supportFragmentManager.findFragmentByTag("ReviewFragment")
            mMeFragment = supportFragmentManager.findFragmentByTag("MeFragment")
            findFragment = supportFragmentManager.findFragmentByTag("FindFragment")
            // 解决重叠问题
            supportFragmentManager.beginTransaction()
                    .hide(mHomeFragment!!)
                    .hide(mLessonFragment!!)
                    .hide(mReviewFragment!!)
                    .hide(mMeFragment!!)
                    .hide(findFragment!!)
                    .commitAllowingStateLoss()

            when (savedInstanceState.getString("mCurrentFragment", "HomeFragment")) {
                "HomeFragment" -> onClick(llHome)
                "LessonFragment" -> onClick(llLesson)
                "ReviewFragment" -> onClick(llReview)
                "MeFragment" -> onClick(llMe)
                "FindFragment" -> onClick(llFind)
            }
        } else {  // 正常时
            mHomeFragment = HomeFragment()
            mLessonFragment = HomeFragment()
            mReviewFragment = HomeFragment()
            mMeFragment = MeFragment()
            findFragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mHomeFragment!!, "HomeFragment")
                    .hide(mHomeFragment!!)
                    .add(R.id.container, mLessonFragment!!, "LessonFragment")
                    .hide(mLessonFragment!!)
                    .add(R.id.container, mReviewFragment!!, "ReviewFragment")
                    .hide(mReviewFragment!!)
                    .add(R.id.container, mMeFragment!!, "MeFragment")
                    .hide(mMeFragment!!)
                    .add(R.id.container, findFragment!!, "FindFragment")
                    .hide(findFragment!!)
                    .commitAllowingStateLoss()
            onClick(findViewById(R.id.llFind))
        }
    }

    private fun showFragment(fragment: Fragment?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (mCurrentFragment != null) {
            fragmentTransaction.hide(mCurrentFragment!!)
        }
        fragmentTransaction.show(fragment!!)
        mCurrentFragment = fragment
        fragmentTransaction.commitAllowingStateLoss()
    }

    private fun resetNavi() {
        tvHome.setTextColor(mColorNormal)
        tvLesson.setTextColor(mColorNormal)
        tvReview.setTextColor(mColorNormal)
        tvMe.setTextColor(mColorNormal)
        tvFind.setTextColor(mColorNormal)

        ivHome.setImageResource(R.drawable.tab_home)
        ivLesson.setImageResource(R.drawable.tab_lesson)
        ivReview.setImageResource(R.drawable.tab_review)
        ivMe.setImageResource(R.drawable.tab_me)
        ivFind.setImageResource(R.drawable.tab_find)
    }
}
