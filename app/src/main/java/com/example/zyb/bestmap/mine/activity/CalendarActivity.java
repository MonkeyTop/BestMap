package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.mine.adapter.CalendarAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zyb on 2016/9/20.
 */
public class CalendarActivity extends Activity implements GestureDetector.OnGestureListener {
    private ImageButton ib_back;
    private GestureDetector gestureDetector = null;
    private CalendarAdapter calV = null;
    private GridView gridView = null;
    private TextView topText = null;
    private static int jumpMonth = 0;//每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0;//滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    private Bundle bun = null;//接收参数
    private String state = "";

    public CalendarActivity() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);  //当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        bun = getIntent().getExtras();//in
        if (bun != null && bun.getString("state").equals("ruzhu")) {
            state = bun.getString("state");
            System.out.println("%%%%%%" + state);
        } else if (bun != null && bun.getString("state").equals("lidian")) {
            state = bun.getString("state");
            System.out.println("|||||||||||" + state);
        }
        gestureDetector = new GestureDetector(this);
        calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        addGridView();
        gridView.setAdapter(calV);
        topText = (TextView) findViewById(R.id.tv_month);
        addTextToTopTextView(topText);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int gvFlag = 0;//每次添加gridview到viewflipper中时给的标记
        if (e1.getX() - e2.getX() > 120) {
            //像左滑动
            addGridView();//添加一个gridView
            jumpMonth++;//下一个月
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
            gridView.setAdapter(calV);
            addTextToTopTextView(topText);
            gvFlag++;
            return true;
        } else if (e1.getX() - e2.getX() < -120) {
            //向右滑动
            addGridView();//添加一个gridView
            jumpMonth--;//上一个月
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
            gridView.setAdapter(calV);
            gvFlag++;
            addTextToTopTextView(topText);
            return true;
        }
        return false;
    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, menu.FIRST, menu.FIRST, "今天");
        return super.onCreateOptionsMenu(menu);
    }

    //选择菜单
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                jumpMonth = 0;    //跳转到今天
                int xMonth = jumpMonth;
                int xYear = jumpYear;
                int gvFlag = 0;
                jumpYear = 0;
                addGridView();//添加一个gridView
                year_c = Integer.parseInt(currentDate.split("-")[0]);
                month_c = Integer.parseInt(currentDate.split("-")[1]);
                day_c = Integer.parseInt(currentDate.split("-")[2]);
                calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
                gridView.setAdapter(calV);
                addTextToTopTextView(topText);
                gvFlag++;
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.gestureDetector.onTouchEvent(event);
    }

    //添加头部的年份 闰哪月等信息
    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
        view.setTextColor(Color.WHITE);
        view.setTypeface(Typeface.DEFAULT_BOLD);
    }

    //添加gridview
    private void addGridView() {
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            //将gridview中的触摸事件回传给gestureDetector
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return CalendarActivity.this.gestureDetector.onTouchEvent(event);
            }
        });
    }
}
