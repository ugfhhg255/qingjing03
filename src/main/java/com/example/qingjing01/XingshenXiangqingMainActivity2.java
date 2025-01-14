package com.example.qingjing01;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.ref.WeakReference;
import java.util.Locale;

public class XingshenXiangqingMainActivity2 extends AppCompatActivity {
    private int currentTemperature = 24;
    private TextView currentSelectedTextView = null;
    private TextView mainTextView; // 声明为类成员变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xingshen_xiangqing_main2);


//        ImageButton xingshenfanhui = findViewById(R.id.xingshenfanhui);
//        xingshenfanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(XingshenXiangqingMainActivity2.this, MainActivity.class);
//                startActivity(intent);
//                finish(); // 可选：结束当前活动
//            }
//        });
        ImageView xingshenxiangfen = findViewById(R.id.xingshenxiangfen);
        xingshenxiangfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showXiangfenDialog();
            }
        });

        ImageView xingshenmeitiyinliang = findViewById(R.id.xingshenmeitiyinliang); // 替换为你的 ImageView ID
        xingshenmeitiyinliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        ImageView kongtiaowendu234 = findViewById(R.id.xingshenkongtiaowendu);
        kongtiaowendu234.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTemperatureDialogxingshen();
            }
        });

        ImageButton xingshenfanhui34 = findViewById(R.id.xingshenfanhui);
        xingshenfanhui34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showXingshenFanhuiDialog();
            }
        });

        ImageView xiaoqibaocun = findViewById(R.id.xingshenbaocun);
        xiaoqibaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog_baocun();
            }
        });

        ImageView xiaoqichongzhi = findViewById(R.id.xingshenshuaxin);
        xiaoqichongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog_chongzhi();
            }
        });

        // 在主布局中找到TextView
        mainTextView = findViewById(R.id.meitiyinliangziti);

    }//OnCreate结束

        private void showTemperatureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_temperature, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        TextView wendujian = dialogView.findViewById(R.id.wendujian);
        TextView wenduxianshidi = dialogView.findViewById(R.id.wenduxianshidi);
        TextView wenduxianshi = dialogView.findViewById(R.id.wenduxianshi);
        TextView wenduxianshigao = dialogView.findViewById(R.id.wenduxianshigao);
        TextView wendujia01 = dialogView.findViewById(R.id.wendujia01);
        TextView shangsheng02 = dialogView.findViewById(R.id.shangsheng02);
        TextView xiajiang02 = dialogView.findViewById(R.id.xiajiang02);
        TextView kongtiaowenduzidingyi = dialogView.findViewById(R.id.kongtiaowenduzidingyi);
        TextView kongtiaowenduxianshi = findViewById(R.id.kongtiaowenduxianshi);

        // 显示初始温度
        updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);

        // 减少温度
        wendujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTemperature > 16) {
                    currentTemperature--;
                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);

                    if (currentSelectedTextView != null) {
                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
                    }
                    currentSelectedTextView = null;
                }
            }
        });

        // 增加温度
        wendujia01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTemperature < 30) {
                    currentTemperature++;
                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);

                    if (currentSelectedTextView != null) {
                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
                    }
                    currentSelectedTextView = null;
                }
            }
        });

        // 上升 2°C
        shangsheng02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTemperature <= 28) {
                    currentTemperature += 2;
                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);

                    if (currentSelectedTextView != null) {
                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
                    }
                    shangsheng02.setBackgroundColor(Color.parseColor("#4285F4"));
                    currentSelectedTextView = shangsheng02;
                }
            }
        });

        // 下降 2°C
        xiajiang02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTemperature >= 18) {
                    currentTemperature -= 2;
                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);

                    if (currentSelectedTextView != null) {
                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
                    }
                    xiajiang02.setBackgroundColor(Color.parseColor("#4285F4"));
                    currentSelectedTextView = xiajiang02;
                }
            }
        });

        kongtiaowenduzidingyi.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (currentSelectedTextView != null) {
            currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
        }
        kongtiaowenduzidingyi.setBackgroundColor(Color.parseColor("#4285F4"));
        currentSelectedTextView = kongtiaowenduzidingyi;

        // 创建自定义温度选择对话框
        AlertDialog.Builder customBuilder = new AlertDialog.Builder(v.getContext());
        View customView = getLayoutInflater().inflate(R.layout.dialog_custom_temperature, null);

        NumberPicker temperaturePicker = customView.findViewById(R.id.temperaturePicker);
        Button btnConfirm = customView.findViewById(R.id.btn_confirm);  // 添加确定按钮
        Button btnCancel = customView.findViewById(R.id.btn_cancel);    // 添加取消按钮

        // 设置NumberPicker的范围和当前值
        temperaturePicker.setMinValue(16);
        temperaturePicker.setMaxValue(30);
        temperaturePicker.setValue(currentTemperature);
        temperaturePicker.setWrapSelectorWheel(false);

        customBuilder.setView(customView);

        // 创建对话框
        AlertDialog customDialog = customBuilder.create();

        // 设置确定按钮点击事件
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTemperature = temperaturePicker.getValue();
                updateAllTemperatures(wenduxianshidi, wenduxianshi,
                                    wenduxianshigao, kongtiaowenduxianshi);
                customDialog.dismiss();
            }
        });

        // 设置取消按钮点击事件
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });

        // 显示对话框
        customDialog.show();
    }
});

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateAllTemperatures(TextView wenduxianshidi, TextView wenduxianshi,
                                     TextView wenduxianshigao, TextView kongtiaowenduxianshi) {
        wenduxianshidi.setText((currentTemperature - 1) + "℃");
        wenduxianshi.setText(currentTemperature + "℃");
        wenduxianshigao.setText((currentTemperature + 1) + "℃");
        kongtiaowenduxianshi.setText("空调温度\n" + currentTemperature + "℃");
    }



    private void showVolumeDialog(TextView mediaVolumeTextView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout_meitiyinliang, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        SeekBar mediaVolumeSeekBar = dialogView.findViewById(R.id.yinliangSeekbar);
        mediaVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 更新 TextView 显示的媒体音量
                mediaVolumeTextView.setText("媒体音量\n" + (progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 用户开始触摸 SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 用户停止触摸 SeekBar
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


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
                Intent intent = new Intent(XingshenXiangqingMainActivity2.this, MainActivity.class);
                intent.putExtra("showToastxingshen", true);  // 设置显示Toast的标记
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



    private void showCustomDialog_chongzhi() {
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
                Intent intentxingshen = new Intent(XingshenXiangqingMainActivity2.this, MainActivity.class);

                // 返回 MainActivity
                startActivity(intentxingshen);
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

    private AlertDialog dialog;  // 声明为类成员变量，方便管理生命周期

    private void showXingshenFanhuiDialog() {
        if (isFinishing()) return;  // 防止 Activity 已销毁时显示对话框

        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_layout_xingshenfanhui, null);

        // 在设置监听器之前先找到视图引用
        ImageView quedingButton = dialogView.findViewById(R.id.xingshenfanhuiqueding);
        ImageView quxiaoButton = dialogView.findViewById(R.id.xingshenfanhuiquxiao);

        // 使用弱引用避免内存泄漏
        WeakReference<XingshenXiangqingMainActivity2> activityRef =
                new WeakReference<>(this);

        dialog = new AlertDialog.Builder(this, R.style.RoundedCornersDialog)
                .setView(dialogView)
                .setCancelable(true)
                .create();

        // 使用 lambda 表达式简化代码
        quedingButton.setOnClickListener(v -> {
            XingshenXiangqingMainActivity2 activity = activityRef.get();
            if (activity != null && !activity.isFinishing()) {
                activity.saveChanges();

                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("showToast", true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // 清除栈顶的 Activity
                activity.startActivity(intent);

                dismissDialog();
                activity.finish();
            }
        });

        quxiaoButton.setOnClickListener(v -> dismissDialog());

        dialog.show();
    }

// 安全地关闭对话框
    private void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;  // 释放引用
        }
    }

// 在 Activity 销毁时确保对话框被关闭
@Override
    protected void onDestroy() {
        dismissDialog();
        super.onDestroy();
    }



//    private void showXingshenFanhuiDialog() {
////        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_layout_xingshenfanhui, null);
//        builder.setView(dialogView);
//        builder.setCancelable(true);
//
//        // 获取“确定”和“取消”按钮的引用
//        ImageView quedingButton = dialogView.findViewById(R.id.xingshenfanhuiqueding);
//        ImageView quxiaoButton = dialogView.findViewById(R.id.xingshenfanhuiquxiao);
//
//        // 创建对话框
//        AlertDialog dialog = builder.create();
//
//        // 设置“确定”按钮的点击事件
//        quedingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 在这里实现保存功能
//                saveChanges();
//
//                // 返回 MainActivity 并传递标志
//                Intent intent02 = new Intent(XingshenXiangqingMainActivity2.this, MainActivity.class);
//                intent02.putExtra("showToast", true);
//                startActivity(intent02);
//
//                dialog.dismiss(); // 关闭对话框
//                finish(); // 可选：结束当前活动以防止返回
//            }
//        });
//
//        // 设置“取消”按钮的点击事件
//        quxiaoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 在这里实现取消功能
//                dialog.dismiss(); // 关闭对话框
//                finish(); // 可选：结束当前活动以防止返回
//
//            }
//        });
//
//
//        dialog.show();
//    }

    // 示例保存功能
    private void saveChanges() {
        // 实现保存逻辑，例如保存到数据库或文件
        Toast.makeText(this, "内容已保存", Toast.LENGTH_SHORT).show();
    }


    private void showTemperatureDialogxingshen() {
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.RoundedCornersDialog);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_temperature_xingshen, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        TextView wendujian = dialogView.findViewById(R.id.wendujian);
        TextView wenduxianshi = dialogView.findViewById(R.id.wenduxianshi);
        TextView wendujia01 = dialogView.findViewById(R.id.wendujia01);

        // 显示初始温度
        wenduxianshi.setText(currentTemperature + "℃");

        // 减少温度
        wendujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTemperature > 16) { // 假设最低温度为16℃
                    currentTemperature--;
                    wenduxianshi.setText(currentTemperature + "℃");
                } else if (currentTemperature == 16) {
                    currentTemperature--;
                    wenduxianshi.setText("LO");
                }
            }
        });

        // 增加温度
        wendujia01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTemperature < 30) { // 假设最高温度为30℃
                    currentTemperature++;
                    wenduxianshi.setText(currentTemperature + "℃");
                } else if (currentTemperature == 30) {
                    currentTemperature++;
                    wenduxianshi.setText("HI");
                }
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }




