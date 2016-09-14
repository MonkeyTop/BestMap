package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zyb.bestmap.R;

/**
 * Created by zyb on 2016/9/14.
 */
public class UserActivity extends Activity {
    private ImageButton ib_back;
    private EditText et_username;
    private EditText et_password;
    private Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        bt_register = (Button) findViewById(R.id.bt_register);
        //返回键点击事件
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //跳转注册界面
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
