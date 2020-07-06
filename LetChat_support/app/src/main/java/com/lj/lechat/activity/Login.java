package com.lj.lechat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lj.lechat.Bean.User;
import com.lj.lechat.MainActivity;
import com.lj.lechat.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login extends AppCompatActivity {
    private Button toReg, toLogin;
    private EditText lusername, lpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this,"c796533e10b18dac485c0501e3fa5a1e");

        initView();

        //登录按钮监听
        toLogin.setOnClickListener(new View.OnClickListener() {
            String username = lusername.getText().toString().trim();
            String pwd = lpwd.getText().toString().trim();

            @Override
            public void onClick(View view) {
                if (lusername.equals("") || lpwd.equals("")) {
                    Toast.makeText(Login.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            EMClient.getInstance().login(lusername.toString().trim(), lpwd.toString().trim(), new EMCallBack() {
                                @Override
                                public void onSuccess() {
                                    EMClient.getInstance().groupManager().loadAllGroups();
                                    EMClient.getInstance().chatManager().loadAllConversations();
                                    Log.d("main", "登录聊天服务器成功！");
                                    //登录成功
                                    final User user = new User();
                                    user.setUsername(username);
                                    user.setPassword(pwd);
                                    user.login(new SaveListener<User>() {
                                        @Override
                                        public void done(User user, BmobException e) {
                                            if (e == null) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Login.this, MainActivity.class));
                                                        Log.d("Message","比目登录成功");
                                                        finish();
                                                    }
                                                });
                                            } else {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(Login.this, "bmob登录失败"+e, Toast.LENGTH_SHORT).show();
                                                        Log.d("Message","比目登录失败"+e);
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onError(int i, String s) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Login.this,"登录失败"+s,Toast.LENGTH_SHORT).show();
                                            Log.d("Message","环信登录失败"+s);
                                        }
                                    });
                                }

                                @Override
                                public void onProgress(int i, String s) {

                                }
                            });
                        }
                    }.start();
                }
            }
        });

        //注册界面跳转
        toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    private void initView() {
        toReg = findViewById(R.id.toReg);
        toLogin = findViewById(R.id.login);
        lusername = findViewById(R.id.edit_username);
        lpwd = findViewById(R.id.edit_pwd);
    }
}
