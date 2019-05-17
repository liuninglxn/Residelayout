# Residelayout
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.liuninglxn:Residelayout:v1.0.2'
	}
如何使用：
ResideLayout：在布局中直接引用
    <com.liu.residelibrary.view.ResideLayout
         xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:id="@+id/reside_layout"
         android:layout_width="match_parent"
         android:layout_height="match_parent">
         ......
    </com.liu.residelibrary.view.ResideLayout>

WaveView：
注意：在AndroidManifest中修改theme
         <activity
             android:name=".activity.WaveActivity"
             android:screenOrientation="portrait"
             android:theme="@style/SurfaceTheme"/>

         <style name="SurfaceTheme" parent="Theme.AppCompat.NoActionBar">
                 <!-- Customize your theme here. -->
                 <item name="android:windowIsTranslucent">true</item>
                 <item name="android:windowNoTitle">true</item>
         </style>

在布局中直接引用
    <com.liu.residelibrary.waveview.WaveView
            android:id="@+id/wavaview"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:wlvBackgroundColor="@color/white"
            app:wlvFineLineWidth="1dp"
            app:wlvLineColor="@color/pink"
            app:wlvMoveSpeed="350"
            app:wlvThickLineWidth="1dp" />
在代码中使用：
    record.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        wavaview.startAnim()
                    }
                    MotionEvent.ACTION_UP -> {
                        wavaview.stopAnim()
                    }
                }
                true
            }
示例预览：
1.在Activity中使用
![Image text](https://raw.githubusercontent.com/liuninglxn/Residelayout/master/image/Screenshot_1557906858.png)
2.在Fragment中使用
![Image text](https://raw.githubusercontent.com/liuninglxn/Residelayout/master/image/Screenshot_1557906868.png)
3.在Fragment+Activity中使用
![Image text](https://raw.githubusercontent.com/liuninglxn/Residelayout/master/image/Screenshot_1557906875.png)

属性：
 ResideLayout.setAttribute(false, false);//第一个：背景是否变暗 第二个：是否倾斜角度
