package com.example.qingjing01;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class XiaoQi_xiangqing extends AppCompatActivity {
    private TextView kongtiaoWenduText;    // 这是主布局中的控件
    private TextView xiaJiangBtn;    // 新增
    private TextView shangShengBtn;  // 新增
    private TextView wenduJiaBtn;  // 新增
    private static final float MAX_TEMP = 30f;  // 新增
    private TextView wenduJianBtn;
    private TemperatureSliderView temperatureSlider;
    private static final float MIN_TEMP = 16f;  // 最低温度
        private WeakReference<Dialog> dialogRef;

    private int currentFanSpeed = 2; // 初始风速
    private int currentTemperature = 24; // 初始温度
    // 声明一个变量记录是否被选中
    private boolean isShangsheng02Selected = false;
    // 声明变量记录当前选中的按钮
    private TextView currentSelectedTextView = null;
    int currentPercent02 = 20; // 初始值20%

    private Dialog sleepTimeDialog;
    private TextView selectedTextView = null; // 记录当前选中的选项
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xiao_qi_xiangqing);

        // 在主布局中找到温度显示控件
        kongtiaoWenduText = findViewById(R.id.kongtiaowenduxianshi);




        ImageButton fanhuiButton = findViewById(R.id.fanhui);
        fanhuiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(XiaoQi_xiangqing.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });




        ImageView xiaoqibaocun = findViewById(R.id.xiaoqibaocun);
        xiaoqibaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog_baocun();
            }
        });




        ImageView zuoyiImageView = findViewById(R.id.zuoyi);
        zuoyiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSeatPositionDialog();
//                showSeatPositionAdjustmentDialog();
            }
        });







        ImageView zhumianImageView = findViewById(R.id.zhumian); //助眠模式
        zhumianImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSleepTimeDialog();
            }
        });


        ImageView kongtiaowendu = findViewById(R.id.kongtiaowendu);
        kongtiaowendu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTemperatureDialog();
            }
        });


        ImageView kongtiaofengl=findViewById(R.id.kongtiaofengl);
        kongtiaofengl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showFanSpeedDialog();
                showFanSpeedDialog02();

            }
        });

        ImageView xiangfenButton = findViewById(R.id.xiangfen);
        xiangfenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showXiangfenDialog();
            }
        });

        ImageView zuoyianmoButton = findViewById(R.id.zuoyianmo);
        zuoyianmoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSeatMassageDialog();
            }
        });

        ImageView fenweideng = findViewById(R.id.fenweideng);
        fenweideng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFenweidengDialog();
            }
        });


        ImageView xiaoqibaocun03 = findViewById(R.id.xiaoqibaocun);
        xiaoqibaocun03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog_baocunxiaoqi();
            }
        });

        ImageView xiaoqichongzhi023 = findViewById(R.id.xiaoqichongzhi);
        xiaoqichongzhi023.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog_chongzhixiaoqi();
            }
        });







    } //oncreate结束





//    private void showSeatPositionDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.activity_dia_seat_position);
//
//
//        dialog.show();
//    }

    private Dialog seatDialog;

    private void showSeatPositionDialog() {
        if (isFinishing()) return;

        // 使用弱引用避免内存泄漏
        WeakReference<Context> contextRef = new WeakReference<>(this);

        seatDialog = new Dialog(this, R.style.RoundedCornersDialog);
        seatDialog.setContentView(R.layout.activity_dia_seat_position);
        seatDialog.setCanceledOnTouchOutside(true);

        // 初始化所有控件
        SeatControls controls = initializeSeatControls(seatDialog);

        // 设置所有点击监听器
        setupClickListeners(controls, contextRef);

        seatDialog.show();
    }

    // 控件包装类
    private static class SeatControls {
        TextView backrestAngle;    // 靠背角度
        TextView seatPosition;     // 座椅位置
        TextView seatHeight;       // 座椅高度
        ImageView[] arrows = new ImageView[6];  // 所有箭头控件
    }

// 初始化所有控件
    private SeatControls initializeSeatControls(Dialog dialog) {
        SeatControls controls = new SeatControls();
        controls.backrestAngle = dialog.findViewById(R.id.text_left);
        controls.seatPosition = dialog.findViewById(R.id.below_line_text);
        controls.seatHeight = dialog.findViewById(R.id.text_below_arrow);

        controls.arrows[0] = dialog.findViewById(R.id.arrow_right);
        controls.arrows[1] = dialog.findViewById(R.id.arrow_left);
        controls.arrows[2] = dialog.findViewById(R.id.right_arrow);
        controls.arrows[3] = dialog.findViewById(R.id.left_arrow);
        controls.arrows[4] = dialog.findViewById(R.id.arrow_top);
        controls.arrows[5] = dialog.findViewById(R.id.arrow_bottom);

        return controls;
    }

// 设置点击监听器
    private void setupClickListeners(SeatControls controls, WeakReference<Context> contextRef) {
        // 靠背角度控制
        setupPercentageControl(controls.arrows[0], controls.arrows[1], controls.backrestAngle, "靠背角度");

        // 座椅位置控制
        setupPercentageControl(controls.arrows[2], controls.arrows[3], controls.seatPosition, "座椅位置", true);

        // 座椅高度控制
        setupPercentageControl(controls.arrows[4], controls.arrows[5], controls.seatHeight, "座椅高度");
    }

// 通用的百分比控制设置方法
// 通用的百分比控制设置方法
private void setupPercentageControl(ImageView increaseButton, ImageView decreaseButton,
                                  TextView displayText, String label, boolean... enableHaptic) {
    final int[] currentImageState = {0};  // 从0开始，表示初始图片004
    ImageView seatImageView = seatDialog.findViewById(R.id.imageView_seat);

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (displayText == null) return;

            String currentText = displayText.getText().toString();
            int currentPercentage = Integer.parseInt(currentText.replaceAll("[^0-9]", ""));
            int newPercentage;

            if (v == increaseButton) {
                newPercentage = Math.min(currentPercentage + 10, 100);
                // 点击右箭头时向前切换图片
                if (v.getId() == R.id.arrow_right && currentImageState[0] < 5) { // 确保未达到最终状态
                    switch (currentImageState[0]) {
                        case 0: // 从004开始
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_005);
                            currentImageState[0] = 1;
                            break;
                        case 1:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_006);
                            currentImageState[0] = 2;
                            break;
                        case 2:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_007);
                            currentImageState[0] = 3;
                            break;
                        case 3:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_008);
                            currentImageState[0] = 4;
                            break;
                        case 4:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_009);
                            currentImageState[0] = 5;
                            break;
                    }
                }
            } else {
                newPercentage = Math.max(currentPercentage - 10, 10);
                // 点击左箭头时向后切换图片
                if (v.getId() == R.id.arrow_left && currentImageState[0] > 0) { // 确保不是初始状态
                    switch (currentImageState[0]) {
                        case 1:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_004);
                            currentImageState[0] = 0;
                            break;
                        case 2:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_005);
                            currentImageState[0] = 1;
                            break;
                        case 3:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_006);
                            currentImageState[0] = 2;
                            break;
                        case 4:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_007);
                            currentImageState[0] = 3;
                            break;
                        case 5:
                            seatImageView.setImageResource(R.drawable.pic_light_backrest_angle_008);
                            currentImageState[0] = 4;
                            break;
                    }
                }
            }

            displayText.setText(label + newPercentage + "%");

            if (enableHaptic.length > 0 && enableHaptic[0] &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK);
            }
        }
    };

    increaseButton.setOnClickListener(listener);
    decreaseButton.setOnClickListener(listener);
}


