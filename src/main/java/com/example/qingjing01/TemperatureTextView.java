package com.example.qingjing01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Locale;

public class TemperatureTextView extends androidx.appcompat.widget.AppCompatTextView {
    private float currentTemp = 22f;
    private float minTemp = 16f;
    private float maxTemp = 30f;
    private float lastX;
    private TextPaint smallPaint;

    public TemperatureTextView(Context context) {
        this(context, null);
    }

    public TemperatureTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TemperatureTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始化小字体画笔
        smallPaint = new TextPaint();
        smallPaint.setAntiAlias(true);
        smallPaint.setTextSize(getTextSize() * 0.8f);
        smallPaint.setColor(Color.GRAY);

        // 设置初始温度
        updateTemperatureText();
    }

    private void updateTemperatureText() {
        // 构建显示文本
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // 添加左侧温度（如果不是最小温度）
        if (currentTemp > minTemp) {
            builder.append(String.format(Locale.getDefault(), "%.0f°C", currentTemp - 1));
            builder.setSpan(new RelativeSizeSpan(0.8f), 0, builder.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(Color.GRAY), 0, builder.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append("  ");
        }

        // 添加当前温度
        int currentTempStart = builder.length();
        builder.append(String.format(Locale.getDefault(), "%.0f°C", currentTemp));
        builder.setSpan(new RelativeSizeSpan(1.2f), currentTempStart, builder.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.BLACK), currentTempStart, builder.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 添加右侧温度（如果不是最大温度）
        if (currentTemp < maxTemp) {
            builder.append("  ");
            int rightTempStart = builder.length();
            builder.append(String.format(Locale.getDefault(), "%.0f°C", currentTemp + 1));
            builder.setSpan(new RelativeSizeSpan(0.8f), rightTempStart, builder.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(Color.GRAY), rightTempStart, builder.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        setText(builder);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                return true;

            case MotionEvent.ACTION_MOVE:
                float deltaX = event.getX() - lastX;
                if (Math.abs(deltaX) > 50) { // 滑动阈值
                    if (deltaX > 0 && currentTemp > minTemp) {
                        currentTemp--;
                        updateTemperatureText();
                        lastX = event.getX();
                    } else if (deltaX < 0 && currentTemp < maxTemp) {
                        currentTemp++;
                        updateTemperatureText();
                        lastX = event.getX();
                    }
                }
                return true;

            case MotionEvent.ACTION_UP:
                if (onTemperatureChangeListener != null) {
                    onTemperatureChangeListener.onTemperatureChanged(currentTemp);
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    // 温度改变监听器接口
    public interface OnTemperatureChangeListener {
        void onTemperatureChanged(float temperature);
    }

    private OnTemperatureChangeListener onTemperatureChangeListener;

    public void setOnTemperatureChangeListener(OnTemperatureChangeListener listener) {
        this.onTemperatureChangeListener = listener;
    }

    public void setTemperature(float temperature) {
        this.currentTemp = Math.min(Math.max(temperature, minTemp), maxTemp);
        updateTemperatureText();
    }

    public float getTemperature() {
        return currentTemp;
    }
}
