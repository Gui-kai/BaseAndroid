package com.guikai.test.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.guikai.test.R;
import com.guikai.test.StyleView.LoginStyle;
import com.guikai.test.datasave.FileSave;
import com.guikai.test.floatwindow.FloatWindow;
import com.guikai.test.guidepage.GuideActivity;
import com.guikai.test.sliding_tab_layout.MainActivity;
import com.guikai.test.updatedialog.UpdateApk;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    Button btn_test;
    Button btn_guide;
    Button btn_float_widow;
    Button btn_data_save;
    Button btn_sp;
    Button btn_sli_tab_layout;
    Button btn_login;
    Button btn_update_dialog;
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

        btn_float_widow.setOnClickListener(this);
        btn_guide.setOnClickListener(this);
        btn_data_save.setOnClickListener(this);
        btn_sp.setOnClickListener(this);
        btn_sli_tab_layout.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_update_dialog.setOnClickListener(this);
        btn_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guide:
                intent= new Intent(IndexActivity.this,GuideActivity.class);
                startActivity(intent);
                break;
            case R.id.float_widow:
                intent= new Intent(IndexActivity.this,FloatWindow.class);
                startActivity(intent);
                break;
            case R.id.data_save:
                intent= new Intent(IndexActivity.this,FileSave.class);
                startActivity(intent);
                break;
            case R.id.sli_tab_layout:
                intent= new Intent(IndexActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.update_dialog:
                intent= new Intent(IndexActivity.this,UpdateApk.class);
                startActivity(intent);
                break;
            case R.id.login:
                intent= new Intent(IndexActivity.this,LoginStyle.class);
                startActivity(intent);
                break;
            case R.id.test:
                intent = new Intent("guikai");
                startActivity(intent);
                break;

        }
    }
}