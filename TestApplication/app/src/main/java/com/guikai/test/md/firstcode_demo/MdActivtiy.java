package com.guikai.test.md.firstcode_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.guikai.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MdActivtiy extends AppCompatActivity {

    private Fruit[] fruitList = {new Fruit("Apple", R.drawable.login),
            new Fruit("Apple", R.drawable.login), new Fruit("Apple", R.drawable.login), new Fruit("Apple", R.drawable.login),
            new Fruit("Apple", R.drawable.login), new Fruit("Apple", R.drawable.login), new Fruit("Apple", R.drawable.login),
            new Fruit("Apple", R.drawable.login)};

    private List<Fruit> fruitsList = new ArrayList<>();

    private FruitAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        initFruits();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "点击了", Snackbar.LENGTH_SHORT)
                        .setAction("好的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MdActivtiy.this, "toast", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        adapter = new FruitAdapter(fruitsList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        fruitsList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruitList.length);
            fruitsList.add(fruitList[index]);
        }
    }
}
