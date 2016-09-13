package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.zyb.bestmap.R;

/**
 * 特别感谢界面
 * <p/>
 * Created by zyb on 2016/9/13.
 */
public class ThanksActivity extends Activity {
    private ImageButton ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        //初始化返回键
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        //操作控件点击事件
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanksActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
