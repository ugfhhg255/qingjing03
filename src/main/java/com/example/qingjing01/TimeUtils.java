package com.example.qingjing01;

import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeUtils {

    public static void setCurrentTimeToTextView(TextView textView) {
        if (textView != null) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String currentTime = dateFormat.format(calendar.getTime());
            textView.setText(currentTime);
        }
    }

    public static void setupLayoutAndSetTime(Activity activity, int layoutResId, int textViewResId) {
        activity.setContentView(layoutResId);
        TextView shijianxianshi = activity.findViewById(textViewResId);
        setCurrentTimeToTextView(shijianxianshi);
    }
}
