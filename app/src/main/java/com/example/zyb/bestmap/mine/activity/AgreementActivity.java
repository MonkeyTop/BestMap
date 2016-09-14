package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.zyb.bestmap.R;

/**
 * Created by zyb on 2016/9/14.
 */
public class AgreementActivity extends Activity {
    private ImageButton ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        //返回键
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgreementActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