// 安全地关闭对话框
    private void dismissDialog() {
        if (seatDialog != null && seatDialog.isShowing()) {
            seatDialog.dismiss();
            seatDialog = null;
        }
    }




    private AlertDialog ambientLightDialog;

// 颜色名称映射
    private static final Map<Integer, String> COLOR_MAP = new HashMap<Integer, String>() {{
        put(R.id.fenweidengbai, "白色");
        put(R.id.fenweidenglv, "绿色");
        put(R.id.fenweidengqing, "青色");
        put(R.id.fenweidenglan, "蓝色");
        put(R.id.fenweidengzi, "紫色");
        put(R.id.fenweidenghuang, "黄色");
        put(R.id.fenweidenghong, "红色");
    }};
    private void showFenweidengDialog() {
    if (isFinishing()) return;

    View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_fenweideng, null);

    // 获取控件引用
    TextView colorText = findViewById(R.id.fenweidengxianshi);
    TextView tuijianyanse = dialogView.findViewById(R.id.tuijianyanse);
    TextView colorTextSecond = dialogView.findViewById(R.id.yanseooo);
    ImageView musicRhythmSwitch = dialogView.findViewById(R.id.yinyuelvdong35);

    // 当前选中的颜色View
    final View[] currentSelected = {null};

    // 颜色按钮点击监听器
    View.OnClickListener colorClickListener = v -> {
        // 更新选中状态
        if (currentSelected[0] != null) {
            currentSelected[0].setBackground(null);
        }
        v.setBackground(getDrawable(R.drawable.blue_border_select));
        currentSelected[0] = v;

        // 更新颜色文本
        String colorName = COLOR_MAP.get(v.getId());
        if (colorName != null) {
            colorText.setText("氛围灯\n" + colorName);
        }
    };
// 设置所有颜色按钮的点击监听
    for (int buttonId : COLOR_MAP.keySet()) {
        View colorButton = dialogView.findViewById(buttonId);
        if (colorButton != null) {
            colorButton.setOnClickListener(colorClickListener);
        }
    }

    // 音乐律动开关
    final boolean[] isFirstImage = {true};
    musicRhythmSwitch.setOnClickListener(v -> {
        if (isFirstImage[0]) {
            // 开启音乐律动
            musicRhythmSwitch.setImageResource(R.drawable.yinyuelvdongkai23d);
            tuijianyanse.setTextColor(Color.GRAY);
            colorTextSecond.setTextColor(Color.GRAY);

            // 禁用所有颜色按钮
            for (int buttonId : COLOR_MAP.keySet()) {
                View colorButton = dialogView.findViewById(buttonId);
                if (colorButton != null) {
                    colorButton.setEnabled(false);
                    colorButton.setAlpha(0.5f);
                }
            }

            // 清除当前选中状态
            if (currentSelected[0] != null) {
                currentSelected[0].setBackground(null);
                currentSelected[0] = null;
            }
 // 更新显示文本
            colorText.setText("氛围灯\n音乐律动开启中");

        } else {
            // 关闭音乐律动
            musicRhythmSwitch.setImageResource(R.drawable.anniuaaa);
            tuijianyanse.setTextColor(Color.BLACK);
            colorTextSecond.setTextColor(Color.BLACK);

            // 启用所有颜色按钮
            for (int buttonId : COLOR_MAP.keySet()) {
                View colorButton = dialogView.findViewById(buttonId);
                if (colorButton != null) {
                    colorButton.setEnabled(true);
                    colorButton.setAlpha(1.0f);
                }
            }

            // 恢复显示文本
            colorText.setText("氛围灯\n请选择颜色");
        }
        isFirstImage[0] = !isFirstImage[0];
    });
 // 创建并显示对话框
    ambientLightDialog = new AlertDialog.Builder(this, R.style.RoundedCornersDialog)
            .setView(dialogView)
            .setCancelable(true)
            .create();
    ambientLightDialog.setCanceledOnTouchOutside(true);
    ambientLightDialog.show();
}

    @Override
    protected void onDestroy() {
        // 关闭氛围灯对话框
        if (ambientLightDialog != null && ambientLightDialog.isShowing()) {
            ambientLightDialog.dismiss();
        }

        // 关闭按摩对话框（使用WeakReference方式）
        if (dialogRef != null && dialogRef.get() != null) {
            Dialog seatMassageDialog = dialogRef.get();
            if (seatMassageDialog.isShowing()) {
                seatMassageDialog.dismiss();
            }
            dialogRef.clear();
        }

        if (xiangfenDialog != null && xiangfenDialog.isShowing()) {
            xiangfenDialog.dismiss();
        }

        if (fanSpeedDialog != null && fanSpeedDialog.isShowing()) {
            fanSpeedDialog.dismiss();
        }

        if (sleepTimeDialog02 != null && sleepTimeDialog02.isShowing()) {
            sleepTimeDialog02.dismiss();
        }

        super.onDestroy();
    }


    private void updateSeatPosition(TextView textView, int change) {
        try {
            // 获取当前文本
            String currentText = textView.getText().toString();
            // 提取百分比数字
            int currentPercentage = Integer.parseInt(currentText.replaceAll("[^0-9]", ""));
            // 更新百分比
            int newPercentage = currentPercentage + change;
            // 确保百分比在0%到100%之间
            newPercentage = Math.max(0, Math.min(newPercentage, 100));
            // 更新 TextView 文本
            textView.setText("靠背角度" + newPercentage + "%");
        } catch (NumberFormatException e) {
            // 处理解析错误
            textView.setText("靠背角度0%");
        }
    }




    private int blendColors(int color1, int color2, float ratio) {
        final float inverseRatio = 1f - ratio;
        float r = (color1 >> 16 & 0xFF) * ratio + (color2 >> 16 & 0xFF) * inverseRatio;
        float g = (color1 >> 8 & 0xFF) * ratio + (color2 >> 8 & 0xFF) * inverseRatio;
        float b = (color1 & 0xFF) * ratio + (color2 & 0xFF) * inverseRatio;
        return 0xFF << 24 | ((int) r << 16) | ((int) g << 8) | (int) b;
    }

     private void showSeatMassageDialog() {
        final Dialog seatMassageDialog = new Dialog(this, R.style.RoundedCornersDialog);
        dialogRef = new WeakReference<>(seatMassageDialog);
        seatMassageDialog.setContentView(R.layout.dialog_seat_massage);

        // 设置Dialog样式
        Window window = seatMassageDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
            window.setAttributes(params);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // 使用数组存储按摩模式的TextView
        final TextView[] modeViews = new TextView[]{
            seatMassageDialog.findViewById(R.id.bolang),
            seatMassageDialog.findViewById(R.id.maobu),
            seatMassageDialog.findViewById(R.id.jiaocha),
            seatMassageDialog.findViewById(R.id.shuhuan),
            seatMassageDialog.findViewById(R.id.tiaoyue)
        };
 // 使用数组存储强度级别的TextView
        final TextView[] intensityViews = new TextView[]{
            seatMassageDialog.findViewById(R.id.low),
            seatMassageDialog.findViewById(R.id.medium),
            seatMassageDialog.findViewById(R.id.high)
        };

        // 优化的模式选择监听器
        final View.OnClickListener modeListener = v -> {
            for (TextView view : modeViews) {
                view.setBackgroundResource(R.drawable.rounded_background);
            }
            v.setBackgroundResource(R.drawable.selected_background);
            handleModeSelection((TextView) v); // 处理模式选择的业务逻辑
        };

        // 优化的强度选择监听器
        final View.OnClickListener intensityListener = v -> {
            for (TextView view : intensityViews) {
                view.setBackgroundResource(R.drawable.rounded_background);
            }
            v.setBackgroundResource(R.drawable.selected_background);
            handleIntensitySelection((TextView) v); // 处理强度选择的业务逻辑
        };
// 批量设置模式选择监听器
        for (TextView view : modeViews) {
            view.setOnClickListener(modeListener);
        }

        // 批量设置强度选择监听器
        for (TextView view : intensityViews) {
            view.setOnClickListener(intensityListener);
        }

        // 添加对话框消失监听器
        seatMassageDialog.setOnDismissListener(dialog -> {
            // 清理资源
            for (TextView view : modeViews) {
                view.setOnClickListener(null);
            }
            for (TextView view : intensityViews) {
                view.setOnClickListener(null);
            }
            if (dialogRef != null) {
                dialogRef.clear();
            }
        });

        seatMassageDialog.show();
    }
    // 处理模式选择的业务逻辑
    private void handleModeSelection(TextView selectedView) {
        String mode = selectedView.getText().toString();
        // 根据选择的模式执行相应的操作
        // 例如：发送命令到设备、更新UI状态等
    }

    // 处理强度选择的业务逻辑
    private void handleIntensitySelection(TextView selectedView) {
        String intensity = selectedView.getText().toString();
        // 根据选择的强度执行相应的操作
        // 例如：发送命令到设备、更新UI状态等
    }



