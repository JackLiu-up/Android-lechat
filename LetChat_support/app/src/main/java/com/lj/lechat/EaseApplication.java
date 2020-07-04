package com.lj.lechat;

import android.app.Application;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

import cn.bmob.v3.Bmob;

public class EaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);    //默认接受 不直接同意好友请求 单聊
        options.setAutoAcceptGroupInvitation(false); //默认接受 不直接同意群组请求 群聊
        EaseUI.getInstance().init(this,options);

        //比目初始化
        Bmob.initialize(this,"c796533e10b18dac485c0501e3fa5a1e");
    }
}
