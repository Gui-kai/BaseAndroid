package com.guikai.test.accessibility;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.guikai.test.R;
import com.guikai.test.accessibility.utils.AccessibilityOperator;

public class AccessibilityMainActivity extends AppCompatActivity implements View.OnClickListener {

    private View mOpenSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility_main);
        initView();
        AccessibilityOperator.getInstance().init(this);
    }

    private void initView() {
        mOpenSetting = findViewById(R.id.open_accessibility_setting);
        mOpenSetting.setOnClickListener(this);
        findViewById(R.id.accessibility_find_and_click).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.open_accessibility_setting:
                // 跳转设置 辅助模式 授权
                OpenAccessibilitySettingHelper.jumpToSettingPage(this);
                break;
            case R.id.accessibility_find_and_click:
                startActivity(new Intent(this, AccessibilityNormalSampleActivity.class));
                break;
        }
    }
}
