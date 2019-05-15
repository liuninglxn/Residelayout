package com.liu.material_animate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.liu.material_animate.R;
import com.liu.residelibrary.view.ResideLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeFragment extends Fragment {
    private SimpleAdapter adapter;
    private List<Map<String, Object>> lists;
    private String[] content = {"修改密码", "收货地址", "关于", "清除缓存"};
    private String[] cacheSize = {"", "", "", "100M"};
    private int[] imageViews = {R.drawable.icon_change_pwd, R.drawable.icon_address, R.drawable.icon_about, R.drawable.icon_clear_cache};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ResideLayout resideLayout = view.findViewById(R.id.reside_layout);
        ResideLayout.setAttribute(false,false);
        //ViewPager pager = findViewById(R.id.pager);
        ImageView set=view.findViewById(R.id.set);
        //pager.setAdapter(new SimplePagerAdapter(getSupportFragmentManager()));
        ListView menu = view.findViewById(R.id.menu);
//        准备数据源
        lists = new ArrayList<>();
        for (int i = 0; i < content.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageViews[i]);
            map.put("content", content[i]);
            map.put("cacheSize", cacheSize[i]);
            lists.add(map);
        }
        adapter = new SimpleAdapter(getContext(), lists, R.layout.list_item
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
}
