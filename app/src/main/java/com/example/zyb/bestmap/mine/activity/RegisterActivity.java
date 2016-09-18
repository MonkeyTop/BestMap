package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyb.bestmap.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.mob.tools.utils.R.getStringRes;

/**
 * Created by zyb on 2016/9/14.
 */
public class RegisterActivity extends Activity {
    private ImageButton ib_back;
    private TextView tv_agreement;
    private EditText et_phone;
    private EditText et_password;
    private EditText et_code;
    private Button bt_getcode;
    private CheckBox cb_check;
    private Button bt_register;
    private TextView tv_hint;
    private String iPhone;
    private String iPassword;
    private String iCode;
    private int time = 60;
    private boolean flag = true;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化控件
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        tv_agreement = (TextView) findViewById(R.id.tv_agreement);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);
        et_code = (EditText) findViewById(R.id.et_code);
        bt_getcode = (Button) findViewById(R.id.bt_getcode);
        cb_check = (CheckBox) findViewById(R.id.cb_check);
        bt_register = (Button) findViewById(R.id.bt_register);
        tv_hint = (TextView) findViewById(R.id.tv_hint);
        //创建SharedPreferences存储,Context.MODE_PRIVATE：只允许自己的程序访问
        SharedPreferences sharedPreferences = getSharedPreferences("FILE_NAME", Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString("PHONE", "");
        String pwd = sharedPreferences.getString("PWD", "");
        et_phone.setText(phone);
        et_password.setText(pwd);
        //返回键
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //操作协议控件
        tv_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, AgreementActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //获取验证码点击事件
        bt_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_phone.getText().toString().trim()) && !TextUtils.isEmpty(et_password.getText().toString().trim())) {
                    if (et_phone.getText().toString().trim().length() == 11 && !TextUtils.isEmpty(et_password.getText().toString().trim())) {
                        iPhone = et_phone.getText().toString().trim();
                        iPassword = et_password.getText().toString().trim();
                        SMSSDK.getVerificationCode("86", iPhone);
                        et_code.requestFocus();
                        bt_getcode.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(RegisterActivity.this, "请输入正确手机号或密码", Toast.LENGTH_SHORT).show();
                        et_phone.requestFocus();
                        et_password.requestFocus();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入你的手机号或正确密码", Toast.LENGTH_SHORT).show();
                    et_phone.requestFocus();
                    et_password.requestFocus();
                }
            }
        });
        //确认注册点击事件
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_check.isChecked()) {
                    if (!TextUtils.isEmpty(et_code.getText().toString().trim())) {
                        if (et_code.getText().toString().trim().length() == 4) {
                            iCode = et_code.getText().toString().trim();
                            SMSSDK.submitVerificationCode("86", iPhone, iCode);
                            flag = false;
                            editor = getSharedPreferences("FILE_NAME", MODE_PRIVATE).edit();
                            String phone = et_phone.getText().toString();
                            String password = et_password.getText().toString();
                            editor.putString("PHONE", phone);
                            editor.putString("PWD", password);
                            editor.commit();
                            //成功注册后跳转到登录界面
                            Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                            et_code.requestFocus();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                        et_code.requestFocus();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "未经本协议同意不予注册权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cn.smssdk.SMSSDK.initSDK(this, "171ec3fd783f4", "f61d25f601940d45564e6340ea58dcf5");
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, result, data);
                Message message = new Message();
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                handler.sendMessage(message);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    //验证码送成功后提示文字
    private void reminderText() {
        tv_hint.setVisibility(View.VISIBLE);
        handlerText.sendEmptyMessageDelayed(1, 1000);
    }

    Handler handlerText = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (time > 0) {
                    tv_hint.setText("验证码已发送" + time + "秒");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                } else {
                    tv_hint.setText("提示信息");
                    time = 60;
                    tv_hint.setVisibility(View.GONE);
                    bt_getcode.setVisibility(View.VISIBLE);
                }
            } else {
                et_code.setText("");
                tv_hint.setText("提示信息");
                time = 60;
                tv_hint.setVisibility(View.GONE);
                bt_getcode.setVisibility(View.VISIBLE);
            }
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    handlerText.sendEmptyMessage(2);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (flag) {
                    bt_getcode.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    et_phone.requestFocus();
                } else {
                    ((Throwable) data).printStackTrace();
                    int resId = getStringRes(RegisterActivity.this, "smssdk_network_error");
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    et_code.selectAll();
                    if (resId > 0) {
                        Toast.makeText(RegisterActivity.this, resId, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}