//    private void showSeatMassageDialog() {
////        final Dialog seatMassageDialog = new Dialog(this);
//        final Dialog seatMassageDialog = new Dialog(this, R.style.RoundedCornersDialog);
//        seatMassageDialog.setContentView(R.layout.dialog_seat_massage);
//
//        // 设置Dialog样式
//        Window window = seatMassageDialog.getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
//            window.setAttributes(params);
//            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//
//        // 找到Dialog中的视图
//        final TextView bolang = seatMassageDialog.findViewById(R.id.bolang);
//        final TextView maobu = seatMassageDialog.findViewById(R.id.maobu);
//        final TextView jiaocha = seatMassageDialog.findViewById(R.id.jiaocha);
//        final TextView shuhuan = seatMassageDialog.findViewById(R.id.shuhuan);
//        final TextView tiaoyue = seatMassageDialog.findViewById(R.id.tiaoyue);
//
//        // 其他模式TextView
//
//        final TextView low = seatMassageDialog.findViewById(R.id.low);
//        final TextView medium = seatMassageDialog.findViewById(R.id.medium);
//        final TextView high = seatMassageDialog.findViewById(R.id.high);
//        // 其他强度TextView
//
//        // 设置模式选择的点击事件
//        View.OnClickListener modeListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bolang.setBackgroundResource(R.drawable.rounded_background);
//                maobu.setBackgroundResource(R.drawable.rounded_background);
//                jiaocha.setBackgroundResource(R.drawable.rounded_background);
//                shuhuan.setBackgroundResource(R.drawable.rounded_background);
//                tiaoyue.setBackgroundResource(R.drawable.rounded_background);
//                // 其他模式背景重置
//
//                TextView selected = (TextView) v;
//                selected.setBackgroundResource(R.drawable.selected_background);
//            }
//        };
//        bolang.setOnClickListener(modeListener);
//        maobu.setOnClickListener(modeListener);
//        jiaocha.setOnClickListener(modeListener);
//        shuhuan.setOnClickListener(modeListener);
//        tiaoyue.setOnClickListener(modeListener);
//        // 其他模式设置监听
//
//        // 设置强度选择的点击事件
//        View.OnClickListener intensityListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                low.setBackgroundResource(R.drawable.rounded_background);
//                medium.setBackgroundResource(R.drawable.rounded_background);
//                high.setBackgroundResource(R.drawable.rounded_background);
//                // 其他强度背景重置
//
//                TextView selected = (TextView) v;
//                selected.setBackgroundResource(R.drawable.selected_background);
//            }
//        };
//
//        low.setOnClickListener(intensityListener);
//        medium.setOnClickListener(intensityListener);
//        high.setOnClickListener(intensityListener);
//        // 其他强度设置监听
//
//        seatMassageDialog.show();
////        finish();
//    }

//    private Dialog seatMassageDialog;
//
//// 模式按钮ID数组
//    private static final int[] MODE_IDS = {
//        R.id.bolang, R.id.maobu, R.id.jiaocha, R.id.shuhuan, R.id.tiaoyue
//    };
//
//// 强度按钮ID数组
//    private static final int[] INTENSITY_IDS = {
//        R.id.low, R.id.medium, R.id.high
//    };
//
//    private void showSeatMassageDialog() {
//        if (isFinishing()) return;
//
//        seatMassageDialog = new Dialog(this, R.style.RoundedCornersDialog);
//        seatMassageDialog.setContentView(R.layout.dialog_seat_massage);
//
//        // 设置Dialog样式
//        setupDialogWindow();
//
//        // 初始化按钮和监听器
//        initializeButtons();
//
//        seatMassageDialog.show();
//    }
//
//
//
//    private void initializeButtons() {
//        View.OnClickListener modeListener = v -> resetBackgrounds(MODE_IDS, v);
//        View.OnClickListener intensityListener = v -> resetBackgrounds(INTENSITY_IDS, v);
//
//        // 设置模式按钮监听器
//        for (int id : MODE_IDS) {
//            View button = seatMassageDialog.findViewById(id);
//            if (button != null) {
//                button.setOnClickListener(modeListener);
//            }
//        }
//
//        // 设置强度按钮监听器
//        for (int id : INTENSITY_IDS) {
//            View button = seatMassageDialog.findViewById(id);
//            if (button != null) {
//                button.setOnClickListener(intensityListener);
//            }
//        }
//    }
//
//    private void resetBackgrounds(int[] ids, View selectedView) {
//        // 重置所有按钮背景
//        for (int id : ids) {
//            View view = seatMassageDialog.findViewById(id);
//            if (view != null) {
//                view.setBackgroundResource(R.drawable.rounded_background);
//            }
//        }
//        // 设置选中按钮背景
//        selectedView.setBackgroundResource(R.drawable.selected_background);
//    }




