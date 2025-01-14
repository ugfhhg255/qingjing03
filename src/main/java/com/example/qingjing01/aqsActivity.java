package com.example.qingjing01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.ref.WeakReference;

public class aqsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aqs);


      ImageView imageButton = findViewById(R.id.aqsanniuaaa);
    final boolean[] isOriginal = {true}; // 追踪当前显示的是否为原图

    imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isOriginal[0]) {
                // 如果是原图，切换到第二张图片
                imageButton.setBackgroundResource(R.drawable.yinyuelvdongkai23d);
            } else {
                // 如果是第二张图片，恢复原图
                imageButton.setBackgroundResource(R.drawable.anniuaaa);
            }
            isOriginal[0] = !isOriginal[0]; // 切换状态
        }
    });


        //aqs返回
        ImageButton aqsfanhuibutton = findViewById(R.id.aqsfanhui);
        aqsfanhuibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showaqsFanhuiDialog();
            }
        });

        ImageView xiaoqibaocun = findViewById(R.id.aqsbaocun);
        xiaoqibaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog_baocun();
            }
        });

        ImageView xiaoqichongzhi = findViewById(R.id.aqschongzhi);
        xiaoqichongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });




    }//Oncreate结束

    private void showCustomDialog_baocun() {
        // 创建AlertDialog实例的构建器
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.RoundedCornersDialog);
        // 通过布局 inflater 将自定义布局文件转化为View对象
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom_baocun, null);
        // 设置对话框的视图为上述转化得到的View对象
        builder.setView(dialogView);

        // 创建AlertDialog实例
        android.app.AlertDialog dialog = builder.create();

        // 通过ID找到确认和取消按钮
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        // 为确认按钮设置点击事件监听器
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重置设置参数的逻辑

                // 关闭对话框
                dialog.dismiss();

                // 在其他活动中返回 MainActivity 时
                Intent intent = new Intent(aqsActivity.this, MainActivity.class);
                intent.putExtra("showToastaqs", true);  // 设置显示Toast的标记
                startActivity(intent);
                finish();

            }
        });

        // 为取消按钮设置点击事件监听器
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭对话框
                dialog.dismiss();
            }
        });
        // 显示对话框
        dialog.show();
    }



    private void showCustomDialog() {
        // 创建AlertDialog实例的构建器
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.RoundedCornersDialog);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 通过布局 inflater 将自定义布局文件转化为View对象
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom, null);
        // 设置对话框的视图为上述转化得到的View对象
        builder.setView(dialogView);

        // 创建AlertDialog实例
        android.app.AlertDialog dialog = builder.create();

        // 通过ID找到确认和取消按钮
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        // 为确认按钮设置点击事件监听器
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重置设置参数的逻辑

                // 关闭对话框
                dialog.dismiss();
                // 创建 Intent 并添加标记
                Intent intent = new Intent(aqsActivity.this, MainActivity.class);
                intent.putExtra("showToastaqs", true);  // 添加标记
                // 返回 MainActivity
                startActivity(intent);
                finish();
            }
        });

        // 为取消按钮设置点击事件监听器
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭对话框
                dialog.dismiss();
            }
        });
        // 显示对话框
        dialog.show();
    }

    private void showaqsFanhuiDialog() {
        // 使用 try-with-resources 确保资源正确释放
        try {
            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout_aqsfanhui, null);

            AlertDialog dialog = new AlertDialog.Builder(this, R.style.RoundedCornersDialog)
                .setView(dialogView)
                .setCancelable(true)
                .create();

            // 使用弱引用避免内存泄漏
            WeakReference<AlertDialog> dialogWeakRef = new WeakReference<>(dialog);

            // 设置按钮点击事件
            dialogView.findViewById(R.id.xingshenfanhuiqueding).setOnClickListener(v -> {
                AlertDialog dialogInstance = dialogWeakRef.get();
                if (dialogInstance != null && dialogInstance.isShowing()) {
                    Intent intent = new Intent(this, MainActivity.class)
                        .putExtra("SHOW_SAVE_TOAST", true)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    dialogInstance.dismiss();
                    startActivity(intent);
                    finish();
                }
            });

            dialogView.findViewById(R.id.xingshenfanhuiquxiao).setOnClickListener(v -> {
                AlertDialog dialogInstance = dialogWeakRef.get();
                if (dialogInstance != null && dialogInstance.isShowing()) {
                    dialogInstance.dismiss();
                }
            });

            // 设置对话框消失监听器
            dialog.setOnDismissListener(dialogInterface -> {
                dialogInterface.dismiss();
                dialogWeakRef.clear();
            });

            dialog.show();

        } catch (Exception e) {
            Log.e("DialogError", "Error showing dialog: " + e.getMessage());
        }
    }

}