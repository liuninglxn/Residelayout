package com.liu.material_animate.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.liu.material_animate.R;
import com.liu.material_animate.fragment.MainFragment;
import com.liu.material_animate.fragment.MainFragmentActivity;
import com.liu.residelibrary.view.ResideLayout;


public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setStatusBarColor(Color.TRANSPARENT, true);
        ResideLayout.setAttribute(false,false);
        //ViewPager pager = findViewById(R.id.pager);
        Button actButton=findViewById(R.id.button);
        Button FragButton=findViewById(R.id.button2);
        Button FragActButton=findViewById(R.id.button3);

        actButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actIntent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(actIntent);
            }
        });
        FragButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actIntent=new Intent(MainActivity.this, MainFragment.class);
                startActivity(actIntent);
            }
        });
        FragActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actIntent=new Intent(MainActivity.this, MainFragmentActivity.class);
                startActivity(actIntent);
            }
        });
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