//    private void showXiangfenDialog() {
////        final Dialog xiangfenDialog = new Dialog(this);
//        final Dialog xiangfenDialog = new Dialog(this, R.style.RoundedCornersDialog);
//        xiangfenDialog.setContentView(R.layout.dialog_xiangfen);
//
//        // 设置Dialog样式
//        Window window = xiangfenDialog.getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
//            window.setAttributes(params);
//            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//
//        // 找到Dialog中的视图
//        TextView lightFragrance = xiangfenDialog.findViewById(R.id.danxiang);
//        TextView mediumFragrance = xiangfenDialog.findViewById(R.id.qingxiang);
//        TextView strongFragrance = xiangfenDialog.findViewById(R.id.nongxiang);
//
//        // 默认选中清香，设置为蓝色
////        mediumFragrance.setBackgroundColor(getResources().getColor(R.color.qblue));
//        // 默认选中清香，设置为蓝色
//        mediumFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//
//        // 设置强度选择的点击事件
//        View.OnClickListener intensityListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 重置所有选项为灰色
////                lightFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                mediumFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                strongFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
//
//                // 重置所有选项为灰色圆角背景
//                lightFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                mediumFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                strongFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//
//
//                // 将被点击的选项设置为蓝色
//                TextView selected = (TextView) v;
////                selected.setBackgroundColor(getResources().getColor(R.color.qblue));
//                selected.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//            }
//        };
//        lightFragrance.setOnClickListener(intensityListener);
//        mediumFragrance.setOnClickListener(intensityListener);
//        strongFragrance.setOnClickListener(intensityListener);
//
//        // 设置香氛模块的点击事件
//        TextView blackTemptation = xiangfenDialog.findViewById(R.id.heiseyouh);
//        TextView windRising = xiangfenDialog.findViewById(R.id.fengqilian);
//        TextView forestTime = xiangfenDialog.findViewById(R.id.mulinshiguang);
//
//        // 默认选中沐林时光，设置为蓝色
////        forestTime.setBackgroundColor(getResources().getColor(R.color.qblue));
//        // 默认选中沐林时光，设置为蓝色
//        forestTime.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//
//        View.OnClickListener fragranceListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 重置所有选项为灰色
////                blackTemptation.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                windRising.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                forestTime.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
//                // 重置所有选项为灰色圆角背景
//                blackTemptation.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                windRising.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                forestTime.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//
//
//                // 将被点击的选项设置为蓝色
//                TextView selected = (TextView) v;
////                selected.setBackgroundColor(getResources().getColor(R.color.qblue));
//                selected.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//            }
//        };
//
//        blackTemptation.setOnClickListener(fragranceListener);
//        windRising.setOnClickListener(fragranceListener);
//        forestTime.setOnClickListener(fragranceListener);
//
//        xiangfenDialog.show();
//    }

//    private void showXiangfenDialog() {  //11111
//        final Dialog xiangfenDialog = new Dialog(this, R.style.RoundedCornersDialog);
//        xiangfenDialog.setContentView(R.layout.dialog_xiangfen);
//
//        // 设置Dialog样式
//        Window window = xiangfenDialog.getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
//            window.setAttributes(params);
//            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//
//        // 找到要更新的结果显示 TextView
//        final TextView resultTextView = findViewById(R.id.selected_fragrance_text);
//        final String[] selectedIntensity = {""}; // 默认强度
//        final String[] selectedFragrance = {""}; // 默认香氛
//
//        // 找到Dialog中的视图
//        TextView lightFragrance = xiangfenDialog.findViewById(R.id.danxiang);
//        TextView mediumFragrance = xiangfenDialog.findViewById(R.id.qingxiang);
//        TextView strongFragrance = xiangfenDialog.findViewById(R.id.nongxiang);
//
//        // 默认选中清香
//        mediumFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//
//        View.OnClickListener intensityListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 重置所有选项为灰色圆角背景
//                lightFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                mediumFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                strongFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//
//                // 将被点击的选项设置为蓝色
//                TextView selected = (TextView) v;
//                selected.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//                selectedIntensity[0] = selected.getText().toString();
//            }
//        };
//
//        // 设置香氛模块的点击事件
//        TextView blackTemptation = xiangfenDialog.findViewById(R.id.heiseyouh);
//        TextView windRising = xiangfenDialog.findViewById(R.id.fengqilian);
//        TextView forestTime = xiangfenDialog.findViewById(R.id.mulinshiguang);
//
//        // 默认选中沐林时光
//        forestTime.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//
//        View.OnClickListener fragranceListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 重置所有选项为灰色圆角背景
//                blackTemptation.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                windRising.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                forestTime.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//
//                // 将被点击的选项设置为蓝色
//                TextView selected = (TextView) v;
//                selected.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
////                selectedFragrance[0] = selected.getText().toString();
//            }
//        };
//
//        // 设置点击监听器
//        lightFragrance.setOnClickListener(intensityListener);
//        mediumFragrance.setOnClickListener(intensityListener);
//        strongFragrance.setOnClickListener(intensityListener);
//        blackTemptation.setOnClickListener(fragranceListener);
//        windRising.setOnClickListener(fragranceListener);
//        forestTime.setOnClickListener(fragranceListener);
//
//        // 设置对话框消失时的监听器
//        xiangfenDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                // 在对话框消失时更新文本
//                resultTextView.setText(String.format("香氛沐林时光 \n%s",selectedIntensity[0]));
//            }
//        });
//
//        xiangfenDialog.show();
//    }

    private Dialog xiangfenDialog;

