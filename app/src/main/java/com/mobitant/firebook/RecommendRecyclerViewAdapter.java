package com.mobitant.firebook;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecommendRecyclerViewAdapter extends RecyclerView.Adapter<RecommendRecyclerViewAdapter.ViewHolder> {
    Activity activity;
    List<RecommendedBook> recommend_items;

    public RecommendRecyclerViewAdapter(Activity activity, List<RecommendedBook> recommend_items) {
        this.activity = activity;
        this.recommend_items = recommend_items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView recommend_bookName;
        ImageView recommend_bookImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recommend_bookName = itemView.findViewById(R.id.recommend_name);
            recommend_bookImage = itemView.findViewById(R.id.recommend_image);
        }
    }


    @NonNull
    @Override
    public RecommendRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recommend_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        RecommendedBook data = recommend_items.get(i);


        viewHolder.recommend_bookName.setText(data.getName());
        Glide.with(activity).load(data.getImage_url()).into(viewHolder.recommend_bookImage);


    }

    @Override
    public int getItemCount() {
        return recommend_items.size();
    }
}
