package com.liu.material_animate.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.liu.material_animate.R;
import com.liu.residelibrary.view.ResideLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeActivity extends FragmentActivity {
    private SimpleAdapter adapter;
    private List<Map<String, Object>> lists;
    private String[] content = {"修改密码", "收货地址", "关于UUabc", "清除缓存"};
    private String[] cacheSize = {"", "", "", "100M"};
    private int[] imageViews = {R.drawable.icon_change_pwd, R.drawable.icon_address, R.drawable.icon_about, R.drawable.icon_clear_cache};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ResideLayout resideLayout = findViewById(R.id.reside_layout);
        setStatusBarColor(Color.TRANSPARENT, true);
        ResideLayout.setAttribute(false, false);
        //ViewPager pager = findViewById(R.id.pager);
        ImageView set = findViewById(R.id.set);
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
        adapter = new SimpleAdapter(HomeActivity.this, lists, R.layout.list_item
                , new String[]{"image", "content", "cacheSize"}
                , new int[]{R.id.image, R.id.content, R.id.cacheSize});
        menu.setAdapter(adapter);
        menu.setDivider(null);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                resideLayout.closePane();
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideLayout.openPane();
            }
        });
    }

    private class SimplePagerAdapter extends FragmentPagerAdapter {

        SimplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return SimpleFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return 5;
        }

    }

    public static class SimpleFragment extends Fragment {
        private int mIndex;
        private TextView mView;

        static SimpleFragment newInstance(int index) {
            SimpleFragment fragment = new SimpleFragment();
            Bundle args = new Bundle();
            args.putInt("index", index);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mIndex = getArguments().getInt("index");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
            mView = new TextView(container.getContext());

            //mView.setBackgroundColor(Color.WHITE);
            return mView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "点击控件", Toast.LENGTH_LONG).show();
                }
            });
            //mView.setText("Fragment: " + mIndex);
            //mView.setTextColor(getResources().getColor(R.color.black));
        }
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
}
