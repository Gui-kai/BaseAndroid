package com.example.guikai.myapplication.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guikai.myapplication.R;
import com.example.guikai.myapplication.bean.News;

import java.util.ArrayList;
import java.util.List;

public class NewActivity extends AppCompatActivity {
    private List<News> datas;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        mRecyclerView = findViewById(R.id.recycler_view);
        initData();
        NewsAdapter adapter = new NewsAdapter();
        adapter.setAdapterDatas(datas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //模拟数据
    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            List<String> uir_pics = new ArrayList<>(3);
            uir_pics.add("https://upload-images.jianshu.io/upload_images/14610521-03b1690819d5857d.jpg");
            uir_pics.add("https://upload-images.jianshu.io/upload_images/4458735-cb7fba8c860db01a.jpg");
            uir_pics.add("https://upload-images.jianshu.io/upload_images/13222556-23a21f16c9c63128.jpg");

            List<String> uir_pics0 = new ArrayList<>(3);
            uir_pics0.add("https://upload-images.jianshu.io/upload_images/14008088-c4871df083e17dd9.jpg");

            List<String> uir_pics9 = new ArrayList<>(3);

            List<String> uir_pics1 = new ArrayList<>(3);
            uir_pics1.add("https://upload-images.jianshu.io/upload_images/13222556-23a21f16c9c63128.jpg");
            uir_pics1.add("https://upload-images.jianshu.io/upload_images/4458735-cb7fba8c860db01a.jpg");
            uir_pics1.add("https://upload-images.jianshu.io/upload_images/14610521-03b1690819d5857d.jpg");

            List<String> uir_pics5 = new ArrayList<>(3);
            uir_pics5.add("https://upload-images.jianshu.io/upload_images/4458735-cb7fba8c860db01a.jpg");

            List<String> uir_pics2 = new ArrayList<>(3);
            uir_pics2.add("https://upload-images.jianshu.io/upload_images/4458735-cb7fba8c860db01a.jpg");
            uir_pics2.add("https://upload-images.jianshu.io/upload_images/13222556-23a21f16c9c63128.jpg");
            uir_pics2.add("https://upload-images.jianshu.io/upload_images/191443-7a42ba1b72777f68.png");

            List<String> uir_pics6 = new ArrayList<>(4);
            uir_pics6.add("https://upload-images.jianshu.io/upload_images/4458735-cb7fba8c860db01a.jpg");
            uir_pics6.add("https://upload-images.jianshu.io/upload_images/13222556-23a21f16c9c63128.jpg");
            uir_pics6.add("https://upload-images.jianshu.io/upload_images/191443-7a42ba1b72777f68.png");
            uir_pics6.add("https://upload-images.jianshu.io/upload_images/14610521-03b1690819d5857d.jpg");

            News item = new News(uir_pics, "被老婆辜负后，贾乃亮跟陈羽凡的人生态度，截然不同", "新华网", "09:10", "");
            News item1 = new News(uir_pics0, "Java 11 正式发布，这 8 个逆天新特性教你写出更牛逼的代码", "腾讯科技", "12:10", "");
            News item2 = new News(uir_pics1, "让你成为精致女孩的几个小细节。", "腾讯科技", "12:10", "");
            News item3 = new News(uir_pics5, "Android面试，程序员何苦为难程序员！", "腾讯科技", "12:10", "");
            News item4 = new News(uir_pics2, "电脑桌面上不可缺少的3款黑科技软件，好用到炸！", "腾讯科技", "12:10", "");
            News item5 = new News(uir_pics6, "让你成为精致女孩的几个小细节。", "腾讯科技", "12:10", "");
            News item9 = new News(uir_pics9, "", "", "", "");


            datas.add(item);
            datas.add(item9);
            datas.add(item5);
            datas.add(item1);
            datas.add(item2);
            datas.add(item5);
            datas.add(item3);
            datas.add(item9);
            datas.add(item4);
            datas.add(item5);

        }
    }
}
