package com.lj.lechat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyphenate.chat.EMClient;
import com.lj.lechat.MainActivity;
import com.lj.lechat.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //判断是否登录环信
                new Thread() {
                    @Override
                    public void run() {
                        if (EMClient.getInstance().isLoggedInBefore()) {
                            //已登录
                            startActivity(new Intent(Splash.this, MainActivity.class));
                            finish();
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
