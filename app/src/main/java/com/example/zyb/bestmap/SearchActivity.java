package com.example.zyb.bestmap.route.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.zyb.bestmap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anzhuo on 2016/9/22.
 */

public class SearchActivity extends Activity implements Inputtips.InputtipsListener{
    ListView listView;
    EditText tv_search;
    InputtipsQuery inputquery;
    Inputtips inputTips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tv_search= (EditText) findViewById(R.id.tv_search);
        listView= (ListView) findViewById(R.id.lv_search);
        inputTips = new Inputtips(SearchActivity.this,inputquery);
        inputTips.setInputtipsListener(this);
        String search=tv_search.getText().toString();
        String city="武汉";
        inputquery = new InputtipsQuery(search, city);
        inputquery.setCityLimit(true);
        inputTips.requestInputtipsAsyn();
    }
    @Override
    public void onGetInputtips(final List<Tip> tipList, int rCode) {
        if (rCode == 1000) {
            List<HashMap<String, String>> listString = new ArrayList<>();
            for (int i = 0; i < tipList.size(); i++) {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", tipList.get(i).getName());
                map.put("address", tipList.get(i).getDistrict());
                listString.add(map);
            }
            SimpleAdapter aAdapter = new SimpleAdapter(getApplicationContext(), listString, R.layout.item_search,
                    new String[]{"name", "address"}, new int[]{R.id.tv_name, R.id.tv_address});
            listView.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
        }
    }
}