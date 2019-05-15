package com.liu.material_animate.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.liu.material_animate.R;
import com.liu.residelibrary.view.ResideLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragmentActivity extends FragmentActivity implements View.OnClickListener {
    private ResideLayout mResideLayout;
    private TextView mTvHome, mTvLesson, mTvMe, mTvReview;
    private ImageView mIvHome, mIvLesson, mIvMe, mIvReview;
    private Fragment mHomeFragment, mLessonFragment, mMeFragment, mReviewFragment, mCurrentFragment;
    private Fragment findFragment;//新加入的“发现”
    private TextView tvFind;
    private ImageView ivFind;
    private int mColorNormal = Color.parseColor("#A7B1BE");
    private int mColorSelect = Color.parseColor("#FF0000");

    private SimpleAdapter adapter;
    private List<Map<String, Object>> lists;
    private String[] content = {"修改密码", "收货地址", "关于UUabc", "清除缓存"};
    private String[] cacheSize = {"", "", "", "100M"};
    private int[] imageViews = {R.drawable.icon_change_pwd, R.drawable.icon_address, R.drawable.icon_about, R.drawable.icon_clear_cache};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragact);

        mResideLayout = findViewById(R.id.reside_layout);
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

        setStatusBarColor(Color.TRANSPARENT, true);
        ResideLayout.setAttribute(false,false);
        //pager.setAdapter(new SimplePagerAdapter(getSupportFragmentManager()));
        ListView menu = findViewById(R.id.menu);
//        准备数据源
        lists = new ArrayList<>();
        for (int i = 0; i < content.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageViews[i]);
            map.put("content", content[i]);
            map.put("cacheSize", cacheSize[i]);
            lists.add(map);
        }
        adapter = new SimpleAdapter(MainFragmentActivity.this, lists, R.layout.list_item
                , new String[]{"image", "content", "cacheSize"}
                , new int[]{R.id.image, R.id.content, R.id.cacheSize});
        menu.setAdapter(adapter);
        menu.setDivider(null);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mResideLayout.closePane();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mCurrentFragment != null)
            outState.putString("mCurrentFragment", mCurrentFragment.getClass().getSimpleName());
        super.onSaveInstanceState(outState);
    }

    protected void setStatusBarColor(int color, boolean lightStatusBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            int visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            if (lightStatusBar) {
                visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(visibility);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llHome:
                showFragment(mHomeFragment);
                resetNavi();
                mTvHome.setTextColor(mColorSelect);
                mIvHome.setImageResource(R.drawable.tab_home_c);
                mResideLayout.setSlideable(false);
                break;
            case R.id.llLesson:
                showFragment(mLessonFragment);
                resetNavi();
                mTvLesson.setTextColor(mColorSelect);
                mIvLesson.setImageResource(R.drawable.tab_lesson_c);
                mResideLayout.setSlideable(false);
                break;
            case R.id.llReview:
                showFragment(mReviewFragment);
                resetNavi();
                mTvReview.setTextColor(mColorSelect);
                mIvReview.setImageResource(R.drawable.tab_review_c);
                mResideLayout.setSlideable(false);
                break;
            case R.id.llMe:
                showFragment(mMeFragment);
                resetNavi();
                mTvMe.setTextColor(mColorSelect);
                mIvMe.setImageResource(R.drawable.tab_me_c);
                mResideLayout.setSlideable(true);
                break;
            case R.id.llFind:
                showFragment(findFragment);
                resetNavi();
                tvFind.setTextColor(mColorSelect);
                ivFind.setImageResource(R.drawable.tab_find_c);
                mResideLayout.setSlideable(false);
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
            mMeFragment = new HomeFragment();
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
