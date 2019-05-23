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
         android:id="@+id/reside_layout"
         android:layout_width="match_parent"
         android:layout_height="match_parent">
         ......
    </com.liu.residelibrary.view.ResideLayout>

ShadowLayout:阴影的效果

    <com.liu.residelibrary.shadow.ShadowLayout
        android:id="@+id/imageView"
        android:layout_width="200dp"
        android:layout_height="200dp"
        app:shadowColor="#29000000"
        app:shadowShape="rectangle"
        app:shadowDx="0dp"
        app:shadowDy="6dp"
        app:shadowRadius="8dp"
        app:shadowSide="all"/>
        
    如上面 xml 中代码显示的那样，总共有 6 个自定义属性，其含义分别如下：
    
    app:shadowColor="#29000000" 控制阴影的颜色，注意：颜色必须带有透明度的值
    app:shadowShape="rectangle|oval" 控制阴影的形状：圆角矩形|圆形
    app:shadowDx="0dp" 控制阴影 x 轴的偏移量
    app:shadowDy="6dp" 控制阴影 y 轴的偏移量
    app:shadowRadius="8dp" 控制阴影的范围
    app:shadowSide="all|left|right|top|bottom" 控制阴影显示的边界，共有五个值
    
WaveView 注意：

1）在AndroidManifest中修改theme

         <activity
             android:name=".activity.WaveActivity"
             android:screenOrientation="portrait"
             android:theme="@style/SurfaceTheme"/>

2）添加SurfaceTheme

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

    开始：wavaview.startAnim()
    
    结束：wavaview.stopAnim()

属性：

    ResideLayout.setAttribute(false, false);//第一个：背景是否变暗 第二个：是否倾斜角度
