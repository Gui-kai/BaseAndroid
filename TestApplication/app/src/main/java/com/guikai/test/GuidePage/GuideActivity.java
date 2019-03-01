package com.guikai.test.GuidePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guikai.test.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    //容器
    private List<View> mList = new ArrayList<>();
    private View[] mViews;
    //小圆点
    private ImageView point1, point2, point3;
    //跳过
    private ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.hide();
        }
        initView();
    }

    //初始化View
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        mViews = new View[3];
        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);


        //设置默认的图片
        setPointImg(true, false, false);

        mViews[0] = View.inflate(this, R.layout.pager_item_one, null);
        mViews[1] = View.inflate(this, R.layout.pager_item_two, null);
        mViews[2] = View.inflate(this, R.layout.pager_item_three, null);

        mList.add(mViews[0]);
        mList.add(mViews[1]);
        mList.add(mViews[2]);

        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());

        //监听ViewPager的滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        iv_back.setVisibility(View.VISIBLE);
                        setPointImg(true, false, false);
                        break;
                    case 1:
                        iv_back.setVisibility(View.VISIBLE);
                        setPointImg(false, true, false);
                        break;
                    case 2:
                        iv_back.setVisibility(View.GONE);
                        setPointImg(false, false, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
            case R.id.iv_back:
                startActivity(new Intent(this, null));
                finish();
                break;
        }
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        //增加两个 重写删除和添加 滑动pager中

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ((ViewPager) container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView(mList.get(position));

        }
    }

    //设置小圆点的选中效果
    private void setPointImg(boolean isCheck1, boolean isCheck2, boolean isCheck3) {
        if (isCheck1) {
            point1.setBackgroundResource(R.drawable.point_on);
        } else {
            point1.setBackgroundResource(R.drawable.point_off);
        }

        if (isCheck2) {
            point2.setBackgroundResource(R.drawable.point_on);
        } else {
            point2.setBackgroundResource(R.drawable.point_off);
        }

        if (isCheck3) {
            point3.setBackgroundResource(R.drawable.point_on);
        } else {
            point3.setBackgroundResource(R.drawable.point_off);
        }

    }
}
