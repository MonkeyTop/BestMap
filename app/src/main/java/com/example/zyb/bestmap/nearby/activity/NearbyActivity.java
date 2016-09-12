package com.example.zyb.bestmap.nearby.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.home.HomeActivity;

/**
 * Created by zyb on 2016/9/12.
 */
public class NearbyActivity extends Activity {
    private ImageButton ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        //初始化顶部栏控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        //操作控件ib_back点击事件
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearbyActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
