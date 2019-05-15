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
	        implementation 'com.github.liuninglxn:Residelayout:v1.0.1'
	}

示例预览：



属性：
 ResideLayout.setAttribute(false, false);//第一个：背景是否变暗 第二个：是否倾斜角度