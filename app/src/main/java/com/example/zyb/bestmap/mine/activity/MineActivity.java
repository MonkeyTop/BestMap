package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.home.HomeActivity;

/**
 * Created by zyb on 2016/9/12.
 */
public class MineActivity extends Activity {
    private ImageButton ib_back;
    private CheckBox cb_night;
    private RadioButton rb_suggest;
    private RadioButton rb_thanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        cb_night = (CheckBox) findViewById(R.id.cb_night);
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
        //操作切换夜间模式控件点击事件
        cb_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断夜间模式开启与关闭（在这里对应添加关联HomeActivity地图显示模式）
                if (isChecked) {
                    Toast.makeText(MineActivity.this, "已开启夜间模式", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MineActivity.this, "已关闭夜间模式", Toast.LENGTH_SHORT).show();
                }
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
