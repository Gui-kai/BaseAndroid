package com.guikai.test.md.firstcode_demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guikai.test.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> daras;

    public FruitAdapter(List<Fruit> daras) {
        this.daras = daras;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.fruit_item, null, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(parent.getContext(), AppbarlayoutActivity.class);
                intent.putExtra("name", daras.get(position).getName());
                intent.putExtra("img", daras.get(position).getImageId());
                parent.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = daras.get(position);
        holder.imageView.setId(fruit.getImageId());
        holder.textView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return daras.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.fruit_name);
            imageView = itemView.findViewById(R.id.fruit_image);
        }
    }
}
