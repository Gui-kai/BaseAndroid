package com.guikai.test.sliding_tab_layout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.guikai.test.R;
import com.guikai.test.sliding_tab_layout.slidingtablayout.SlidingTabLayout;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

/**
 * https://github.com/xuehuayous/SlidingTabLayout
 */

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SlidingTabLayout mTabLayout0;
    private SlidingTabLayout mTabLayout1;
    private SlidingTabLayout mTabLayout2;
    private SlidingTabLayout mTabLayout3;
    private SlidingTabLayout mTabLayout4;
    private SlidingTabLayout mTabLayout5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);

        mViewPager = findViewById(R.id.view_pager);
//        mTabLayout0 = findViewById(R.id.tab_layout0);
//        mTabLayout1 = findViewById(R.id.tab_layout1);
//        mTabLayout2 = findViewById(R.id.tab_layout2);
//        mTabLayout3 = findViewById(R.id.tab_layout3);
        mTabLayout4 = findViewById(R.id.tab_layout4);
//        mTabLayout5 = findViewById(R.id.tab_layout5);

        Adapter adapter = new Adapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);

//        mTabLayout0.setViewPager(mViewPager);
//        mTabLayout1.setViewPager(mViewPager);
//        mTabLayout2.setViewPager(mViewPager);
//        mTabLayout3.setViewPager(mViewPager);
        mTabLayout4.setViewPager(mViewPager);
//        mTabLayout5.setViewPager(mViewPager);

//        mTabLayout0.setDividerColors(Color.BLUE, Color.RED, Color.GREEN);
//        mTabLayout0.setIndicatorColors(Color.BLUE, Color.RED, Color.GREEN);
        mTabLayout4.setOnColorChangedListener(new SlidingTabLayout.OnColorChangeListener() {
            @Override
            public void onColorChanged(int color) {
                findViewById(R.id.toolbar).setBackgroundColor(color);
                StatusBarUtil.setColor(MainActivity.this, color);
            }
        });

        mTabLayout4.setIndicatorColors(
                Color.parseColor("#EC0000"),
                Color.parseColor("#EC0000"),
                Color.parseColor("#8119EA"),
                Color.parseColor("#CA7D00")
        );
    }

    static class Adapter extends SlidingTabLayout.SlidingTabPageAdapter {

        private final Context mContext;
        ArrayList<String> titles = new ArrayList<>();
        private ArrayList<Fragment> fragments = new ArrayList<>();

        private int[] icons = {R.mipmap.ic_recommend, R.mipmap.ic_free, R.mipmap.ic_path, R.mipmap.ic_actual};

        public Adapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;

            titles.add("推荐");
            titles.add("课程");
            titles.add("路径");
            titles.add("实战");
            titles.add("推荐");
            titles.add("关注");
            titles.add("新时代");
            titles.add("呼号和特");
            titles.add("科技");
            titles.add("健康");
            titles.add("关注");
            titles.add("新时代");
            titles.add("呼号和特");

            for (int i = 0; i < 10; i++) {
                MainFragment fragment = new MainFragment();
                fragment.setTitle(titles.get(i));
                fragments.add(fragment);
            }

        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
//            return "标题" + position;
            return titles.get(position);
        }

        @Override
        public Drawable getDrawable(int position) {
//            return mContext.getResources().getDrawable(icons[position]);
            return null;
        }
    }
}