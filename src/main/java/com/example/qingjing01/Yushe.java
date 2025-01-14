package com.example.qingjing01;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Yushe extends AppCompatActivity {
    // 在类的顶部声明变量
    private int selectedMinutes = 30;  // 默认30分钟
    public NumberPicker timePicker;
    private SeekBar brightnessSeekBar;
    private static final int REQUEST_CODE_WRITE_SETTINGS = 1001;

    private ImageView currentSelected = null;
    private LinearLayout mainLayout;
    private int minutes = 10; // 初始分钟数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yushe);


        timePicker = findViewById(R.id.time_picker);

        // 计算显示的值的数量（5-60分钟，间隔5分钟）
        int minValue = 0;  // 对应5分钟
        int maxValue = 11; // 对应60分钟
        timePicker.setMinValue(minValue);
        timePicker.setMaxValue(maxValue);
        timePicker.setValue(5);  // 默认值设为25分钟（索引5对应30分钟）

        timePicker.setWrapSelectorWheel(true);

        // 设置显示格式，将索引转换为实际分钟数
        timePicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int minutes = (value * 5) + 5;  // 将索引转换为实际分钟数
                return minutes + " 分钟";
            }
        });

// 在原有的监听器中更新 selectedMinutes
        timePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                selectedMinutes = (newVal * 5) + 5;  // 将索引转换为实际分钟数并保存
//      Toast.makeText(YusheActivity.this,
//          "设置时间: " + selectedMinutes + " 分钟",
//          Toast.LENGTH_SHORT).show();
            }
        });





        // 检查权限
//        checkAndRequestPermission();
//
//        // 初始化亮度控制
//        initBrightnessControl();

//        initBrightnessControl();

        mainLayout = findViewById(R.id.main);
        // 创建图片和背景的映射关系
        HashMap<ImageView, Integer> backgroundMap = new HashMap<>();

        ImageView senlinImage = findViewById(R.id.senlin34);
        ImageView yutianImage = findViewById(R.id.yutian346);
        ImageView xiayeImage = findViewById(R.id.xiayeji45);
        ImageView gaoyuanImage = findViewById(R.id.gaoyuan9jgr);
        ImageView haitanImage = findViewById(R.id.haitan945);

        // 设置映射关系
        backgroundMap.put(senlinImage, R.drawable.asenlin);
        backgroundMap.put(yutianImage, R.drawable.pic_rain);
        backgroundMap.put(xiayeImage, R.drawable.pic_summer_night);
        backgroundMap.put(gaoyuanImage, R.drawable.pic_plateau);
        backgroundMap.put(haitanImage, R.drawable.pic_beach);

        // 为所有图片设置点击事件
        for (ImageView imageView : backgroundMap.keySet()) {
            setupImageClick(imageView, backgroundMap.get(imageView));
        }


//        ImageView senlinImage = findViewById(R.id.senlin34);
//        ImageView yutianImage = findViewById(R.id.yutian346);
//        ImageView xiayeImage = findViewById(R.id.xiayeji45);
//        ImageView gaoyuanImage = findViewById(R.id.gaoyuan9jgr);
//        ImageView haitanImage = findViewById(R.id.haitan945);
//
//        senlinImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 切换选中状态
//                v.setSelected(!v.isSelected());
//                mainLayout.setBackgroundResource(R.drawable.asenlin);
//            }
//        });
//
//        yutianImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setSelected(!v.isSelected());
//                mainLayout.setBackgroundResource(R.drawable.pic_rain);
//            }
//        });
//        xiayeImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setSelected(!v.isSelected());
//                mainLayout.setBackgroundResource(R.drawable.pic_summer_night);
//            }
//        });
//
//        gaoyuanImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setSelected(!v.isSelected());
//                mainLayout.setBackgroundResource(R.drawable.pic_plateau);
//            }
//        });
//
//        haitanImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setSelected(!v.isSelected());
//                mainLayout.setBackgroundResource(R.drawable.pic_beach);
//            }
//        });





        ImageView imageView = findViewById(R.id.yushefanhui);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建意图并启动新活动
//                Intent intent = new Intent(Yushe.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        Button button = findViewById(R.id.yushekaiqi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建意图并启动新活动
                // 创建对话框
                Dialog dialog = new Dialog(Yushe.this,R.style.RoundedCornersDialog);
                dialog.setContentView(R.layout.dialog_xiaoqimoshikaiqi); // 替换为你的布局文件名
                dialog.setCancelable(true); // 点击空白处关闭

                // 获取对话框中的按钮
                Button confirmButton = dialog.findViewById(R.id.confirmButton);
                Button cancelButton = dialog.findViewById(R.id.cancelButton);

                // 设置确认按钮点击事件
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 开启小憩模式的逻辑
                        dialog.dismiss();
                        startNapMode();

