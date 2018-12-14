package com.example.guikai.myapplication.news;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guikai.myapplication.R;
import com.example.guikai.myapplication.bean.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<News> mDatas;


    private RecyItemAdapter adapter = new RecyItemAdapter();

    public void setAdapterDatas(List<News> datas) {
        mDatas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
            return new OnePicViewHolder(view);
        } else if (i == 2) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_three_item, viewGroup, false);
            return new ThreePicViewHolder(view);
        } else if (i == 3) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_more_pic, viewGroup, false);
            return new MorePicViewHolder(view);
        } else {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);

            return new RecyItemViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = viewHolder.getItemViewType();
        if (viewType == 1) {
            OnePicViewHolder oneViewHolder = (OnePicViewHolder) viewHolder;
            News news = mDatas.get(i);
            oneViewHolder.tvContent.setText(news.getContent());
            oneViewHolder.tvSource.setText(news.getSource());
            oneViewHolder.tvTime.setText(news.getTime());
            Glide.with(viewHolder.itemView.getContext()).load(news.getImgUrl().get(0)).into(oneViewHolder.ivPic);
        } else if (viewType == 2) {
            ThreePicViewHolder threeViewHolder = (ThreePicViewHolder) viewHolder;
            News news = mDatas.get(i);
            threeViewHolder.tvContent.setText(news.getContent());
            threeViewHolder.tvSource.setText(news.getSource());
            threeViewHolder.tvTime.setText(news.getTime());
            Glide.with(viewHolder.itemView.getContext()).load(news.getImgUrl().get(0)).into(threeViewHolder.ivPic1);
            Glide.with(viewHolder.itemView.getContext()).load(news.getImgUrl().get(1)).into(threeViewHolder.ivPic2);
            Glide.with(viewHolder.itemView.getContext()).load(news.getImgUrl().get(2)).into(threeViewHolder.ivPic3);
        } else if (viewType == 3) {
            MorePicViewHolder morePicViewHolder = (MorePicViewHolder) viewHolder;
            News news = mDatas.get(i);
            morePicViewHolder.tvContent.setText(news.getContent());
            morePicViewHolder.tvSource.setText(news.getSource());
            morePicViewHolder.tvTime.setText(news.getTime());
            morePicViewHolder.tvCount.setText("图"+4);
            Glide.with(viewHolder.itemView.getContext()).load(news.getImgUrl().get(0)).into(morePicViewHolder.ivPic);
        } else {
            RecyItemViewHolder recyItemViewHolder = (RecyItemViewHolder) viewHolder;
            recyItemViewHolder.mRecyclerView.setAdapter(adapter);

           //控件赋值
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        News news = mDatas.get(position);
        int imgSize = news.getImgUrl().size();
        if (imgSize == 1 ) {
            return 1;
        } else if (imgSize == 3) {
            return 2;
        } else  if (imgSize == 0){
            return 0;
        } else {
            return 3;
        }
    }

    class OnePicViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPic;
        TextView tvContent;
        TextView tvSource;
        TextView tvTime;

        public OnePicViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.iv_pic);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

    class ThreePicViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPic1;
        ImageView ivPic2;
        ImageView ivPic3;
        TextView tvContent;
        TextView tvSource;
        TextView tvTime;

        public ThreePicViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic1 = itemView.findViewById(R.id.iv_pic1);
            ivPic2 = itemView.findViewById(R.id.iv_pic2);
            ivPic3 = itemView.findViewById(R.id.iv_pic3);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvTime = itemView.findViewById(R.id.tv_time);

        }
    }

    class MorePicViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvContent;
        TextView tvCount;
        TextView tvSource;
        TextView tvTime;

        public MorePicViewHolder(View view) {
            super(view);
            ivPic = view.findViewById(R.id.iv_pic);
            tvContent = view.findViewById(R.id.tv_content);
            tvCount = view.findViewById(R.id.tv_count);
            tvSource = view.findViewById(R.id.tv_source);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }

    class RecyItemViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mRecyclerView;
        public RecyItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);

        }
    }
}
