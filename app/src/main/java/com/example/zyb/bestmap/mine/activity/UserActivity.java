package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zyb.bestmap.R;

/**
 * Created by zyb on 2016/9/14.
 */
public class UserActivity extends Activity {
    private ImageButton ib_back;
    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    private Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_register = (Button) findViewById(R.id.bt_register);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        //取值
        SharedPreferences sharedPreferences = getSharedPreferences("FILE_NAME", Context.MODE_PRIVATE);
        final String phone = sharedPreferences.getString("PHONE", "");
        final String password = sharedPreferences.getString("PWD", "");
        //返回键点击事件
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //登录点击事件
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_username.getText().toString().trim()) || TextUtils.isEmpty(et_password.getText().toString().trim())) {
                    Toast.makeText(UserActivity.this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone.equals(et_username.getText().toString()) && password.equals(et_password.getText().toString())) {
                    Toast.makeText(UserActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserActivity.this, MineActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UserActivity.this, "账号或密码有错，请重新输入...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //跳转注册界面
        bt_register.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(UserActivity.this, RegisterActivity.class);
                                               startActivity(intent);
                                           }
                                       }

        );
    }
}
