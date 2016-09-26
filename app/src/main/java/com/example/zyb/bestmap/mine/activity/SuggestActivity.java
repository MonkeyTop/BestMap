package com.example.zyb.bestmap.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.zyb.bestmap.R;

/**
 * 帮助与反馈界面
 * <p/>
 * Created by zyb on 2016/9/13.
 */
public class SuggestActivity extends AppCompatActivity {
    private ImageButton ib_back;
    private RadioButton rb_help;
    private RadioButton rb_feedback;
    HelpActivity helpActivity;
    FeedbackActivity feedbackActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        rb_help = (RadioButton) findViewById(R.id.rb_help);
        rb_feedback = (RadioButton) findViewById(R.id.rb_feedback);
        //操作控件点击事件
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuggestActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        rb_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建FragmentManager
                FragmentManager manager = getSupportFragmentManager();
                //开启事务
                FragmentTransaction transaction = manager.beginTransaction();
                //先把所有Fragment隐藏
                hideAllFragment(transaction);
                if (helpActivity == null) {
                    helpActivity = new HelpActivity();
                    transaction.add(R.id.frame, helpActivity);
                } else {
                    transaction.show(helpActivity);
                }
                transaction.commit();
            }
        });
        rb_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建FragmentManager
                FragmentManager manager = getSupportFragmentManager();
                //开启事务
                FragmentTransaction transaction = manager.beginTransaction();
                //先把所有Fragment隐藏
                hideAllFragment(transaction);
                if (feedbackActivity == null) {
                    feedbackActivity = new FeedbackActivity();
                    transaction.add(R.id.frame, feedbackActivity);
                } else {
                    transaction.show(feedbackActivity);
                }
                transaction.commit();
            }
        });
    }

    //fragment隐藏方法
    private void hideAllFragment(FragmentTransaction transaction) {
        if (helpActivity != null) {
            transaction.hide(helpActivity);
        }
        if (feedbackActivity != null) {
            transaction.hide(feedbackActivity);
        }
    }
}
