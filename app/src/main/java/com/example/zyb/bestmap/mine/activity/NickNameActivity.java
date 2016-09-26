package com.example.zyb.bestmap.mine.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyb.bestmap.R;

public class NickNameActivity extends Activity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        final String short_name = bundle.getString("select_short_name");
        Log.d("select_short_name...", short_name);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.query_activity_shortname);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.csy_titlebar);
        TextView txtTitle = (TextView) findViewById(R.id.tv_title);
        txtTitle.setText("请选择车号所在户地");
        Button bt_back = (Button) findViewById(R.id.bt_back);
        bt_back.setVisibility(View.VISIBLE);
        bt_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gridView = (GridView) findViewById(R.id.gv_1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.query_listitem_shortname, getDate());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String txt = adapter.getItem(position);
                if (txt.length() > 0) {
                    Toast.makeText(NickNameActivity.this, txt, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("short_name", txt);
                    setResult(0, intent);
                    finish();
                }
            }
        });
    }

    //全国各省别称集合
    private String[] getDate() {
        return new String[]{"京", "津", "冀", "晋", "蒙", "辽", "吉", "黑", "沪", "苏", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂",
                "湘", "粤", "桂", "琼", "渝", "川", "贵", "云", "藏", "陕", "甘", "青", "宁", "新",""};
    }
}
