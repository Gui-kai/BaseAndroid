package com.guikai.test.broadcast.outline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private OutLineReceiver outLineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollecter.addActivity(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("outline");
        outLineReceiver = new OutLineReceiver();
        registerReceiver(outLineReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (outLineReceiver!=null){
            unregisterReceiver(outLineReceiver);
        }
        ActivityCollecter.removeActivity(this);
    }

    //定义强制下线广播
    class OutLineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告！");
            builder.setMessage("您的账号已在异地登录，请重新登录");
            builder.setCancelable(false);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollecter.finishAll();
                    Intent intent = new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }

}
