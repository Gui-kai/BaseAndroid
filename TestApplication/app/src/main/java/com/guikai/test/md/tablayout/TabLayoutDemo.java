package com.guikai.test.md.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.guikai.test.R;

/**
 * Description:
 * Crete by Anding on 2019-10-14
 */
public class TabLayoutDemo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("个性推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("歌单"));
        tabLayout.addTab(tabLayout.newTab().setText("主播电台"));
        tabLayout.addTab(tabLayout.newTab().setText("排行版"));
    }
}
