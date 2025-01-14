package com.example.qingjing01;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dia_seat_position extends AppCompatActivity {
    private int currentAngle = 60; // 初始角度值
    private TextView textLeft;
    private ImageView arrowRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dia_seat_position);

//        // 获取视图引用
//        TextView textView = findViewById(R.id.text_left);
//        ImageView arrowRight = findViewById(R.id.arrow_right02d);
//        // 设置点击监听器
//        arrowRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 获取当前文本
//                String currentText = textView.getText().toString();
//                // 提取数字部分
//                int currentPercentage = Integer.parseInt(currentText.replaceAll("[^0-9]", ""));
//                // 增加10%
//                int newPercentage = currentPercentage + 10;
//                // 更新文本
//                textView.setText("靠背角度" + newPercentage + "%");
//            }
//        });

    }//OnCreate结束

}