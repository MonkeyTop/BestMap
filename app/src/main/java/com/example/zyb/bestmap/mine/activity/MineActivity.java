package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.home.HomeActivity;

/**
 * Created by zyb on 2016/9/12.
 */
public class MineActivity extends Activity {
    private ImageButton ib_back;
    private RadioButton rb_suggest;
    private RadioButton rb_thanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        rb_suggest = (RadioButton) findViewById(R.id.rb_suggest);
        rb_thanks = (RadioButton) findViewById(R.id.rb_thanks);
        //操作返回控件点击事件
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //操作跳转帮助与反馈界面控件点击事件
        rb_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, SuggestActivity.class);
                startActivity(intent);
            }
        });
        //操作跳转特别鸣谢界面控件点击事件
        rb_thanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, ThanksActivity.class);
                startActivity(intent);
            }
        });
    }
}
