package com.example.qingjing01;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private boolean shouldShowDialog = false;
    private boolean isActivated = false; // 跟踪按钮状态
    // 定义两个布尔变量来跟踪模式状态
    private boolean isAqsActivated = false;
    private boolean isXingshenActivated = false;
    private boolean isXiaoqiActivated = false;  // 新增

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton xiaoqiButton346 = findViewById(R.id.xiaoqi_button);
        xiaoqiButton346.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAqsDialog();
            }
        });



        ImageButton xiaoqiButton = findViewById(R.id.xiaoqi_button);
        xiaoqiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, aqsActivity.class);
                startActivity(intent);
            }
        });
        //弹出成功toast
//        Button aqsbutton = findViewById(R.id.aqsbutton);
//        aqsbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showCustomToast();
//            }
//        });

        // 读取 SharedPreferences 标志
        SharedPreferences preferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean showToast = preferences.getBoolean("showToast", false);

        if (showToast) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();

            // 清除标志以防止重复显示
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("showToast", false);
            editor.apply();
        }
        // 检查是否需要显示Toast
//        if (getIntent().getBooleanExtra("showToast", false)) {
//            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
//        }

        // 找到按钮并设置点击事件
        ImageButton startButton03 = findViewById(R.id.xiaoqi_button03); // Change Button to ImageButton
        startButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建意图并启动新活动
                Intent intent = new Intent(MainActivity.this, XiaoQi_xiangqing.class);
                startActivity(intent);
            }
        });


