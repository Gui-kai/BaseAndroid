package com.guikai.test.open_alarm_calendar;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.guikai.test.R;

public class Intent_Test extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_calendar);
    }

    public void openAlarm(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        startActivity(intent);
    }

    public void openCalendar(View view) {
        Intent intent = new Intent();
        ComponentName componentName = null;
        if (Build.VERSION.SDK_INT >= 8) {
            componentName = new ComponentName("com.google.android.calendar","com.android.calendar.LaunchActivity");
        } else {
            componentName = new ComponentName("com.android.calendar","com.android.calendar.LaunchActivity");
        }

        intent.setComponent(componentName);
        startActivity(intent);
    }
}

/**
 * ComponentName用来打开其他其他应用程序中的Activity或服务的
 */
