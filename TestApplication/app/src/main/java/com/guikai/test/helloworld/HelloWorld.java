package com.guikai.test.helloworld;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.guikai.test.R;

public class HelloWorld extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //可用于布局查看
        setContentView(R.layout.xiaomi_login);
    }
}
