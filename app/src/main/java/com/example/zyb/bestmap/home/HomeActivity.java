package com.example.zyb.bestmap.home;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.mine.activity.MineActivity;
import com.example.zyb.bestmap.nearby.activity.NearbyActivity;
import com.example.zyb.bestmap.route.activity.RouteActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    private EditText et_search;
    private CheckBox cb_night;
    private CheckBox cb_satelliteMap;
    private CheckBox cb_roadCondition;
    private RadioButton rb_nearby;
    private RadioButton rb_route;
    private RadioButton rb_mine;
    private MapView mapView = null;
    private AMap aMap;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double lat;
    private double lon;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    aMapLocation.getLatitude();//获取纬度
                    aMapLocation.getLongitude();//获取经度
                    aMapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    df.format(date);//定位时间
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    aMapLocation.getCity();//城市信息
                    aMapLocation.getDistrict();//城区信息
                    aMapLocation.getStreet();//街道信息
                    aMapLocation.getStreetNum();//街道门牌号信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    lat = aMapLocation.getLatitude();
                    lon = aMapLocation.getLongitude();
                    Log.v("pcw", "lat : " + lat + " lon : " + lon);
                    Log.v("pcw", "Country : " + aMapLocation.getCountry() + " province : " + aMapLocation.getProvince() + " City : " + aMapLocation.getCity() + " District : " + aMapLocation.getDistrict());
                    //设置当前地图显示为当前位置
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 19));
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(lat, lon));
                    markerOptions.title("当前位置");
                    markerOptions.visible(true);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.activity_card_locate));
                    markerOptions.icon(bitmapDescriptor);
                    aMap.addMarker(markerOptions);
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //初始化搜索框控件
        et_search = (EditText) findViewById(R.id.et_search);
        cb_night = (CheckBox) findViewById(R.id.cb_night);
        cb_satelliteMap = (CheckBox) findViewById(R.id.cb_satelliteMap);
        cb_roadCondition = (CheckBox) findViewById(R.id.cb_roadCondition);
        //初始化底部栏控件
        rb_nearby = (RadioButton) findViewById(R.id.rb_nearby);
        rb_route = (RadioButton) findViewById(R.id.rb_route);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        //初始化地图控件
        mapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mapView.onCreate(savedInstanceState);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(aMapLocationListener);
        //初始化Amap对象
        init();
        //操作控件et_search点击事件
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });
        //操作夜间模式切换
        cb_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                } else {
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }
            }
        });
        //操作卫星地图显示模式
        cb_satelliteMap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                } else {
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }
            }
        });
        //操作路况显示事件
        cb_roadCondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aMap.setTrafficEnabled(true);
                } else {
                    aMap.setTrafficEnabled(false);
                }
            }
        });
        //操作控件rb_nearby点击事件
        rb_nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });
        //操作控件et_route点击事件
        rb_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RouteActivity.class);
                startActivity(intent);
            }
        });
        //操作控件et_mine点击事件
        rb_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MineActivity.class);
                startActivity(intent);
            }
        });
    }

    //初始化AMap对象
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        setUpMap();
    }

    private void setUpMap() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //重写方法
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    public AMap getaMap() {
        return aMap;
    }

    public void setaMap(AMap aMap) {
        this.aMap = aMap;
    }
}
