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

public class ShortNameList extends Activity {
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        final String short_name = bundle.getString("select_short_name");
        Log.d("select_short_name...", short_name);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.csy_activity_shortname);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.csy_titlebar);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("ѡ�������ڵ�");
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gv = (GridView) findViewById(R.id.gv_1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.csy_listitem_shortname, getDate());
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String txt = adapter.getItem(position);
                if (txt.length() > 0) {
                    Toast.makeText(ShortNameList.this, txt, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("short_name", txt);
                    setResult(0, intent);
                    finish();
                }
            }
        });
    }

    private String[] getDate() {
        return new String[]{"��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "��", "³", "��", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "��", "ԥ", "��", "��", "��", "��", ""};
    }
}
