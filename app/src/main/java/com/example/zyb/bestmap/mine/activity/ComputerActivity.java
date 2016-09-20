package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zyb.bestmap.R;

/**
 * Created by zyb on 2016/9/20.
 */
public class ComputerActivity extends Activity {
    private ImageButton ib_back;
    private EditText et_result;//显示器
    StringBuffer stringBuffer;
    String number_1 = "";
    String number_2 = "";
    private boolean clicked = false;
    byte bytes = 0;
    //数字按钮
    private Button bt_num0;
    private Button bt_num1;
    private Button bt_num2;
    private Button bt_num3;
    private Button bt_num4;
    private Button bt_num5;
    private Button bt_num6;
    private Button bt_num7;
    private Button bt_num8;
    private Button bt_num9;
    private Button bt_point;
    //功能按钮
    private Button bt_add;
    private Button bt_sub;
    private Button bt_multiply;
    private Button bt_division;
    private Button bt_equal;
    private Button bt_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        stringBuffer = new StringBuffer();
        et_result = (EditText) findViewById(R.id.et_result);
        bt_num0 = (Button) findViewById(R.id.bt_num0);
        bt_num1 = (Button) findViewById(R.id.bt_num1);
        bt_num2 = (Button) findViewById(R.id.bt_num2);
        bt_num3 = (Button) findViewById(R.id.bt_num3);
        bt_num4 = (Button) findViewById(R.id.bt_num4);
        bt_num5 = (Button) findViewById(R.id.bt_num5);
        bt_num6 = (Button) findViewById(R.id.bt_num6);
        bt_num7 = (Button) findViewById(R.id.bt_num7);
        bt_num8 = (Button) findViewById(R.id.bt_num8);
        bt_num9 = (Button) findViewById(R.id.bt_num9);
        bt_point = (Button) findViewById(R.id.bt_point);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_sub = (Button) findViewById(R.id.bt_sub);
        bt_multiply = (Button) findViewById(R.id.bt_multiply);
        bt_division = (Button) findViewById(R.id.bt_division);
        bt_equal = (Button) findViewById(R.id.bt_equal);
        bt_clear = (Button) findViewById(R.id.bt_clear);
        //返回键
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComputerActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //操作控件
        bt_num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("0");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("1");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("2");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("3");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("4");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("5");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("6");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("7");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("8");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                stringBuffer.append("9");
                et_result.setText(stringBuffer.toString());
            }
        });
        bt_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    et_result.setText("");
                    clicked = false;
                    stringBuffer.setLength(0);
                }
                if (et_result.getText().toString().indexOf(".") == -1) {//已经有.了
                    if (stringBuffer.length() == 0) {
                        stringBuffer.append("0.");
                    } else {
                        stringBuffer.append(".");
                    }
                    et_result.setText(stringBuffer.toString());
                }
            }
        });
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_1 = et_result.getText().toString();
                bytes = 1;
                clicked = true;
            }
        });
        bt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_1 = et_result.getText().toString();
                bytes = 2;
                clicked = true;
            }
        });
        bt_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_1 = et_result.getText().toString();
                bytes = 3;
                clicked = true;
            }
        });
        bt_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_1 = et_result.getText().toString();
                bytes = 4;
                clicked = true;
            }
        });
        bt_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_2 = et_result.getText().toString();
                if (number_1.equals("") && number_2.equals("")) {
                    return;
                }
                Double d1 = Double.valueOf(number_1);
                Double d2 = Double.valueOf(number_2);
                Double r = 0.0;
                switch (bytes) {
                    case 1:
                        r = d1 + d2;
                        break;
                    case 2:
                        r = d1 - d2;
                        break;
                    case 3:
                        r = d1 * d2;
                        break;
                    case 4:
                        r = d1 / d2;
                        break;
                }
                et_result.setText(r.toString());
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_result.setText("");
                number_1 = "";
                number_2 = "";
                bytes = 0;
                clicked = false;
                stringBuffer.setLength(0);
            }
        });
    }
}
