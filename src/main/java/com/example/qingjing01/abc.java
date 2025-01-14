package com.example.qingjing01;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class abc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abc);

        TemperatureSliderView temperatureSlider = findViewById(R.id.temperatureSlider);
        temperatureSlider.setOnTemperatureChangeListener(new TemperatureSliderView.OnTemperatureChangeListener() {
            @Override
            public void onTemperatureChanged(float temperature) {
                // 处理温度改变事件
                Toast.makeText(abc.this,
                    "温度设置为: " + (int)temperature + "°C",
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
}