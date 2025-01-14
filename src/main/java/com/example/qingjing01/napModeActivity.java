package com.example.qingjing01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class napModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_nap_mode);






        // 获取 TextView
        TextView shijianxianshi = findViewById(R.id.shijianxianshi);

        // 检查 TextView 是否为 null
        if (shijianxianshi != null) {
            // 获取当前时间
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String currentTime = dateFormat.format(calendar.getTime());

            // 设置当前时间到 TextView
            shijianxianshi.setText(currentTime);
        } else {
            // 打印日志或处理错误
            Log.e("NapModeActivity", "TextView with ID shijianxianshi not found");
        }


        Button xipingButton = findViewById(R.id.xiping044);
        xipingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调整屏幕亮度
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = 0.0f; // 0.0f 表示最暗，1.0f 表示最亮
                getWindow().setAttributes(layoutParams);
            }
        });



        ImageView shezhi02 = findViewById(R.id.shezhi02);
        shezhi02.setOnClickListener(v -> showSettingsDialog());



    }//Oncreate结束

    private void showSettingsDialog() {
        // 创建并显示对话框
        new AlertDialog.Builder(this)
                .setTitle("设置")
                .setMessage("这是一个设置对话框")
                .setPositiveButton("确定", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }


    }

