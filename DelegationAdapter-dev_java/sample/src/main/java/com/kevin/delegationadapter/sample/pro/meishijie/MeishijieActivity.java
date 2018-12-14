package com.kevin.delegationadapter.sample.pro.meishijie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.kevin.delegationadapter.DelegationAdapter;
import com.kevin.delegationadapter.extras.span.SpanDelegationAdapter;
import com.kevin.delegationadapter.sample.R;
import com.kevin.delegationadapter.sample.pro.meishijie.adapter.MeishiChannelAdapterDelegate;
import com.kevin.delegationadapter.sample.pro.meishijie.adapter.MeishiSancanAdapterDelegate;
import com.kevin.delegationadapter.sample.pro.meishijie.adapter.MeishiZhuantiAdapterDelegate;
import com.kevin.delegationadapter.sample.pro.meishijie.adapter.MeishiZhuantiTitleAdapterDelegate;
import com.kevin.delegationadapter.sample.pro.meishijie.bean.Meishi;
import com.kevin.delegationadapter.sample.util.LocalFileUtils;

/**
 * MeishijieActivity
 *
 * @author zwenkai@foxmail.com, Created on 2018-06-13 10:59:25
 *         Major Function：<b></b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

public class MeishijieActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DelegationAdapter delegationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meishijie);
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        recyclerView = this.findViewById(R.id.recycler_view);
        // 设置LayoutManager
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        // 设置Adapter
        delegationAdapter = new SpanDelegationAdapter();
        // 添加委托Adapter
        delegationAdapter.addDelegate(new MeishiChannelAdapterDelegate());
        delegationAdapter.addDelegate(new MeishiSancanAdapterDelegate());
        delegationAdapter.addDelegate(new MeishiZhuantiTitleAdapterDelegate());
        delegationAdapter.addDelegate(new MeishiZhuantiAdapterDelegate());
        recyclerView.setAdapter(delegationAdapter);
    }

    private void initData() {
        String meishiStr = LocalFileUtils.getStringFormAsset(this, "meishi.json");
        Meishi meishi = new Gson().fromJson(meishiStr, Meishi.class);
        delegationAdapter.addDataItems(meishi.channel);
        delegationAdapter.addDataItem(meishi.sancan);
        delegationAdapter.addDataItem(meishi.zhuanti);
        delegationAdapter.addDataItems(meishi.zhuanti.items);
    }
}