//                        showNapModeDialog();
                    }
                });

                // 设置取消按钮点击事件
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 关闭对话框，不执行小憩
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        ImageView imageViewshezhi = findViewById(R.id.shezhi56fd); // 替换为你的 ImageView ID
        imageViewshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogShezhi();
            }
        });





    }//OnCreate结束


    // 获取选择的时间值的方法（如果需要）
    public int getSelectedTime() {
        return timePicker.getValue();
    }
// 获取选择的时间值的方法（如果需要）
















//    private void checkAndRequestPermission() {
//        if (!Settings.System.canWrite(this)) {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
//            intent.setData(Uri.parse("package:" + getPackageName()));
//            Toast.makeText(this, "请授予修改系统设置权限", Toast.LENGTH_LONG).show();
//            startActivity(intent);
//        }
//    }
//
//    private void initBrightnessControl() {
//        brightnessSeekBar = findViewById(R.id.shezhiliangdu);
//
//        // 设置最大值
//        brightnessSeekBar.setMax(255);
//
//        try {
//            // 获取当前系统亮度
//            int currentBrightness = Settings.System.getInt(
//                    getContentResolver(),
//                    Settings.System.SCREEN_BRIGHTNESS
//            );
//            brightnessSeekBar.setProgress(currentBrightness);
//        } catch (Settings.SettingNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // 设置监听器
//        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (fromUser) {
//                    changeBrightness(progress);
//                }
//            }
//
//@Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                // 开始拖动时的操作（如果需要）
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                // 停止拖动时的操作（如果需要）
//            }
//        });
//    }
//
//    private void changeBrightness(int brightness) {
//        try {
//            // 设置最小亮度为20，避免屏幕太暗看不见
//            int minBrightness = 20;
//            brightness = Math.max(brightness, minBrightness);
//
//            if (Settings.System.canWrite(this)) {
//                // 修改系统亮度
//                Settings.System.putInt(
//                        getContentResolver(),
//                        Settings.System.SCREEN_BRIGHTNESS,
//                        brightness
//                );
//
//                // 更新当前窗口亮度
//                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//                layoutParams.screenBrightness = brightness / 255f;
//                getWindow().setAttributes(layoutParams);
//
//                // 可选：保存亮度值到SharedPreferences
//                saveBrightnessValue(brightness);
//            } else {
//                // 如果没有权限，再次请求
//                checkAndRequestPermission();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "调节亮度失败", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    // 保存亮度值到SharedPreferences（可选）
//    private void saveBrightnessValue(int brightness) {
//        getSharedPreferences("AppSettings", MODE_PRIVATE)
//                .edit()
//                .putInt("saved_brightness", brightness)
//                .apply();
//    }
//
//    // 读取保存的亮度值（可选）
//    private int getSavedBrightness() {
//        return getSharedPreferences("AppSettings", MODE_PRIVATE)
//                .getInt("saved_brightness", 128); // 默认值128
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        // 检查是否有权限，如果有，恢复保存的亮度值
//        if (Settings.System.canWrite(this)) {
//            int savedBrightness = getSavedBrightness();
//            brightnessSeekBar.setProgress(savedBrightness);
//            changeBrightness(savedBrightness);
//        }
//    }





    private void showNapModeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_nap_mode, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        TextView napshuimian = dialogView.findViewById(R.id.napshuimian);
        napshuimian.setText(minutes + "分钟后结束小憩模式");

        AlertDialog dialog = builder.create();
        dialog.show();
    }


//    private void showCustomDialogShezhi() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_layout_shezhi, null);
//        builder.setView(dialogView);
//
//        // 不设置对话框按钮
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }







private WeakReference<AlertDialog> shezhiDialogRef;
private WeakReference<View> mainLayoutRef;

private void showCustomDialogShezhi() {
    // 保存主布局的弱引用
    LinearLayout mainLayout = findViewById(R.id.main);
    mainLayoutRef = new WeakReference<>(mainLayout);

    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
    LayoutInflater inflater = getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.dialog_layout_shezhi, null);
    builder.setView(dialogView);

    // 使用数组存储ImageView，便于统一管理
    final ImageView[] themeViews = new ImageView[]{
        dialogView.findViewById(R.id.yutianaaa),
        dialogView.findViewById(R.id.xiliuaaa),
        dialogView.findViewById(R.id.haitanaaa),
        dialogView.findViewById(R.id.senlinaaa),
        dialogView.findViewById(R.id.xiayeaaa),
        dialogView.findViewById(R.id.gaoyuanaaa)
    };
