package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cheshouye.api.client.WeizhangClient;
import com.cheshouye.api.client.json.CityInfoJson;
import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.mine.info.ViolationInfo;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends Activity {
    private ListView lv_list;
    private ListAdapter mAdapter;
    private String provinceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.query_activity_citys);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.csy_titlebar);
        TextView txtTitle = (TextView) findViewById(R.id.tv_title);
        txtTitle.setText("");
        Button bt_back = (Button) findViewById(R.id.bt_back);
        bt_back.setVisibility(View.VISIBLE);
        bt_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        provinceName = bundle.getString("province_name");
        final String provinceId = bundle.getString("province_id");
        lv_list = (ListView) findViewById(R.id.lv_1ist);
        mAdapter = new com.example.zyb.bestmap.mine.adapter.ListAdapter(this, getData(provinceId));
        lv_list.setAdapter(mAdapter);
        lv_list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
                Intent intent = new Intent();
                intent.putExtra("city_name", txt_name.getText());
                intent.putExtra("city_id", txt_name.getTag().toString());
                setResult(20, intent);
                finish();
            }
        });
    }

    private List<ViolationInfo> getData(String provinceId) {
        List<ViolationInfo> list = new ArrayList<>();
        List<CityInfoJson> cityList = WeizhangClient.getCitys(Integer.parseInt(provinceId));
        TextView txtListTip = (TextView) findViewById(R.id.list_tip);
        txtListTip.setText(provinceName + "" + cityList.size() + "");
        for (CityInfoJson cityInfoJson : cityList) {
            String cityName = cityInfoJson.getCity_name();
            int cityId = cityInfoJson.getCity_id();
            ViolationInfo model = new ViolationInfo();
            model.setNameId(cityId);
            model.setTextName(cityName);
            list.add(model);
        }
        return list;
    }
}
