package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheshouye.api.client.WeizhangClient;
import com.cheshouye.api.client.WeizhangIntentService;
import com.cheshouye.api.client.json.CarInfo;
import com.cheshouye.api.client.json.CityInfoJson;
import com.cheshouye.api.client.json.InputConfigJson;
import com.example.zyb.bestmap.R;

/**
 * Created by zyb on 2016/9/21.
 */
public class ViolationActivity extends Activity {
    private String defaultChepai = "��";
    private TextView short_name;
    private TextView query_city;
    private View btn_cpsz;
    private Button btn_query;
    private EditText chepai_number;
    private EditText chejia_number;
    private EditText engine_number;
    private View popXSZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_violation);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("����Υ�²�ѯ");
        Log.d("��ʼ���������", "");
        Intent weizhangIntent = new Intent(this, WeizhangIntentService.class);
        weizhangIntent.putExtra("appId", 2145);
        weizhangIntent.putExtra("appKey", "5b47d5febb23bbcc4b8bf1c17e2697de");
        startService(weizhangIntent);
        query_city = (TextView) findViewById(R.id.cx_city);
        chepai_number = (EditText) findViewById(R.id.chepai_number);
        chejia_number = (EditText) findViewById(R.id.chejia_number);
        engine_number = (EditText) findViewById(R.id.engine_number);
        short_name = (TextView) findViewById(R.id.chepai_sz);
        btn_cpsz = (View) findViewById(R.id.btn_cpsz);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_cpsz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ViolationActivity.this, ShortNameList.class);
                intent.putExtra("select_short_name", short_name.getText());
                startActivityForResult(intent, 0);
            }
        });
        query_city.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ViolationActivity.this, ProvinceList.class);
                startActivityForResult(intent, 1);
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CarInfo car = new CarInfo();
                String quertCityStr = null;
                String quertCityIdStr = null;
                final String shortNameStr = short_name.getText().toString().trim();
                final String chepaiNumberStr = chepai_number.getText().toString().trim();
                if (query_city.getText() != null && !query_city.getText().equals("")) {
                    quertCityStr = query_city.getText().toString().trim();
                }
                if (query_city.getTag() != null && !query_city.getTag().equals("")) {
                    quertCityIdStr = query_city.getTag().toString().trim();
                    car.setCity_id(Integer.parseInt(quertCityIdStr));
                }
                final String chejiaNumberStr = chejia_number.getText().toString().trim();
                final String engineNumberStr = engine_number.getText().toString().trim();
                Intent intent = new Intent();
                car.setChejia_no(chejiaNumberStr);
                car.setChepai_no(shortNameStr + chepaiNumberStr);
                car.setEngine_no(engineNumberStr);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carInfo", car);
                intent.putExtras(bundle);
                boolean result = checkQueryItem(car);
                if (result) {
                    intent.setClass(ViolationActivity.this, WeizhangResult.class);
                    startActivity(intent);
                }
            }
        });
        short_name.setText(defaultChepai);
        popXSZ = (View) findViewById(R.id.popXSZ);
        popXSZ.setOnTouchListener(new popOnTouchListener());
        hideShowXSZ();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case 0:
                Bundle bundle = data.getExtras();
                String ShortName = bundle.getString("short_name");
                short_name.setText(ShortName);
                break;
            case 1:
                Bundle bundle1 = data.getExtras();
                // String cityName = bundle1.getString("city_name");
                String cityId = bundle1.getString("city_id");
                // query_city.setText(cityName);
                // query_city.setTag(cityId);
                // InputConfigJson inputConfig =
                // WeizhangClient.getInputConfig(Integer.parseInt(cityId));
                // System.out.println(inputConfig.toJson());
                setQueryItem(Integer.parseInt(cityId));
                break;
        }
    }

    private void setQueryItem(int cityId) {
        InputConfigJson cityConfig = WeizhangClient.getInputConfig(cityId);
        if (cityConfig != null) {
            CityInfoJson city = WeizhangClient.getCity(cityId);
            query_city.setText(city.getCity_name());
            query_city.setTag(cityId);
            int len_chejia = cityConfig.getClassno();
            int len_engine = cityConfig.getEngineno();
            View row_chejia = (View) findViewById(R.id.row_chejia);
            View row_engine = (View) findViewById(R.id.row_engine);
            if (len_chejia == 0) {
                row_chejia.setVisibility(View.GONE);
            } else {
                row_chejia.setVisibility(View.VISIBLE);
                setMaxlength(chejia_number, len_chejia);
                if (len_chejia == -1) {
                    chejia_number.setHint("�������������ܺ�");
                } else if (len_chejia > 0) {
                    chejia_number.setHint("�����복�ܺź�" + len_chejia + "λ");
                }
            }
            if (len_engine == 0) {
                row_engine.setVisibility(View.GONE);
            } else {
                row_engine.setVisibility(View.VISIBLE);
                setMaxlength(engine_number, len_engine);
                if (len_engine == -1) {
                    engine_number.setHint("��������������������");
                } else if (len_engine > 0) {
                    engine_number.setHint("�����뷢������" + len_engine + "λ");
                }
            }
        }
    }

    private boolean checkQueryItem(CarInfo car) {
        if (car.getCity_id() == 0) {
            Toast.makeText(ViolationActivity.this, "��ѡ���ѯ��", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (car.getChepai_no().length() != 7) {
            Toast.makeText(ViolationActivity.this, "������ĳ��ƺ�����", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (car.getCity_id() > 0) {
            InputConfigJson inputConfig = WeizhangClient.getInputConfig(car.getCity_id());
            int engineno = inputConfig.getEngineno();
            int registno = inputConfig.getRegistno();
            int classno = inputConfig.getClassno();
            if (classno > 0) {
                if (car.getChejia_no().equals("")) {
                    Toast.makeText(ViolationActivity.this, "���복�ܺŲ�Ϊ��", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (car.getChejia_no().length() != classno) {
                    Toast.makeText(ViolationActivity.this, "���복�ܺź�" + classno + "λ", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else if (classno < 0) {
                if (car.getChejia_no().length() == 0) {
                    Toast.makeText(ViolationActivity.this, "����ȫ�����ܺ�", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            if (engineno > 0) {
                if (car.getEngine_no().equals("")) {
                    Toast.makeText(ViolationActivity.this, "���뷢�����Ų�Ϊ��", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (car.getEngine_no().length() != engineno) {
                    Toast.makeText(ViolationActivity.this, "���뷢�����ź�" + engineno + "λ", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else if (engineno < 0) {
                if (car.getEngine_no().length() == 0) {
                    Toast.makeText(ViolationActivity.this, "����ȫ����������", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            if (registno > 0) {
                if (car.getRegister_no().equals("")) {
                    Toast.makeText(ViolationActivity.this, "����֤���Ų�Ϊ��", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (car.getRegister_no().length() != registno) {
                    Toast.makeText(ViolationActivity.this, "����֤���ź�" + registno + "λ", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else if (registno < 0) {
                if (car.getRegister_no().length() == 0) {
                    Toast.makeText(ViolationActivity.this, "����ȫ��֤����", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void setMaxlength(EditText et, int maxLength) {
        if (maxLength > 0) {
            et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        } else {
            et.setFilters(new InputFilter[]{});
        }
    }

    private void hideShowXSZ() {
        View btn_help1 = (View) findViewById(R.id.ico_chejia);
        View btn_help2 = (View) findViewById(R.id.ico_engine);
        Button btn_closeXSZ = (Button) findViewById(R.id.btn_closeXSZ);
        btn_help1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popXSZ.setVisibility(View.VISIBLE);
            }
        });
        btn_help2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popXSZ.setVisibility(View.VISIBLE);
            }
        });
        btn_closeXSZ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popXSZ.setVisibility(View.GONE);
            }
        });
    }

    private class popOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            popXSZ.setVisibility(View.GONE);
            return true;
        }
    }
}