package com.example.qingjing01;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimePickerAdapter extends RecyclerView.Adapter<TimePickerAdapter.ViewHolder> {
    private List<Integer> timeList;
    private int selectedPosition = 25;  // 默认选中30分钟

    public TimePickerAdapter() {
        timeList = new ArrayList<>();
        // 添加5-60分钟的选项
        for (int i = 5; i <= 60; i++) {
            timeList.add(i);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_time_picker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int time = timeList.get(position);
        holder.textView.setText(time + " 分钟");

        // 设置选中项的样式
        if (position == selectedPosition) {
            holder.textView.setTextColor(Color.parseColor("#FF4081"));  // 选中颜色
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        } else {
            holder.textView.setTextColor(Color.BLACK);  // 未选中颜色
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public int getSelectedTime() {
        return timeList.get(selectedPosition);
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
