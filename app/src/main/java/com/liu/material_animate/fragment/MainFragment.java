package com.liu.material_animate.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.liu.material_animate.R;

public class MainFragment extends FragmentActivity implements View.OnClickListener {
    private TextView mTvHome, mTvLesson, mTvMe, mTvReview;
    private ImageView mIvHome, mIvLesson, mIvMe, mIvReview;
    private Fragment mHomeFragment, mLessonFragment, mMeFragment, mReviewFragment, mCurrentFragment;
    private Fragment findFragment;//新加入的“发现”
    private TextView tvFind;
    private ImageView ivFind;
    private int mColorNormal = Color.parseColor("#A7B1BE");
    private int mColorSelect = Color.parseColor("#FF0000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        mTvHome = findViewById(R.id.tvHome);
        mIvHome = findViewById(R.id.ivHome);
        mTvLesson = findViewById(R.id.tvLesson);
        mIvLesson = findViewById(R.id.ivLesson);
        mTvMe = findViewById(R.id.tvMe);
        mIvMe = findViewById(R.id.ivMe);
        mTvReview = findViewById(R.id.tvReview);
        mIvReview = findViewById(R.id.ivReview);
        tvFind = findViewById(R.id.tvFind);
        ivFind = findViewById(R.id.ivFind);
        findViewById(R.id.llHome).setOnClickListener(this);
        findViewById(R.id.llLesson).setOnClickListener(this);
        findViewById(R.id.llMe).setOnClickListener(this);
        findViewById(R.id.llReview).setOnClickListener(this);
        findViewById(R.id.llFind).setOnClickListener(this);
        initFragment(savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mCurrentFragment != null)
            outState.putString("mCurrentFragment", mCurrentFragment.getClass().getSimpleName());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llHome:
                showFragment(mHomeFragment);
                resetNavi();
                mTvHome.setTextColor(mColorSelect);
                mIvHome.setImageResource(R.drawable.tab_home_c);
                break;
            case R.id.llLesson:
                showFragment(mLessonFragment);
                resetNavi();
                mTvLesson.setTextColor(mColorSelect);
                mIvLesson.setImageResource(R.drawable.tab_lesson_c);
                break;
            case R.id.llReview:
                showFragment(mReviewFragment);
                resetNavi();
                mTvReview.setTextColor(mColorSelect);
                mIvReview.setImageResource(R.drawable.tab_review_c);
                break;
            case R.id.llMe:
                showFragment(mMeFragment);
                resetNavi();
                mTvMe.setTextColor(mColorSelect);
                mIvMe.setImageResource(R.drawable.tab_me_c);
                break;
            case R.id.llFind:
                showFragment(findFragment);
                resetNavi();
                tvFind.setTextColor(mColorSelect);
                ivFind.setImageResource(R.drawable.tab_find_c);
                break;
        }
    }


    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {  // “内存重启”时调用
            mHomeFragment = getSupportFragmentManager().findFragmentByTag("HomeFragment");
            mLessonFragment = getSupportFragmentManager().findFragmentByTag("LessonFragment");
            mReviewFragment = getSupportFragmentManager().findFragmentByTag("ReviewFragment");
            mMeFragment = getSupportFragmentManager().findFragmentByTag("MeFragment");
            findFragment = getSupportFragmentManager().findFragmentByTag("FindFragment");
            // 解决重叠问题
            getSupportFragmentManager().beginTransaction()
                    .hide(mHomeFragment)
                    .hide(mLessonFragment)
                    .hide(mReviewFragment)
                    .hide(mMeFragment)
                    .hide(findFragment)
                    .commitAllowingStateLoss();

            switch (savedInstanceState.getString("mCurrentFragment", "HomeFragment")) {
                case "HomeFragment":
                    onClick(findViewById(R.id.llHome));
                    break;
                case "LessonFragment":
                    onClick(findViewById(R.id.llLesson));
                    break;
                case "ReviewFragment":
                    onClick(findViewById(R.id.llReview));
                    break;
                case "MeFragment":
                    onClick(findViewById(R.id.llMe));
                    break;
                case "FindFragment":
                    onClick(findViewById(R.id.llFind));
                    break;
            }
        } else {  // 正常时
            mHomeFragment = new HomeFragment();
            mLessonFragment = new HomeFragment();
            mReviewFragment = new HomeFragment();
            mMeFragment = new MeFragment();
            findFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mHomeFragment, "HomeFragment")
                    .hide(mHomeFragment)
                    .add(R.id.container, mLessonFragment, "LessonFragment")
                    .hide(mLessonFragment)
                    .add(R.id.container, mReviewFragment, "ReviewFragment")
                    .hide(mReviewFragment)
                    .add(R.id.container, mMeFragment, "MeFragment")
                    .hide(mMeFragment)
                    .add(R.id.container, findFragment, "FindFragment")
                    .hide(findFragment)
                    .commitAllowingStateLoss();
            onClick(findViewById(R.id.llFind));
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != null) {
            fragmentTransaction.hide(mCurrentFragment);
        }
        fragmentTransaction.show(fragment);
        mCurrentFragment = fragment;
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void resetNavi() {
        mTvHome.setTextColor(mColorNormal);
        mTvLesson.setTextColor(mColorNormal);
        mTvReview.setTextColor(mColorNormal);
        mTvMe.setTextColor(mColorNormal);
        tvFind.setTextColor(mColorNormal);

        mIvHome.setImageResource(R.drawable.tab_home);
        mIvLesson.setImageResource(R.drawable.tab_lesson);
        mIvReview.setImageResource(R.drawable.tab_review);
        mIvMe.setImageResource(R.drawable.tab_me);
        ivFind.setImageResource(R.drawable.tab_find);
    }
}