// 香氛强度按钮ID数组
    private static final int[] INTENSITY_IDS_XIANGFEN = {
        R.id.danxiang, R.id.qingxiang, R.id.nongxiang
    };

    // 香氛类型按钮ID数组
    private static final int[] FRAGRANCE_IDS = {
        R.id.heiseyouh, R.id.fengqilian, R.id.mulinshiguang
    };

    private void showXiangfenDialog() {
        if (isFinishing()) return;

        xiangfenDialog = new Dialog(this, R.style.RoundedCornersDialog);
        xiangfenDialog.setContentView(R.layout.dialog_xiangfen);

        // 设置Dialog样式
        Window window = xiangfenDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
            window.setAttributes(params);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // 获取结果显示的TextView
        final TextView resultTextView = findViewById(R.id.selected_fragrance_text);
        final String[] selectedIntensity = {""};

        // 初始化所有按钮
        TextView mediumFragrance = xiangfenDialog.findViewById(R.id.qingxiang);
        TextView forestTime = xiangfenDialog.findViewById(R.id.mulinshiguang);

        // 设置默认选中状态
        mediumFragrance.setBackground(getDrawable(R.drawable.xiangfen_select02));
        forestTime.setBackground(getDrawable(R.drawable.xiangfen_select02));

        // 设置强度选择监听器
        View.OnClickListener intensityListener = v -> {
            // 重置所有按钮背景
            for (int id : INTENSITY_IDS_XIANGFEN) {
                View button = xiangfenDialog.findViewById(id);
                if (button != null) {
                    button.setBackground(getDrawable(R.drawable.xiangfen_select));
                }
            }
            // 设置选中状态
            v.setBackground(getDrawable(R.drawable.xiangfen_select02));
            selectedIntensity[0] = ((TextView) v).getText().toString();
        };

        // 设置香氛类型选择监听器
        View.OnClickListener fragranceListener = v -> {
            // 重置所有按钮背景
            for (int id : FRAGRANCE_IDS) {
                View button = xiangfenDialog.findViewById(id);
                if (button != null) {
                    button.setBackground(getDrawable(R.drawable.xiangfen_select));
                }
            }
            // 设置选中状态
            v.setBackground(getDrawable(R.drawable.xiangfen_select02));
        };

        // 设置强度按钮监听器
        for (int id : INTENSITY_IDS_XIANGFEN) {
            View button = xiangfenDialog.findViewById(id);
            if (button != null) {
                button.setOnClickListener(intensityListener);
            }
        }

        // 设置香氛类型按钮监听器
        for (int id : FRAGRANCE_IDS) {
            View button = xiangfenDialog.findViewById(id);
            if (button != null) {
                button.setOnClickListener(fragranceListener);
            }
        }

        // 设置对话框关闭监听
        xiangfenDialog.setOnDismissListener(dialog ->
            resultTextView.setText(String.format("香氛沐林时光 \n%s", selectedIntensity[0]))
        );

        xiangfenDialog.show();
    }



    private void setupDialogWindow() {
        Window window = xiangfenDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
            window.setAttributes(params);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void resetButtonBackgrounds(int[] buttonIds) {
        for (int id : buttonIds) {
            View button = xiangfenDialog.findViewById(id);
            if (button != null) {
                button.setBackground(getDrawable(R.drawable.xiangfen_select));
            }
        }
    }

    private void setButtonListeners(int[] buttonIds, View.OnClickListener listener) {
        for (int id : buttonIds) {
            View button = xiangfenDialog.findViewById(id);
            if (button != null) {
                button.setOnClickListener(listener);
            }
        }
    }





//    private void showXiangfenDialog() {
////        final Dialog xiangfenDialog = new Dialog(this);
//        final Dialog xiangfenDialog = new Dialog(this, R.style.RoundedCornersDialog);
//        xiangfenDialog.setContentView(R.layout.dialog_xiangfen);
//
//        // 设置Dialog样式
//        Window window = xiangfenDialog.getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
//            window.setAttributes(params);
//            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//
//        // 找到Dialog中的视图
//        TextView lightFragrance = xiangfenDialog.findViewById(R.id.danxiang);
//        TextView mediumFragrance = xiangfenDialog.findViewById(R.id.qingxiang);
//        TextView strongFragrance = xiangfenDialog.findViewById(R.id.nongxiang);
//
//        // 默认选中清香，设置为蓝色
////        mediumFragrance.setBackgroundColor(getResources().getColor(R.color.qblue));
//        // 默认选中清香，设置为蓝色
//        mediumFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//
//        // 设置强度选择的点击事件
//        View.OnClickListener intensityListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 重置所有选项为灰色
////                lightFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                mediumFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                strongFragrance.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
//
//                // 重置所有选项为灰色圆角背景
//                lightFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                mediumFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                strongFragrance.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//
//
//                // 将被点击的选项设置为蓝色
//                TextView selected = (TextView) v;
////                selected.setBackgroundColor(getResources().getColor(R.color.qblue));
//                selected.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//            }
//        };
//        lightFragrance.setOnClickListener(intensityListener);
//        mediumFragrance.setOnClickListener(intensityListener);
//        strongFragrance.setOnClickListener(intensityListener);
//
//        // 设置香氛模块的点击事件
//        TextView blackTemptation = xiangfenDialog.findViewById(R.id.heiseyouh);
//        TextView windRising = xiangfenDialog.findViewById(R.id.fengqilian);
//        TextView forestTime = xiangfenDialog.findViewById(R.id.mulinshiguang);
//
//        // 默认选中沐林时光，设置为蓝色
////        forestTime.setBackgroundColor(getResources().getColor(R.color.qblue));
//        // 默认选中沐林时光，设置为蓝色
//        forestTime.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//
//        View.OnClickListener fragranceListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 重置所有选项为灰色
////                blackTemptation.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                windRising.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
////                forestTime.setBackgroundColor(getResources().getColor(R.color.semi_transparent_gray));
//                // 重置所有选项为灰色圆角背景
//                blackTemptation.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                windRising.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//                forestTime.setBackground(getResources().getDrawable(R.drawable.xiangfen_select));
//
//
//                // 将被点击的选项设置为蓝色
//                TextView selected = (TextView) v;
////                selected.setBackgroundColor(getResources().getColor(R.color.qblue));
//                selected.setBackground(getResources().getDrawable(R.drawable.xiangfen_select02));
//            }
//        };
//
//        blackTemptation.setOnClickListener(fragranceListener);
//        windRising.setOnClickListener(fragranceListener);
//        forestTime.setOnClickListener(fragranceListener);
//
//        xiangfenDialog.show();
//    }



    // 在类的开头添加一个变量来保存当前的风量级别


    private AlertDialog fanSpeedDialog;

    private void showFanSpeedDialog02() {
        if (isFinishing()) return;

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_fan_speed02, null);

        // 初始化控件
        SeekBar seekBar = dialogView.findViewById(R.id.seekBarKongtiao23);
        TextView dialogValueText = dialogView.findViewById(R.id.kongtiaofengl03Text);
        TextView mainTextView = findViewById(R.id.kongtiaofengaaaa);

        // 设置初始值
        seekBar.setProgress(currentFanSpeed);
        dialogValueText.setText(String.valueOf(currentFanSpeed));

        // 设置SeekBar监听器
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser) return;

                currentFanSpeed = progress;
                dialogValueText.setText(String.valueOf(progress));
                mainTextView.setText(String.format("空调风量\n%d级", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 创建对话框
        fanSpeedDialog = new AlertDialog.Builder(this, R.style.RoundedCornersDialog)
                .setView(dialogView)
                .setCancelable(true)
                .create();

        fanSpeedDialog.show();
    }


//    private void showFanSpeedDialog02() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_fan_speed02, null);
//        builder.setView(dialogView);
//        builder.setCancelable(true);
//
//        SeekBar seekBar0232 = dialogView.findViewById(R.id.seekBarKongtiao23);
//        TextView valueTextv = dialogView.findViewById(R.id.kongtiaofengl03Text);
//        TextView mainTextView = findViewById(R.id.kongtiaofengaaaa);
//
//        // 设置SeekBar的初始值为保存的风量级别
//        seekBar0232.setProgress(currentFanSpeed);
//        valueTextv.setText(String.valueOf(currentFanSpeed));
//
//        seekBar0232.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                valueTextv.setText(String.valueOf(progress));
//                // 保存当前选择的值
//                currentFanSpeed = progress;
//                mainTextView.setText("空调风量\n" + progress + "级");
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }


