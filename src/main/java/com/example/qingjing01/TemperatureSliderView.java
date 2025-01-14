package com.example.qingjing01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Locale;

public class TemperatureSliderView extends View {
    private float itemWidth = 150f;
    private float minTemp = 16f;
    private float maxTemp = 30f;
    private float currentTemp = 24f;
    private float scrollX = 0f;
    private float lastX = 0f;
    private Paint paint;
    private int selectedColor = Color.parseColor("#333333");
    private int unselectedColor = Color.parseColor("#CCCCCC");

    public TemperatureSliderView(Context context) {
        this(context, null);
    }
    // 添加获取当前温度的方法
    public float getCurrentTemp() {
        return currentTemp;
    }

    public TemperatureSliderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TemperatureSliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (itemWidth * 0.8f);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;

        // 绘制当前温度
        paint.setTextSize(itemWidth * 0.4f);
        paint.setColor(selectedColor);
        String tempText = String.format(Locale.getDefault(), "%.0f°C", currentTemp);
        canvas.drawText(tempText, centerX, centerY, paint);

        // 绘制左右温度
        paint.setTextSize(itemWidth * 0.3f);
        paint.setColor(unselectedColor);

        // 左侧温度
        if (currentTemp > minTemp) {
            String leftTemp = String.format(Locale.getDefault(), "%.0f°C", currentTemp - 1);
            canvas.drawText(leftTemp, centerX - itemWidth, centerY, paint);
        }

        // 右侧温度
        if (currentTemp < maxTemp) {
            String rightTemp = String.format(Locale.getDefault(), "%.0f°C", currentTemp + 1);
            canvas.drawText(rightTemp, centerX + itemWidth, centerY, paint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                return true;

            case MotionEvent.ACTION_MOVE:
                float dx = event.getX() - lastX;
                if (Math.abs(dx) > itemWidth * 0.3f) {
                    // 更新温度
                    if (dx > 0 && currentTemp > minTemp) {
                        currentTemp--;
                        lastX = event.getX();
                        invalidate();
                    } else if (dx < 0 && currentTemp < maxTemp) {
                        currentTemp++;
                        lastX = event.getX();
                        invalidate();
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

    // 温度改变监听器
    public interface OnTemperatureChangeListener {
        void onTemperatureChanged(float temperature);
    }

    private OnTemperatureChangeListener onTemperatureChangeListener;

    public void setOnTemperatureChangeListener(OnTemperatureChangeListener listener) {
        this.onTemperatureChangeListener = listener;
    }

    public void setCurrentTemperature(float temperature) {
        this.currentTemp = Math.min(Math.max(temperature, minTemp), maxTemp);
        invalidate();
    }
}
