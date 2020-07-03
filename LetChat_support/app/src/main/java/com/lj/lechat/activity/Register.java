package com.lj.lechat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lj.lechat.R;

public class Register extends AppCompatActivity {
    private EditText reg_username;
    private EditText reg_pwd;
    private Button reg_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String username = reg_username.getText().toString().trim();
               String pwd = reg_pwd.getText().toString().trim();

               if(username.equals("")){
                   Toast.makeText(Register.this,"用户名不能为空",Toast.LENGTH_LONG).show();
               }else if(pwd.equals("")){
                   Toast.makeText(Register.this,"密码不能为空",Toast.LENGTH_LONG).show();
               }else {
                   new Thread(){
                       @Override
                       public void run() {
                           try {
                               EMClient.getInstance().createAccount(username,pwd);
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(Register.this,"注册成功",Toast.LENGTH_LONG).show();
                                   }
                               });
                           } catch (HyphenateException e) {
                               e.printStackTrace();
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(Register.this,"注册失败:"+e.getMessage(),Toast.LENGTH_LONG).show();
                                   }
                               });
                           }
                       }
                   }.start();
               }
            }
        });
    }

    private void initView() {
        reg_username = findViewById(R.id.edit_username);
        reg_pwd = findViewById(R.id.edit_pwd);
        reg_btn = findViewById(R.id.toReg);

    }
}
