package com.liu.material_animate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.liu.material_animate.R
import com.liu.residelibrary.view.ResideLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MeFragment : Fragment() {
    private var adapter: SimpleAdapter? = null
    private var lists: MutableList<Map<String, Any>>? = null
    private val content = arrayOf("修改密码", "收货地址", "关于", "清除缓存")
    private val cacheSize = arrayOf("", "", "", "100M")
    private val imageViews = intArrayOf(R.drawable.icon_change_pwd, R.drawable.icon_address, R.drawable.icon_about, R.drawable.icon_clear_cache)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ResideLayout.setAttribute(false, false)
        //ViewPager pager = findViewById(R.id.pager);
        //pager.setAdapter(new SimplePagerAdapter(getSupportFragmentManager()));
        lists = ArrayList()
        for (i in content.indices) {
            val map = HashMap<String, Any>()
            map["image"] = imageViews[i]
            map["content"] = content[i]
            map["cacheSize"] = cacheSize[i]
            lists!!.add(map)
        }
        adapter = SimpleAdapter(context, lists, R.layout.list_item, arrayOf("image", "content", "cacheSize"), intArrayOf(R.id.image, R.id.content, R.id.cacheSize))
        menu.adapter = adapter
        menu.divider = null
        menu.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ -> reside_layout.closePane() }
        set.setOnClickListener { reside_layout.openPane() }
    }
}