//    private void showCustomDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
////        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_layout_meitiyinliang, null);
//        builder.setView(dialogView);
//
//        // 不设置对话框按钮
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }



// 添加类成员变量
    private static final String PREFS_NAME = "MediaVolumeSettings";
    private static final String KEY_VOLUME = "media_volume";
    private SharedPreferences preferences;

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_layout_meitiyinliang, null);
        builder.setView(dialogView);

        // 找到对话框中的控件
        SeekBar yinliangSeekbar = dialogView.findViewById(R.id.yinliangSeekbar);

        // 获取保存的音量值（范围0-9）
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVolume = preferences.getInt(KEY_VOLUME, 4); // 默认值为4（显示为5）
        yinliangSeekbar.setProgress(savedVolume);

        // 设置初始值
        updateVolumeText(mainTextView, savedVolume);

        // 设置监听器
        yinliangSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateVolumeText(mainTextView, progress);
                // 保存当前音量值
                preferences.edit().putInt(KEY_VOLUME, progress).apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        AlertDialog dialog = builder.create();
        // 添加对话框关闭监听器
        dialog.setOnDismissListener(dialogInterface -> {
            // 可以在这里进行一些资源清理
        });
        dialog.show();
    }

// 优化更新文本的方法
    private void updateVolumeText(TextView textView, int progress) {
        if (textView != null) {
            // progress 是 0-9，显示为 1-10
            int displayValue = Math.min(Math.max(progress + 1, 1), 10); // 确保值在1-10范围内
            textView.setText(String.format(Locale.getDefault(), "媒体音量\n %d", displayValue));
        }
    }




    private void showXiangfenDialog() {
        final Dialog xiangfenDialog = new Dialog(this, R.style.RoundedCornersDialog);
//        final Dialog xiangfenDialog = new Dialog(this);
        xiangfenDialog.setContentView(R.layout.dialog_xiangfen);

        // 设置Dialog样式
        Window window = xiangfenDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
            window.setAttributes(params);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // 找到Dialog中的视图
        TextView lightFragrance = xiangfenDialog.findViewById(R.id.danxiang);
        TextView mediumFragrance = xiangfenDialog.findViewById(R.id.qingxiang);
        TextView strongFragrance = xiangfenDialog.findViewById(R.id.nongxiang);

        // 默认选中清香，设置为蓝色
        mediumFragrance.setBackgroundColor(getResources().getColor(R.color.qblue));

        // 设置强度选择的点击事件
        View.OnClickListener intensityListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重置所有选项为灰色
                lightFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
                mediumFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
                strongFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));

                // 将被点击的选项设置为蓝色
                TextView selected = (TextView) v;
                selected.setBackgroundColor(getResources().getColor(R.color.qblue));
            }
        };
        lightFragrance.setOnClickListener(intensityListener);
        mediumFragrance.setOnClickListener(intensityListener);
        strongFragrance.setOnClickListener(intensityListener);

        // 设置香氛模块的点击事件
        TextView blackTemptation = xiangfenDialog.findViewById(R.id.heiseyouh);
        TextView windRising = xiangfenDialog.findViewById(R.id.fengqilian);
        TextView forestTime = xiangfenDialog.findViewById(R.id.mulinshiguang);

        // 默认选中沐林时光，设置为蓝色
        forestTime.setBackgroundColor(getResources().getColor(R.color.qblue));

        View.OnClickListener fragranceListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 重置所有选项为灰色
                blackTemptation.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
                windRising.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
                forestTime.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));

                // 将被点击的选项设置为蓝色
                TextView selected = (TextView) v;
                selected.setBackgroundColor(getResources().getColor(R.color.qblue));
            }
        };

        blackTemptation.setOnClickListener(fragranceListener);
        windRising.setOnClickListener(fragranceListener);
        forestTime.setOnClickListener(fragranceListener);

        xiangfenDialog.show();
    }




}