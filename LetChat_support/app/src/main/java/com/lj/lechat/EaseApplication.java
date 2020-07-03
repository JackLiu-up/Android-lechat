package com.lj.lechat;

import android.app.Application;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

public class EaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);    //默认接受 不直接同意好友请求 单聊
        options.setAutoAcceptGroupInvitation(false); //默认接受 不直接同意群组请求 群聊
        EaseUI.getInstance().init(this,options);

    }
}
