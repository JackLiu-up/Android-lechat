package com.lj.lechat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lj.lechat.Bean.User;
import com.lj.lechat.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {
    private EditText reg_username;
    private EditText reg_pwd;
    private Button reg_btn;
    private EditText reg_nickname, reg_hobbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = reg_username.getText().toString().trim();
                final String pwd = reg_pwd.getText().toString().trim();
                final String nickname = reg_nickname.getText().toString().trim();
                final String hobbit = reg_hobbit.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(Register.this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            /**
                             * 环信和比目注册
                             */
                            EMClient.getInstance().createAccount(reg_username.getText().toString(), reg_pwd.getText().toString());
                            final User user = new User();
                            user.setUsername(username);
                            user.setPassword(pwd);
                            user.setNickname(nickname);
                            user.setHobbit(hobbit);
                            user.signUp(new SaveListener<User>() {
                                @Override
                                public void done(User user, BmobException e) {
                                    if (e == null){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(Register.this,Login.class));
                                            }
                                        });
                                    }else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Toast.makeText(Register.this, "注册失败"+e, Toast.LENGTH_LONG).show();
                                                Log.d("环信注册失败：", String.valueOf(e));
                                            }
                                        });
                                    }
                                }
                            });

                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Register.this, "注册失败:" + e, Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }.start();
            }
        });
    }

    private void initView() {
        reg_username = findViewById(R.id.edit_username);
        reg_pwd = findViewById(R.id.edit_pwd);
        reg_btn = findViewById(R.id.toReg);
        reg_nickname = findViewById(R.id.editNick);
        reg_hobbit = findViewById(R.id.editHobbit);
    }

}