// 使用数组存储对应的背景资源ID
    final int[] backgroundResources = new int[]{
        R.drawable.pic_rain,
        R.drawable.pic_summer_night,
        R.drawable.pic_beach,
        R.drawable.pic_plateau,
        R.drawable.pic_summer_night,
        R.drawable.pic_plateau
    };

    // 创建统一的点击监听器
    View.OnClickListener themeClickListener = v -> {
        clearAllForegrounds(themeViews);
        v.setForeground(getDrawable(R.drawable.blue_border));

        // 通过tag找到对应的背景资源
        int position = (int) v.getTag();
        View mainLayoutView = mainLayoutRef.get();
        if (mainLayoutView != null) {
            mainLayoutView.setBackgroundResource(backgroundResources[position]);
        }
    };

    // 设置tag和点击监听器
    for (int i = 0; i < themeViews.length; i++) {
        themeViews[i].setTag(i);
        themeViews[i].setOnClickListener(themeClickListener);
    }
 AlertDialog dialog = builder.create();
    shezhiDialogRef = new WeakReference<>(dialog);

    // 设置对话框消失监听器
    dialog.setOnDismissListener(dialogInterface -> {
        // 清理资源
        for (ImageView view : themeViews) {
            view.setOnClickListener(null);
            view.setTag(null);
        }
        if (shezhiDialogRef != null) {
            shezhiDialogRef.clear();
        }
    });

    dialog.show();
}

// 在Activity的onDestroy中添加清理代码
@Override
protected void onDestroy() {
    if (shezhiDialogRef != null && shezhiDialogRef.get() != null) {
        AlertDialog dialog = shezhiDialogRef.get();
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        shezhiDialogRef.clear();
    }

    if (mainLayoutRef != null) {
        mainLayoutRef.clear();
    }

    // ... 其他清理代码 ...

    super.onDestroy();
}



// 优化后的clearAllForegrounds方法
    private void clearAllForegrounds(ImageView[] views) {
        for (ImageView view : views) {
            if (view != null) {
                view.setForeground(null);
            }
        }
    }

    // 假设你有一个方法用于启动小憩模式界面
// 假设你有一个方法用于启动小憩模式界面
    private void startNapMode02() {
        Intent intent = new Intent(Yushe.this, napModeActivity.class);
        intent.putExtra("NAP_TIME", String.valueOf(minutes));
        startActivity(intent);
    }


    private void startNapMode() {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_nap_mode);

        // 使用 selectedMinutes 设置小憩时间
        TextView napShuimianTextView = dialog.findViewById(R.id.napshuimian);
        napShuimianTextView.setText(selectedMinutes + "分钟后结束小憩模式");

        // 设置时间显示
        TextView timeDisplay = dialog.findViewById(R.id.shijianxianshi);
        Handler timeHandler = new Handler();
        Runnable timeRunnable = new Runnable() {
            @Override
            public void run() {
                // 获取当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String currentTime = sdf.format(new Date());

                // 更新时间显示
                timeDisplay.setText(currentTime);

                // 每秒更新一次
                timeHandler.postDelayed(this, 1000);
            }
        };
        // 开始更新时间
        timeHandler.post(timeRunnable);

        Button confirmButton = dialog.findViewById(R.id.xiping044);
        Button cancelButton = dialog.findViewById(R.id.cancelButton);

        // 确认按钮（息屏）
        confirmButton.setOnClickListener(v -> {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.screenBrightness = 0.01f;  // 设置为最低亮度
            getWindow().setAttributes(params);
        });

        // 取消按钮
        cancelButton.setOnClickListener(v -> {
            try {
                // 恢复系统亮度设置
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
                getWindow().setAttributes(layoutParams);
                dialog.dismiss();

                // 当对话框关闭时，移除时间更新的回调，防止内存泄漏
                timeHandler.removeCallbacks(timeRunnable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 当对话框被取消时也要清理Handler
        dialog.setOnDismissListener(dialogInterface -> {
            timeHandler.removeCallbacks(timeRunnable);
        });

        dialog.show();
    }


    private void setupImageClick(ImageView imageView, int backgroundResource) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果当前已有选中的图片，取消其选中状态
                if (currentSelected != null && currentSelected != imageView) {
                    currentSelected.setSelected(false);
                }

                // 设置当前图片的选中状态
                imageView.setSelected(!imageView.isSelected());

                // 更新当前选中的图片
                currentSelected = imageView.isSelected() ? imageView : null;

                // 设置背景
                mainLayout.setBackgroundResource(backgroundResource);
            }
        });
    }
}