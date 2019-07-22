package com.guikai.test.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.guikai.test.R;

//本地广播的使用，全局广播只需要去掉local即可

public class LocalActivity extends AppCompatActivity {

    private MyBroadCastReceiver myBroadCastReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loacalbroad);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("loacalbroad");
        myBroadCastReceiver = new MyBroadCastReceiver();
        localBroadcastManager.registerReceiver(myBroadCastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myBroadCastReceiver);
    }

    public void broad(View view) {
        Intent intent = new Intent("loacalbroad");
        localBroadcastManager.sendBroadcast(intent);
    }
}