//        Button button = findViewById(R.id.start_button03);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 创建意图并启动新活动
//                Intent intent = new Intent(MainActivity.this, Yushe.class);
//                startActivity(intent);
//            }
//        });

        Intent intent = getIntent();
        shouldShowDialog = intent.getBooleanExtra("showDialog", false);
        // 显示自定义对话框

        // AQS模式按钮
        Button aqsbutton02 = findViewById(R.id.aqsbutton);
        aqsbutton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isXingshenActivated) {
                    showToast("醒神模式已开启，无法开启AQS模式");
                    return;
                }
                if (isXiaoqiActivated) {  // 新增判断
                    showToast("小憩模式已开启，无法开启AQS模式");
                    return;
                }

                if (isAqsActivated) {
                    aqsbutton02.setBackgroundResource(R.drawable.kaiqi02);
                    aqsbutton02.setText("开启");
                    showCustomToastaqsguanbi();

                } else {
                    aqsbutton02.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    aqsbutton02.setText("关闭");
                    showCustomToastaqs();
                }
                isAqsActivated = !isAqsActivated;
            }
        });






        // 醒神模式按钮
        Button xingshenbuto = findViewById(R.id.xingshenbutton);
        xingshenbuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAqsActivated) {
                    showToast("AQS模式已开启，无法开启醒神模式");
                    return;
                }
                if (isXiaoqiActivated) {  // 新增判断
                    showToast("小憩模式已开启，无法开启醒神模式");
                    return;
                }

                if (isXingshenActivated) {
                    xingshenbuto.setBackgroundResource(R.drawable.kaiqi02);
                    xingshenbuto.setText("开启");
                    showCustomToastxingshenguanbi();

                } else {
                    xingshenbuto.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    xingshenbuto.setText("关闭");
                    showCustomToastxingshen();
                }
                isXingshenActivated = !isXingshenActivated;
            }
        });

        Button button = findViewById(R.id.start_button03);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 先检查是否可以开启小憩模式
                if (isAqsActivated) {
                    showToast("AQS模式已开启，无法开启小憩模式");
                    return;
                }
                if (isXingshenActivated) {
                    showToast("醒神模式已开启，无法开启小憩模式");
                    return;
                }

                // 如果是点击开启按钮，先跳转到预设页面
                if (!isXiaoqiActivated) {
                    Intent intent = new Intent(MainActivity.this, Yushe.class);
                    startActivity(intent);
                }

                // 更新按钮状态showToastaqs
                if (isXiaoqiActivated) {
                    // 恢复原状
                    button.setBackgroundResource(R.drawable.kaiqi02);
                    button.setText("开启");
                    showCustomToastxiaoqiguanbi();

                } else {
                    // 改变按钮背景色和文字
                    button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    button.setText("关闭");
                    showCustomToastxiaoqi();
                }
                isXiaoqiActivated = !isXiaoqiActivated;
            }
    });





        //醒神模式详情页
        ImageButton xingshenimage = findViewById(R.id.xingshenimage);
        xingshenimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, XingshenXiangqingMainActivity2.class);
                startActivity(intent);
            }
        });






    }//OnCreate结束

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getBooleanExtra("SHOW_SAVE_TOAST", false)) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }
    }








    private void showAqsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_aqs, null);
        builder.setView(dialogView);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void showCustomToastFanhui() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.baocun_success, null);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100); // 设置位置
        toast.show();
    }

        private void showCustomToastchongzhi() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.chongzhi_success, null);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100); // 设置位置
        toast.show();
    }

    private void showCustomToastaqs() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.aqsmoshilayout, null);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100); // 设置位置
        toast.show();
    }


    private void showCustomToastaqsguanbi() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.aqsmoshilayoutguanbi, null);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100); // 设置位置
        toast.show();
    }

    private void showCustomToastxingshen() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.xingshenmoshilayout, null);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100); // 设置位置
        toast.show();
    }


    private void showCustomToastxingshenguanbi() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.xingshenmoshilayoutguanbi, null);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100); // 设置位置
        toast.show();
    }

        // 添加小憩模式的Toast提示方法
    private void showCustomToastxiaoqi() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.xiaoqimoshilayout, null);  // 需要创建对应的布局文件

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }


    private void showCustomToastxiaoqiguanbi() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.xiaoqimoshilayoutgaunbi, null);  // 需要创建对应的布局文件

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }

    private void showCustomToast() {
        Toast toast = Toast.makeText(this, "AQS期间，不可触发其他模式", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100); // 调整位置
        toast.show();
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // 检查是否需要显示对话框
//        Intent intent = getIntent();
//        if (intent != null && intent.getBooleanExtra("shouldShowDialog", false)) {
//            shouldShowDialog = true;
//            intent.removeExtra("shouldShowDialog");  // 清除标志防止重复显示
//        }
//
//        if (shouldShowDialog) {
//            showSuccessDialog();
//            shouldShowDialog = false;
//        }
//
//        // 检查是否需要显示 Toast
//        Intent intentaqs = getIntent();
//        if (intentaqs.getBooleanExtra("showToast", false)) {
//            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
//            // 清除标记，防止再次进入时显示
//            intentaqs.removeExtra("showToast");
//        }
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // 获取 Intent
//        Intent intent = getIntent();
//        if (intent != null) {
//            // 检查是否需要显示对话框
//            if (intent.getBooleanExtra("shouldShowDialog", false)) {
//                shouldShowDialog = true;
//                intent.removeExtra("shouldShowDialog");  // 清除标志防止重复显示
//            }
//
//            // 检查是否需要显示 Toast
//            if (intent.getBooleanExtra("showToastaqs", false)) {
//                showToast("保存成功");  // 使用已有的showToast方法
//                intent.removeExtra("showToastaqs");  // 清除标记，防止再次进入时显示
//            }
//        }
//
//        // 显示对话框
//        if (shouldShowDialog) {
//            showSuccessDialog();
//            shouldShowDialog = false;
//        }
//    }


    @Override
    protected void onResume() {
        super.onResume();

        // 获取 Intent
        Intent intent = getIntent();
        if (intent != null) {
            // 检查是否需要显示对话框
            if (intent.getBooleanExtra("shouldShowDialog", false)) {
                shouldShowDialog = true;
                intent.removeExtra("shouldShowDialog");  // 清除标志防止重复显示
            }

            // 检查是否需要显示自定义 Toast
            if (intent.getBooleanExtra("showToastaqs", false)) {
                showCustomToastFanhui();  // 使用自定义Toast
                intent.removeExtra("showToastaqs");  // 清除标记，防止再次进入时显示
            }
            // 检查是否需要显示自定义 Toast
            if (intent.getBooleanExtra("showToastxingshen", false)) {
                showCustomToastFanhui();  // 使用自定义Toast
                intent.removeExtra("showToastxingshen");  // 清除标记，防止再次进入时显示
            }
            // 检查是否需要显示自定义 Toast
            if (intent.getBooleanExtra("showToastaqschongzhi", false)) {
                showCustomToastchongzhi();  // 使用自定义Toast
                intent.removeExtra("showToastaqschongzhi");  // 清除标记，防止再次进入时显示
            }

        }

        // 显示对话框
        if (shouldShowDialog) {
            showSuccessDialog();
            shouldShowDialog = false;
        }
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("保存成功")
                .setPositiveButton("确定", null)
                .create()
                .show();
    }

}