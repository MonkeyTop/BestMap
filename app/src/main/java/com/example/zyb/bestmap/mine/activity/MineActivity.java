package com.example.zyb.bestmap.mine.activity;

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
public class MineActivity extends Activity {
    private ImageButton ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
