package com.guikai.test.datasave;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guikai.test.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class FileSave extends AppCompatActivity {

    private EditText editText;
    private Button mSaveButton;
    private Button mOpenButton;
    private TextView textView;
    BufferedWriter bufferedWriter = null;
    BufferedReader bufferedReader = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);
        editText = findViewById(R.id.et_file_save);
        mSaveButton = findViewById(R.id.bt_save);
        mOpenButton = findViewById(R.id.bt_open);
        textView = findViewById(R.id.tv_open_file);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!text.isEmpty()) {
                    try {
                        FileOutputStream fileOutputStream = openFileOutput("file_data", MODE_PRIVATE);
//                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//                        bufferedWriter.write(text);
                        fileOutputStream.write(text.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder stringBuilder = new StringBuilder();

                try {
                    FileInputStream inputStream = openFileInput("file_data");
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }
//                    byte[] bytes = new byte[100];
//                    int byteCount = 0;
//                    byteCount = inputStream.read(bytes);
//                    String open = new String(bytes, 0, byteCount);
//                    textView.setText(open);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader!=null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                String open = stringBuilder.toString();
                if (!TextUtils.isEmpty(open)) {
                    textView.setText(open);
                }
            }
        });
    }
}
