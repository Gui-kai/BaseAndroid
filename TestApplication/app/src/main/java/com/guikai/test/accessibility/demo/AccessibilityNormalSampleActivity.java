package com.guikai.test.accessibility.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.guikai.test.R;
import com.guikai.test.accessibility.utils.AccessibilityLog;
import com.guikai.test.accessibility.utils.AccessibilityOperator;

public class AccessibilityNormalSampleActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility_normal_sample);
        initViews();

    }

    private void initViews() {
        findViewById(R.id.normal_sample_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.normal_sample_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                simulationClickByText();
                simulationClickById();
            }
        }, 2000);
    }

    // 开始模拟点击 自动
    private void simulationClickById() {
        // 自动点击复选框
        boolean result = AccessibilityOperator.getInstance().clickById("com.guikai.test:id/normal_sample_checkbox");
        AccessibilityLog.printLog(result ? "复选框模拟点击成功" : "复选框模拟点击失败");

        // 自动点击单选按钮
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean result = AccessibilityOperator.getInstance().clickById("com.guikai.test:id/normal_sample_radiobutton");
                AccessibilityLog.printLog(result ? "单选按钮模拟点击成功" : "单选按钮模拟点击失败");
            }
        }, 2000);

        // 自动点击按钮
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean result = AccessibilityOperator.getInstance().clickById("com.guikai.test:id/normal_sample_togglebutton");
                AccessibilityLog.printLog(result ? "OnOff开关模拟点击成功" : "OnOff开关模拟点击失败");
            }
        }, 4000);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                boolean result = AccessibilityOperator.getInstance().clickById("com.guikai.test:id/normal_sample_back");
//                AccessibilityLog.printLog(result ? "退出本页面模拟点击成功" : "退出本页面模拟点击失败");
                // 下面这个模拟点击系统返回键
                boolean result = AccessibilityOperator.getInstance().clickBackKey();
                AccessibilityLog.printLog(result ? "返回键模拟点击成功" : "返回键模拟点击失败");
            }
        }, 6000);
    }
}