//    private void showFanSpeedDialog02() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_fan_speed02, null);
//        builder.setView(dialogView);
//        builder.setCancelable(true);
//
//        // 修正：应该从dialogView中查找控件，而不是从Activity中查找
//        SeekBar seekBar0232 = dialogView.findViewById(R.id.seekBarKongtiao23);
//        TextView valueTextv = dialogView.findViewById(R.id.kongtiaofengl03Text);
//
//        // 设置初始值
//        valueTextv.setText(String.valueOf(seekBar0232.getProgress()));
//
//        // 设置滑动监听
//        seekBar0232.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                valueTextv.setText(String.valueOf(progress));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });
//
//        // 创建并显示对话框
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }





    private void showFanSpeedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_fan_speed, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        TextView fanSpeedDecrease = dialogView.findViewById(R.id.fan_pxeed_decrease);
        TextView fanSpeedDisplay = dialogView.findViewById(R.id.fan_pxeed_dipxlay);
        TextView fanSpeedIncrease = dialogView.findViewById(R.id.fan_pxeed_increase);

        // 显示初始风速
        fanSpeedDisplay.setText(String.valueOf(currentFanSpeed));

        // 减少风速
        fanSpeedDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFanSpeed > 1) { // 假设最低风速为1
                    currentFanSpeed--;
                    fanSpeedDisplay.setText(String.valueOf(currentFanSpeed));
                }
            }
        });
        // 增加风速
        fanSpeedIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFanSpeed < 10) { // 假设最高风速为5
                    currentFanSpeed++;
                    fanSpeedDisplay.setText(String.valueOf(currentFanSpeed));
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



//
//    private void showTemperatureDialog() {
////        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_temperature, null);
//        builder.setView(dialogView);
//        builder.setCancelable(true);
//
//        TextView wendujian = dialogView.findViewById(R.id.wendujian);
//        TextView wenduxianshi = dialogView.findViewById(R.id.wenduxianshi);
//        TextView wendujia01 = dialogView.findViewById(R.id.wendujia01);
//        TextView shangsheng02 = dialogView.findViewById(R.id.shangsheng02);
//        TextView xiajiang02 = dialogView.findViewById(R.id.xiajiang02);
//        // 获取外部显示温度的TextView
//        TextView kongtiaowenduxianshi = findViewById(R.id.kongtiaowenduxianshi);
//        // 显示初始温度
//        wenduxianshi.setText(currentTemperature + "℃");
//
//        // 减少温度
//        wendujian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature > 16) { // 假设最低温度为16℃
//                    currentTemperature--;
//                    wenduxianshi.setText(currentTemperature + "℃");
//                    // 更新外部温度显示
//                    kongtiaowenduxianshi.setText("空调温度\n" + currentTemperature + "℃");
//                }
//            }
//        });
//        // 增加温度
//        wendujia01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature < 30) { // 假设最高温度为30℃
//                    currentTemperature++;
//                    wenduxianshi.setText(currentTemperature + "℃");
//                    // 更新外部温度显示
//                    kongtiaowenduxianshi.setText("空调温度\n" + currentTemperature + "℃");
//                }
//            }
//        });
//
//        // 上升 2°C
//        shangsheng02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature <= 28) { // 确保不超过30℃
//                    currentTemperature += 2;
//                    wenduxianshi.setText(currentTemperature + "℃");
//                    // 更新外部温度显示
//                    kongtiaowenduxianshi.setText("空调温度\n" + currentTemperature + "℃");
//
//                    // 如果之前有选中的按钮，恢复其背景
//                    if (currentSelectedTextView != null) {
//                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
//                    }
//
//                    // 设置当前按钮为蓝色背景
//                    // 方式1：使用 Color.parseColor() 方法
//                    // Google 品牌蓝色 (#4285F4)
//                    shangsheng02.setBackgroundColor(Color.parseColor("#4285F4"));
////                    shangsheng02.setBackgroundResource(R.drawable.tuoyuanjuxing_selected);
//                    currentSelectedTextView = shangsheng02;
//                }
//            }
//        });
//
//        // 下降 2°C
//        xiajiang02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature >= 18) { // 确保不低于16℃
//                    currentTemperature -= 2;
//                    wenduxianshi.setText(currentTemperature + "℃");
//                    // 更新外部温度显示
//                    kongtiaowenduxianshi.setText("空调温度\n" + currentTemperature + "℃");
//
//                    // 如果之前有选中的按钮，恢复其背景
//                    if (currentSelectedTextView != null) {
//                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
//                    }
//
//                    // 设置当前按钮为蓝色背景
//                    xiajiang02.setBackgroundColor(Color.parseColor("#4285F4"));
////                    shangsheng02.setBackgroundResource(R.drawable.tuoyuanjuxing_selected);
//
//                    currentSelectedTextView = xiajiang02;
//                }
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }





// 辅助方法：更新所有温度显示
    private void updateAllTemperatures(TextView wenduxianshidi, TextView wenduxianshi,
                                     TextView wenduxianshigao, TextView kongtiaowenduxianshi) {
        wenduxianshidi.setText((currentTemperature - 1) + "℃");
        wenduxianshi.setText(currentTemperature + "℃");
        wenduxianshigao.setText((currentTemperature + 1) + "℃");
        kongtiaowenduxianshi.setText("空调温度\n" + currentTemperature + "℃");
    }

