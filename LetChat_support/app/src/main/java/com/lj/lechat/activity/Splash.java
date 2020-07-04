package com.lj.lechat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.lj.lechat.Bean.User;
import com.lj.lechat.MainActivity;
import com.lj.lechat.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Bmob.initialize(this,"c796533e10b18dac485c0501e3fa5a1e");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //判断是否登录环信
                new Thread() {
                    @Override
                    public void run() {
                        if (EMClient.getInstance().isLoggedInBefore()) {
                            BmobUser user = BmobUser.getCurrentUser(User.class);
                            if (user != null){
                                //已登录
                                startActivity(new Intent(Splash.this, MainActivity.class));
                            }else {
                                //未登录
                                startActivity(new Intent(Splash.this, Login.class));
                                finish();
                            }

                        } else {
                            //未登录
                            startActivity(new Intent(Splash.this, Login.class));
                            finish();
                        }
                    }
                }.start();
            }
        }, 2000);  //延时两秒跳转
    }
}
