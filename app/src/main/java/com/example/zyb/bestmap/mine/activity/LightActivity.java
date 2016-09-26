package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zyb.bestmap.R;

/**
 * Created by zyb on 2016/9/19.
 */
public class LightActivity extends Activity {
    private ImageButton ib_back;
    private CheckBox cb_light;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        cb_light = (CheckBox) findViewById(R.id.cb_light);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LightActivity.this, MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cb_light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(LightActivity.this, "手电筒开", Toast.LENGTH_SHORT).show();
                    camera = Camera.open();
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                    //开始亮灯
                    camera.startPreview();
                } else {
                    Toast.makeText(LightActivity.this, "手电筒关", Toast.LENGTH_SHORT).show();
                    //关掉亮灯
                    camera.stopPreview();
                    //关掉照相机
                    camera.release();
                }
            }
        });
    }
}
