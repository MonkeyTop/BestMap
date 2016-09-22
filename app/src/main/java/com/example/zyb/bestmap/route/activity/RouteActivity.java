package com.example.zyb.bestmap.route.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.route.adapter.FragAdapter;
import com.example.zyb.bestmap.route.fragment.FragBus;
import com.example.zyb.bestmap.route.fragment.FragDrive;
import com.example.zyb.bestmap.route.fragment.FragWalk;
import com.example.zyb.bestmap.route.util.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyb on 2016/9/12.
 */
public class RouteActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    FragAdapter fragAdapter;
    FragBus fragBus;
    FragDrive fragDrive;
    FragWalk fragWalk;
    List<Fragment> list;
    CustomViewPager viewPager;
    ImageButton ib_back;
    TextView tv_begin;
    TextView tv_finish;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        list = new ArrayList<>();
        viewPager = (CustomViewPager) findViewById(R.id.view_page);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        tv_begin= (TextView) findViewById(R.id.tv_begin);
        tv_finish= (TextView) findViewById(R.id.tv_finish);
        fragWalk = new FragWalk();
        fragBus = new FragBus();
        fragDrive = new FragDrive();
        list.add(fragBus);
        list.add(fragDrive);
        list.add(fragWalk);
        fragAdapter = new FragAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(fragAdapter);
        //设置 不允许滑动
        viewPager.setPagingEnabled(false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_drive:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_bus:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_walk:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RouteActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RouteActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
