package com.guikai.test.updataapp;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.guikai.test.R;

public class MainActivity extends AppCompatActivity {
    //这个是你的包名
    private static final String apkName = "apk";
    private static final String firstUrl = "http://ucan.25pp.com/Wandoujia_web_seo_baidu_homepage.apk";
    private static final String secondUrl = "http://img1.haowmc.com/hwmc/test/android_apk/2018101027122399.apk";
    private static final String url = "http://img1.haowmc.com/hwmc/test/android_";
    private static final String[] mPermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "onPause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3000);
    }



}
