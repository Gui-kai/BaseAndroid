package com.guikai.test.permission;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.guikai.test.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

/**
 * Description: 仿淘宝权限
 * Crete by Anding on 2019-11-12
 */
public class AddPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        Button btn_request_camera = findViewById(R.id.btn_request_camera);
        Button btn_in_request = findViewById(R.id.btn_in_request);
        // 必须需要的授权
        btn_request_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AndPermission.hasPermissions(AddPermissionActivity.this, Manifest.permission.READ_PHONE_NUMBERS)) {
                    Toast.makeText(AddPermissionActivity.this, "已经通过授权", Toast.LENGTH_SHORT).show();
                } else {

                    ApplyMustPermission();
                }
            }
        });
        // 可以取消的权限
        btn_in_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyPermission();
            }
        });
    }

    private void ApplyPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.CAMERA)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(AddPermissionActivity.this, "已经通过授权", Toast.LENGTH_SHORT).show();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(AddPermissionActivity.this, Manifest.permission.CAMERA)) {
                            Log.e("", "部分功能被禁止");
                            showSettingsDialog(AddPermissionActivity.this);
                        } else {
                            showCamearDialog(AddPermissionActivity.this);
                        }
                    }
                })
                .start();
    }

    private void showPhoneDialog(final Context context) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.drawable.ic_app_launcher);
        normalDialog.setTitle("我们需要您的电话申请权限");
        normalDialog.setCancelable(false);
        normalDialog.setMessage("电话权限被你禁止了，可能误操作，会影响部分功能使用，需要授予权限才能使用");
        normalDialog.setPositiveButton("好的",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        ApplyMustPermission();
                    }
                });

        // 显示
        normalDialog.show();
    }

    private void showCamearDialog(final Context context) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.drawable.ic_app_launcher);
        normalDialog.setTitle("我们需要您的电话申请权限");
        normalDialog.setCancelable(false);
        normalDialog.setMessage("电话权限被你禁止了，可能误操作，会影响部分功能使用，需要授予权限才能使用");
        normalDialog.setPositiveButton("好的",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        ApplyPermission();
                    }
                })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AddPermissionActivity.this, "已经取消相机授权", Toast.LENGTH_SHORT).show();
                            }
                        });

        // 显示
        normalDialog.show();
    }

    // 授予权限自定义提示框
    public void ApplyMustPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.READ_PHONE_NUMBERS)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(AddPermissionActivity.this, "已经通过授权", Toast.LENGTH_SHORT).show();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(AddPermissionActivity.this, Manifest.permission.READ_PHONE_NUMBERS)) {
                            Log.e("", "部分功能被禁止");
                            showMustSettingsDialog(AddPermissionActivity.this);
                        } else {
                            showPhoneDialog(AddPermissionActivity.this);
                        }
                    }
                })
                .start();
    }

    private void showSettingsDialog(final Context context) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.drawable.ic_app_launcher);
        normalDialog.setTitle("你已经永久拒绝授权——进入settings");
        normalDialog.setCancelable(false);
        normalDialog.setMessage("部分权限被你禁止了，可能误操作，可能会影响部分功能，是否去要去重新设置？");
        normalDialog.setPositiveButton("进入设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        getAppDetailSettingIntent(AddPermissionActivity.this, 2);
                    }
                })
                .setNegativeButton("下次设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddPermissionActivity.this, "已经取消相机授权", Toast.LENGTH_SHORT).show();
                    }
                });

        // 显示
        normalDialog.show();
    }

    // 永久拒绝进入设置自定义提示框
    private void showMustSettingsDialog(final Context context) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.drawable.ic_app_launcher);
        normalDialog.setTitle("你已经永久拒绝授权——进入settings");
        normalDialog.setCancelable(false);
        normalDialog.setMessage("部分权限被你禁止了，可能误操作，可能会影响部分功能，是否去要去重新设置？");
        normalDialog.setPositiveButton("进入设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        getAppDetailSettingIntent(AddPermissionActivity.this, 1);
                    }
                });

        // 显示
        normalDialog.show();
    }

    private void getAppDetailSettingIntent(Context context, int code) {
        Intent localIntent = new Intent();
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        startActivityForResult(localIntent, code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (AndPermission.hasPermissions(this, Manifest.permission.READ_PHONE_NUMBERS)) {
                Toast.makeText(AddPermissionActivity.this, "您已授权操作！", Toast.LENGTH_SHORT).show();

            } else {
                showMustSettingsDialog(AddPermissionActivity.this);
            }
        } else if (requestCode == 2) {
            if (AndPermission.hasPermissions(this, Manifest.permission.CAMERA)) {
                Toast.makeText(AddPermissionActivity.this, "您已授权操作！", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(AddPermissionActivity.this, "相机权限未授权通过！", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
