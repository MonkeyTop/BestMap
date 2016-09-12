package com.example.zyb.bestmap.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zyb on 2016/9/12.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResID());
        infindViewById();
    }

    protected abstract void infindViewById();

    /**
     * 设置布局文件
     */
    public abstract int setLayoutResID();

    @Override
    public void onClick(View v) {

    }
}
