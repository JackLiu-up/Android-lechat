## 1.下载环信 SDK

* http://www.easemob.com/download/im


## 2.配置清单文件
* 参考环信开发文档:http://docs-im.easemob.com/im/android/sdk/import
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="Your Package"
    android:versionCode="100"
    android:versionName="1.0.0">
  
    <!-- Required -->
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_MOCK_LOCATION" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>  
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.GET_TASKS" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
 
    <application
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:name="Your Application">
  
   	<!-- 设置环信应用的AppKey -->
    	<meta-data android:name="EASEMOB_APPKEY"  android:value="Your AppKey" />
    	<!-- 声明SDK所需的service SDK核心功能-->
    	<service android:name="com.hyphenate.chat.EMChatService" android:exported="true"/>
        <service android:name="com.hyphenate.chat.EMJobService"
            android:permission="android.permission.BIND_JOB_SERVICE"
            android:exported="true"
            />
        <!-- 声明SDK所需的receiver -->
        <receiver android:name="com.hyphenate.chat.EMMonitorReceiver">
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_REMOVED"/>
                <data android:scheme="package"/>
            </intent-filter>
            <!-- 可选filter -->
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
                <action android:name="android.intent.action.USER_PRESENT" />
            </intent-filter>
        </receiver>
    </application>
</manifest>

```

## 3.导入 MODEL

File new import Moudels

```
将下载好的easemob-sdk-3.6.9.1/examples/easeui导入
```

## 4.EaseUI的配置
* 初始化EaseUI http://docs-im.easemob.com/im/android/sdk/basic

## 5.splash页面编写