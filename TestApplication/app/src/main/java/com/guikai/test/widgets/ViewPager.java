package com.guikai.test.widgets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.guikai.test.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPager extends AppCompatActivity {

    private List<View> viewList;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        android.support.v4.view.ViewPager viewPager = findViewById(R.id.vp_test);
        viewList = new ArrayList<>();
        View view = View.inflate(this,R.layout.pager_item_one,null);
        viewList.add(view);
        View view1 = View.inflate(this,R.layout.pager_item_two,null);
        viewList.add(view1);
        View view2 = View.inflate(this,R.layout.pager_item_three,null);
        viewList.add(view2);
        MyPagerAdapter adapter = new MyPagerAdapter(viewList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Toast.makeText(getApplicationContext(),"第"+i+"页",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    class MyPagerAdapter extends PagerAdapter{

        private List<View> viewList;

        public MyPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        //判断View是否来自对象
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        //实例化一个页卡

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        //销毁一个页卡
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
