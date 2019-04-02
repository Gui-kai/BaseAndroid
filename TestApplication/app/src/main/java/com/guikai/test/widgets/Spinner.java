package com.guikai.test.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guikai.test.R;

import java.util.ArrayList;
import java.util.List;

public class Spinner extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        android.widget.Spinner spinner = findViewById(R.id.spinner);
        final TextView textView = findViewById(R.id.tv_spinner);
        List<String> list = new ArrayList<>();
        list.add("大一");
        list.add("大二");
        list.add("大三");
        list.add("大四");

        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list);

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String grade = arrayAdapter.getItem(position);
                textView.setText("你选择的值是" + grade);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
