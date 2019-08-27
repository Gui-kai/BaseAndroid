package com.guikai.test.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guikai.test.R;
import com.guikai.test.md.MdActivtiy;
import com.guikai.test.shadow.ShadowActivity;
import com.guikai.test.styleview.LoginStyle;
import com.guikai.test.broadcast.outline.LoginActivity;
import com.guikai.test.datasave.FileSave;
import com.guikai.test.floatwindow.FloatWindow;
import com.guikai.test.guidepage.GuideActivity;
import com.guikai.test.sliding_tab_layout.MainActivity;
import com.guikai.test.updatedialog.UpdateApk;
import com.guikai.test.vlayout.VLayoutActivity;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    TextView btn_test;
    Button btn_guide;
    Button btn_float_widow;
    Button btn_data_save;
    Button btn_sp;
    Button btn_sli_tab_layout;
    Button btn_login;
    Button btn_update_dialog;
    Button btn_vlayout;
    Button btn_broad;
    Button btn_md;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        btn_float_widow = findViewById(R.id.float_widow);
        btn_guide = findViewById(R.id.guide);
        btn_test = findViewById(R.id.test);
        btn_data_save = findViewById(R.id.data_save);
        btn_sp = findViewById(R.id.sp);
        btn_sli_tab_layout = findViewById(R.id.sli_tab_layout);
        btn_login = findViewById(R.id.login);
        btn_update_dialog = findViewById(R.id.update_dialog);
        btn_vlayout = findViewById(R.id.vlayout);
        btn_broad = findViewById(R.id.broadcast);
        btn_md = findViewById(R.id.md);

        btn_float_widow.setOnClickListener(this);
        btn_guide.setOnClickListener(this);
        btn_data_save.setOnClickListener(this);
        btn_sp.setOnClickListener(this);
        btn_sli_tab_layout.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_update_dialog.setOnClickListener(this);
        btn_test.setOnClickListener(this);
        btn_vlayout.setOnClickListener(this);
        btn_broad.setOnClickListener(this);
        btn_md.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide:
                intent = new Intent(this, GuideActivity.class);
                startActivity(intent);
                break;
            case R.id.float_widow:
                intent = new Intent(this, FloatWindow.class);
                startActivity(intent);
                break;
            case R.id.data_save:
                intent = new Intent(this, FileSave.class);
                startActivity(intent);
                break;
            case R.id.sli_tab_layout:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.update_dialog:
                intent = new Intent(this, UpdateApk.class);
                startActivity(intent);
                break;
            case R.id.login:
                intent = new Intent(this, LoginStyle.class);
                startActivity(intent);
                break;
            case R.id.test:
                intent = new Intent("guikai");
                startActivity(intent);
                break;
            case R.id.vlayout:
                intent = new Intent(this, VLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.broadcast:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.md:
                intent = new Intent(this, MdActivtiy.class);
                startActivity(intent);
                break;

        }
    }
}
