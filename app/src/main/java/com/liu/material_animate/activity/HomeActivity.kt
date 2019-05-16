package com.liu.material_animate.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.SimpleAdapter
import androidx.fragment.app.FragmentActivity
import com.liu.material_animate.R
import com.liu.residelibrary.view.ResideLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class HomeActivity : FragmentActivity() {
    private var adapter: SimpleAdapter? = null
    private var lists: MutableList<Map<String, Any>>? = null
    private val content = arrayOf("修改密码", "收货地址", "关于", "清除缓存")
    private val cacheSize = arrayOf("", "", "", "100M")
    private val imageViews = intArrayOf(R.drawable.icon_change_pwd, R.drawable.icon_address, R.drawable.icon_about, R.drawable.icon_clear_cache)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resideLayout = findViewById<ResideLayout>(R.id.reside_layout)
        setStatusBarColor(Color.TRANSPARENT, true)
        ResideLayout.setAttribute(false, false)
        //      ViewPager pager = findViewById(R.id.pager);
        //      pager.setAdapter(new SimplePagerAdapter(getSupportFragmentManager()));
        //        准备数据源
        lists = ArrayList()
        for (i in content.indices) {
            val map = HashMap<String, Any>()
            map["image"] = imageViews[i]
            map["content"] = content[i]
            map["cacheSize"] = cacheSize[i]
            lists!!.add(map)
        }
        adapter = SimpleAdapter(this@HomeActivity, lists, R.layout.list_item, arrayOf("image", "content", "cacheSize"), intArrayOf(R.id.image, R.id.content, R.id.cacheSize))
        menu.adapter = adapter
        menu.divider = null
        menu.setOnItemClickListener { _, _, _, _ -> resideLayout.closePane() }
        set.setOnClickListener { resideLayout.openPane() }
    }

    //    private class SimplePagerAdapter extends FragmentPagerAdapter {
    //
    //        SimplePagerAdapter(FragmentManager fm) {
    //            super(fm);
    //        }
    //
    //        @Override
    //        public Fragment getItem(int i) {
    //            return SimpleFragment.newInstance(i);
    //        }
    //
    //        @Override
    //        public int getCount() {
    //            return 5;
    //        }
    //
    //    }
    //
    //    public static class SimpleFragment extends Fragment {
    //        private int mIndex;
    //        private TextView mView;
    //
    //        static SimpleFragment newInstance(int index) {
    //            SimpleFragment fragment = new SimpleFragment();
    //            Bundle args = new Bundle();
    //            args.putInt("index", index);
    //            fragment.setArguments(args);
    //            return fragment;
    //        }
    //
    //        @Override
    //        public void onCreate(Bundle savedInstanceState) {
    //            super.onCreate(savedInstanceState);
    //            mIndex = getArguments().getInt("index");
    //        }
    //
    //        @Override
    //        public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
    //            mView = new TextView(container.getContext());
    //
    //            //mView.setBackgroundColor(Color.WHITE);
    //            return mView;
    //        }
    //
    //        @Override
    //        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    //            super.onViewCreated(view, savedInstanceState);
    //            mView.setOnClickListener(new View.OnClickListener() {
    //                @Override
    //                public void onClick(View v) {
    //                    Toast.makeText(getContext(), "点击控件", Toast.LENGTH_LONG).show();
    //                }
    //            });
    //            //mView.setText("Fragment: " + mIndex);
    //            //mView.setTextColor(getResources().getColor(R.color.black));
    //        }
    //    }

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
