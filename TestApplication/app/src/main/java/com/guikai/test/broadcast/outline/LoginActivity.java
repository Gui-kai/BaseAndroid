package com.guikai.test.broadcast.outline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guikai.test.R;

public class LoginActivity extends BaseActivity {

    private EditText accoutEdit;
    private EditText passEdit;

    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accoutEdit = findViewById(R.id.et_phonenumber);
        passEdit = findViewById(R.id.et_verify_code);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accoutEdit.getText().toString();
                String pass = passEdit.getText().toString();
                if (account.equals("123456") && pass.equals("guikai")) {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"发生错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
