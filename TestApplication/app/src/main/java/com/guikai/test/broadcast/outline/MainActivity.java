package com.guikai.test.broadcast.outline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.guikai.test.R;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outline);
    }

    public void outline(View view) {
        Intent intent = new Intent("outline");
        sendBroadcast(intent);
    }
}
