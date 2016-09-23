package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.home.HomeActivity;

/**
 * Created by zyb on 2016/9/12.
 */
public class MineActivity extends Activity {
    private ImageButton ib_back;
    private RadioButton rb_user;
    private RadioButton rb_offlinemap;
    private RadioButton rb_violation;
    private RadioButton rb_tools;
    private CheckBox cb_night;
    private RadioButton rb_news;
    private RadioButton rb_suggest;
    private RadioButton rb_thanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        rb_user = (RadioButton) findViewById(R.id.rb_user);
        rb_offlinemap = (RadioButton) findViewById(R.id.rb_offlinemap);
        rb_violation = (RadioButton) findViewById(R.id.rb_violation);
        rb_tools = (RadioButton) findViewById(R.id.rb_tools);
        cb_night = (CheckBox) findViewById(R.id.cb_night);
        rb_news = (RadioButton) findViewById(R.id.rb_news);
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
        //切换登录注册界面
        rb_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
        //离线宝地图下载控件
        rb_offlinemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, OfflineMapActivity.class);
                startActivity(intent);
            }
        });
        //违章查询点击事件
        rb_violation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, ViolationActivity.class);
                startActivity(intent);
            }
        });
        //工具箱弹窗点击事件
        rb_tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
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
        //操作跳转头条新闻界面控件点击事件
        rb_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, NewsActivity.class);
                startActivity(intent);
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

    private void showPopupWindow() {
        //添加自定义布局
        View view = LayoutInflater.from(this).inflate(R.layout.item_tools, null);
        //初始化子布局控件
        RadioButton rb_computer = (RadioButton) view.findViewById(R.id.rb_computer);
        RadioButton rb_light = (RadioButton) view.findViewById(R.id.rb_light);
        RadioButton rb_calendar = (RadioButton) view.findViewById(R.id.rb_calendar);
        //得到当前屏幕的显示器对象
        Display display = getWindowManager().getDefaultDisplay();
        //创建一个Point点对象用来接收屏幕尺寸信息
        Point size = new Point();
        //Point点对象接收当前设备屏幕尺寸信息
        display.getSize(size);
        int width = size.x;//从Point点对象中获取屏幕的宽度(单位像素)
        int height = size.y;//从Point点对象中获取屏幕的高度(单位像素)
        //设置popupwindow高和宽
        PopupWindow popupWindow = new PopupWindow(view, 2 * width / 3, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setFocusable(true);
        //设置是否点击PopupWindow外退出PopupWindow
        popupWindow.setOutsideTouchable(true);
        //设置PopupWindow的背景为一个空的Drawable对象，如果不设置这个，那么PopupWindow弹出后就无法退出了
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //创建当前界面的一个参数对象
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        //把该参数对象设置进当前界面中
        getWindow().setAttributes(lp);
        //设置PopupWindow退出监听器
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //如果PopupWindow消失了，即退出了，那么触发该事件，然后把当前界面的透明度设置为不透明
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //设置popupwindow位置在屏幕正中央
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //子布局中控件点击事件
        rb_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, ComputerActivity.class);
                startActivity(intent);
            }
        });
        rb_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, LightActivity.class);
                startActivity(intent);
            }
        });
        rb_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}