//    private void showTemperatureDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_temperature, null);
//        builder.setView(dialogView);
//        builder.setCancelable(true);
//
//        TextView wendujian = dialogView.findViewById(R.id.wendujian);
//        TextView wenduxianshidi = dialogView.findViewById(R.id.wenduxianshidi);
//        TextView wenduxianshi = dialogView.findViewById(R.id.wenduxianshi);
//        TextView wenduxianshigao = dialogView.findViewById(R.id.wenduxianshigao);
//        TextView wendujia01 = dialogView.findViewById(R.id.wendujia01);
//        TextView shangsheng02 = dialogView.findViewById(R.id.shangsheng02);
//        TextView xiajiang02 = dialogView.findViewById(R.id.xiajiang02);
//        TextView kongtiaowenduzidingyi = dialogView.findViewById(R.id.kongtiaowenduzidingyi);
//        TextView kongtiaowenduxianshi = findViewById(R.id.kongtiaowenduxianshi);
//
//        // 显示初始温度
//        updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);
//
//        // 减少温度
//        wendujian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature > 16) {
//                    currentTemperature--;
//                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);
//
//                    if (currentSelectedTextView != null) {
//                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
//                    }
//                    currentSelectedTextView = null;
//                }
//            }
//        });
//
//        // 增加温度
//        wendujia01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature < 30) {
//                    currentTemperature++;
//                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);
//
//                    if (currentSelectedTextView != null) {
//                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
//                    }
//                    currentSelectedTextView = null;
//                }
//            }
//        });
//
//        // 上升 2°C
//        shangsheng02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature <= 28) {
//                    currentTemperature += 2;
//                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);
//
//                    if (currentSelectedTextView != null) {
//                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
//                    }
//                    shangsheng02.setBackgroundColor(Color.parseColor("#4285F4"));
//                    currentSelectedTextView = shangsheng02;
//                }
//            }
//        });
//
//        // 下降 2°C
//        xiajiang02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentTemperature >= 18) {
//                    currentTemperature -= 2;
//                    updateAllTemperatures(wenduxianshidi, wenduxianshi, wenduxianshigao, kongtiaowenduxianshi);
//
//                    if (currentSelectedTextView != null) {
//                        currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
//                    }
//                    xiajiang02.setBackgroundColor(Color.parseColor("#4285F4"));
//                    currentSelectedTextView = xiajiang02;
//                }
//            }
//        });
//
//        kongtiaowenduzidingyi.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        if (currentSelectedTextView != null) {
//            currentSelectedTextView.setBackgroundColor(Color.TRANSPARENT);
//        }
//        kongtiaowenduzidingyi.setBackgroundColor(Color.parseColor("#4285F4"));
//        currentSelectedTextView = kongtiaowenduzidingyi;
//
//        // 创建自定义温度选择对话框
//        AlertDialog.Builder customBuilder = new AlertDialog.Builder(v.getContext());
//        View customView = getLayoutInflater().inflate(R.layout.dialog_custom_temperature, null);
//
//        NumberPicker temperaturePicker = customView.findViewById(R.id.temperaturePicker);
//        Button btnConfirm = customView.findViewById(R.id.btn_confirm);  // 添加确定按钮
//        Button btnCancel = customView.findViewById(R.id.btn_cancel);    // 添加取消按钮
//
//        // 设置NumberPicker的范围和当前值
//        temperaturePicker.setMinValue(16);
//        temperaturePicker.setMaxValue(30);
//        temperaturePicker.setValue(currentTemperature);
//        temperaturePicker.setWrapSelectorWheel(false);
//
//        customBuilder.setView(customView);
//
//        // 创建对话框
//        AlertDialog customDialog = customBuilder.create();
//
//        // 设置确定按钮点击事件
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currentTemperature = temperaturePicker.getValue();
//                updateAllTemperatures(wenduxianshidi, wenduxianshi,
//                                    wenduxianshigao, kongtiaowenduxianshi);
//                customDialog.dismiss();
//            }
//        });
//
//        // 设置取消按钮点击事件
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                customDialog.dismiss();
//            }
//        });
//
//        // 显示对话框
//        customDialog.show();
//    }
//});
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }


    private void showTemperatureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_temperature02, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        // 从 dialogView 中查找对话框内的控件
        wenduJianBtn = dialogView.findViewById(R.id.wendujian);
        wenduJiaBtn = dialogView.findViewById(R.id.wendujia01);
        shangShengBtn = dialogView.findViewById(R.id.shangsheng02);
        xiaJiangBtn = dialogView.findViewById(R.id.xiajiang02);
        temperatureSlider = dialogView.findViewById(R.id.temperatureSlider);

        // 更新温度显示的通用方法
        final Runnable updateTemperatureDisplay = new Runnable() {
            @Override
            public void run() {
                float currentTemp = temperatureSlider.getCurrentTemp();
                // 更新主布局中的温度显示
                kongtiaoWenduText.setText(String.format("空调温度\n%.0f℃", currentTemp));
            }
        };

        // 设置减温按钮点击事件
        wenduJianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentTemp = temperatureSlider.getCurrentTemp();
                if (currentTemp > MIN_TEMP) {
                    float newTemp = currentTemp - 1;
                    temperatureSlider.setCurrentTemperature(newTemp);
                    updateTemperatureDisplay.run();
                }
            }
        });

        // 设置加温按钮点击事件
        wenduJiaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentTemp = temperatureSlider.getCurrentTemp();
                if (currentTemp < MAX_TEMP) {
                    float newTemp = currentTemp + 1;
                    temperatureSlider.setCurrentTemperature(newTemp);
                    updateTemperatureDisplay.run();
                }
            }
        });

        // 设置上升2度按钮点击事件
        shangShengBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentTemp = temperatureSlider.getCurrentTemp();
                float newTemp = Math.min(currentTemp + 2, MAX_TEMP);
                temperatureSlider.setCurrentTemperature(newTemp);
                updateTemperatureDisplay.run();
            }
        });

        // 设置下降2度按钮点击事件
        xiaJiangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentTemp = temperatureSlider.getCurrentTemp();
                float newTemp = Math.max(currentTemp - 2, MIN_TEMP);
                temperatureSlider.setCurrentTemperature(newTemp);
                updateTemperatureDisplay.run();
            }
        });

        // 创建对话框
        final AlertDialog dialog = builder.create();

        // 设置温度改变监听器
        temperatureSlider.setOnTemperatureChangeListener(new TemperatureSliderView.OnTemperatureChangeListener() {
            @Override
            public void onTemperatureChanged(float temperature) {
                updateTemperatureDisplay.run();
                Toast.makeText(XiaoQi_xiangqing.this,
                        "温度设置为: " + (int)temperature + "°C",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // 初始化温度显示
        updateTemperatureDisplay.run();

        // 显示对话框
        dialog.show();
    }












    //助眠时间
//    private void showSleepTimeDialog() {
////        Dialog dialog = new Dialog(this);
//        Dialog dialog = new Dialog(this, R.style.RoundedCornersDialog);
//        dialog.setContentView(R.layout.dialog_sleep_time);
//
//        TextView option1 = dialog.findViewById(R.id.zhumian01);
//        TextView option2 = dialog.findViewById(R.id.zhumian02);
//        TextView option3 = dialog.findViewById(R.id.zhumian03);
//        TextView optionFull = dialog.findViewById(R.id.zhumianquan);
//        TextView option5 = dialog.findViewById(R.id.zhumianquan05);
//
//        // 创建一个数组存储所有选项，方便统一处理
//        TextView[] options = {option1, option2, option3, optionFull, option5};
//
//        // 默认选中第一个选项
//        option1.setBackgroundResource(R.drawable.tuoyuanjuxing_selected);
//
//        View.OnClickListener clickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 先将所有选项重置为白色背景
//                for (TextView option : options) {
//                    option.setBackgroundResource(R.drawable.white_background);
//                }
//
//                // 将被点击的选项设置为蓝色背景
//                v.setBackgroundResource(R.drawable.tuoyuanjuxing_selected);
//            }
//        };
//
//        // 为所有选项设置点击监听器
//        for (TextView option : options) {
//            option.setOnClickListener(clickListener);
//        }
//
//        dialog.show();
//    }




//    private void showSeatPositionDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.activity_dia_seat_position);
//        dialog.show();
//    }


//    private void showSleepTimeDialog() {
//        Dialog dialog = new Dialog(this, R.style.RoundedCornersDialog);
//        dialog.setContentView(R.layout.dialog_sleep_time);
//
//        TextView option1 = dialog.findViewById(R.id.zhumian01);
//        TextView option2 = dialog.findViewById(R.id.zhumian02);
//        TextView option3 = dialog.findViewById(R.id.zhumian03);
//        TextView optionFull = dialog.findViewById(R.id.zhumianquan);
//        TextView option5 = dialog.findViewById(R.id.zhumianquan05);
//
//        // 获取要更新的助眠时间TextView
//        TextView sleepTimeText = findViewById(R.id.zhumianshijian);
//
//        TextView[] options = {option1, option2, option3, optionFull, option5};
//
//        option1.setBackgroundResource(R.drawable.tuoyuanjuxing_selected);
//
//        View.OnClickListener clickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 重置所有背景
//                for (TextView option : options) {
//                    option.setBackgroundResource(R.drawable.white_background);
//                }
//
//                // 设置选中背景
//                v.setBackgroundResource(R.drawable.tuoyuanjuxing_selected);
//
//                // 根据选中的选项更新助眠时间文本
//                String timeText = "助眠时间\n";
//                if (v == option1) {
//                    timeText += "3min";
//                } else if (v == option2) {
//                    timeText += "5min";
//                } else if (v == option3) {
//                    timeText += "10min";
//                } else if (v == optionFull) {
//                    timeText += "20min";
//                } else if (v == option5) {
//                    timeText += "30min";
//                }
//                sleepTimeText.setText(timeText);
//
//                // 可选：添加短暂延迟后关闭对话框
//                v.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog.dismiss();
//                    }
//                }, 300); // 300毫秒后关闭对话框
//            }
//        };
//
//        for (TextView option : options) {
//            option.setOnClickListener(clickListener);
//        }
//
//        dialog.show();
//    }


    private Dialog sleepTimeDialog02;

// 时间选项映射
    private static final Map<Integer, String> TIME_MAP = new HashMap<Integer, String>() {{
        put(R.id.zhumian01, "3min");
        put(R.id.zhumian02, "5min");
        put(R.id.zhumian03, "10min");
        put(R.id.zhumianquan, "20min");
        put(R.id.zhumianquan05, "30min");
    }};

    private void showSleepTimeDialog() {
        if (isFinishing()) return;

        sleepTimeDialog = new Dialog(this, R.style.RoundedCornersDialog);
        sleepTimeDialog.setContentView(R.layout.dialog_sleep_time);

        // 获取主界面显示时间的TextView
        TextView sleepTimeText = findViewById(R.id.zhumianshijian);

        // 获取所有选项按钮
        TextView[] options = new TextView[TIME_MAP.size()];
        int index = 0;
        for (Integer id : TIME_MAP.keySet()) {
            options[index++] = sleepTimeDialog.findViewById(id);
        }

        // 设置默认选中状态
        if (options[0] != null) {
            options[0].setBackgroundResource(R.drawable.tuoyuanjuxing_selected);
        }

        // 设置点击监听器
        View.OnClickListener clickListener = v -> {
            // 重置所有背景
            for (TextView option : options) {
                if (option != null) {
                    option.setBackgroundResource(R.drawable.white_background);
                }
            }

            // 设置选中背景
            v.setBackgroundResource(R.drawable.tuoyuanjuxing_selected);

            // 更新时间文本
            String selectedTime = TIME_MAP.get(v.getId());
            if (selectedTime != null && sleepTimeText != null) {
                sleepTimeText.setText(String.format("助眠时间\n%s", selectedTime));
            }

            // 延迟关闭对话框
            v.postDelayed(() -> {
                if (sleepTimeDialog != null && sleepTimeDialog.isShowing()) {
                    sleepTimeDialog.dismiss();
                }
            }, 300);
        };

        // 为所有选项设置监听器
        for (TextView option : options) {
            if (option != null) {
                option.setOnClickListener(clickListener);
            }
        }

        sleepTimeDialog.show();
    }




    private void showSeatPositionAdjustmentDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dia_seat_position); // 替换为你的布局文件名

        // 获取 ImageView 和 TextView 的引用
        ImageView rightArrow = dialog.findViewById(R.id.right_arrow);
        ImageView leftArrow = dialog.findViewById(R.id.left_arrow);
        TextView belowLineText = dialog.findViewById(R.id.below_line_text);

        // 设置 rightArrow 的点击事件监听器
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取当前文本
                String currentText = belowLineText.getText().toString();
                // 提取百分比数字
                int currentPercentage = Integer.parseInt(currentText.replaceAll("[^0-9]", ""));
                // 增加10%
                int newPercentage = currentPercentage + 10;
                // 更新 TextView 文本
                belowLineText.setText("座椅位置" + newPercentage + "%");
            }
        });

        // 设置 leftArrow 的点击事件监听器
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取当前文本
                String currentText = belowLineText.getText().toString();
                // 提取百分比数字
                int currentPercentage = Integer.parseInt(currentText.replaceAll("[^0-9]", ""));
                // 减少10%
                int newPercentage = currentPercentage - 10;
                // 确保百分比不低于0
                if (newPercentage < 0) {
                    newPercentage = 0;
                }
                // 更新 TextView 文本
                belowLineText.setText("座椅位置" + newPercentage + "%");
            }
        });

        dialog.show();
    }






    private void showCustomDialog_baocun() {
        // 创建AlertDialog实例的构建器
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
        // 通过布局 inflater 将自定义布局文件转化为View对象
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom_baocun, null);
        // 设置对话框的视图为上述转化得到的View对象
        builder.setView(dialogView);

        // 创建AlertDialog实例
        AlertDialog dialog = builder.create();

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




    /**
     * 显示自定义对话框的方法
     * 该方法用于创建并显示一个自定义的对话框，其中包含确认和取消按钮
     * 对话框的布局由R.layout.dialog_custom定义
     */
    private void showCustomDialog() {
        // 创建AlertDialog实例的构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedCornersDialog);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 通过布局 inflater 将自定义布局文件转化为View对象
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom, null);
        // 设置对话框的视图为上述转化得到的View对象
        builder.setView(dialogView);

        // 创建AlertDialog实例
        AlertDialog dialog = builder.create();

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



    private void showCustomDialog_baocunxiaoqi() {
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
                Intent intent = new Intent(XiaoQi_xiangqing.this, MainActivity.class);
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



    private void showCustomDialog_chongzhixiaoqi() {
    // 创建AlertDialog实例的构建器
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.RoundedCornersDialog);
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

            // 在其他活动中返回 MainActivity 时
            Intent intent = new Intent(XiaoQi_xiangqing.this, MainActivity.class);
            intent.putExtra("showToastaqschongzhi", true);  // 设置显示Toast的标记
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




}