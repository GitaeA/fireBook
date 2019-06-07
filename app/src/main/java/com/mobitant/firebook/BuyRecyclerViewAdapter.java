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

public class BuyRecyclerViewAdapter extends RecyclerView.Adapter<BuyRecyclerViewAdapter.ViewHolder> {

    Activity activity;
    List<Books> booksList;
    private BuyRecyclerViewClickListener mListener;

    public BuyRecyclerViewAdapter(Activity activity, List<Books> booksList) {
        this.activity = activity;
        this.booksList = booksList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mName, mAuthor, mState, mPublisher, mPrice, mDeliver;

        public ImageView mBook_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDeliver = itemView.findViewById(R.id.buy_deliever);
            mName = itemView.findViewById(R.id.buy_name);
            mAuthor = itemView.findViewById(R.id.buy_author);
            mState = itemView.findViewById(R.id.buy_state);
            mPublisher = itemView.findViewById(R.id.buy_publisher);
            mPrice = itemView.findViewById(R.id.buy_price);
            mBook_image = itemView.findViewById(R.id.buy_image);

        }
    }

    @NonNull
    @Override
    public BuyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.buy_item, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull BuyRecyclerViewAdapter.ViewHolder viewHolder, int position) {

        Books data = booksList.get(position);

        viewHolder.mName.setText(data.getName());
        viewHolder.mAuthor.setText(data.getAuthor());
        switch (data.getState()) {
            case "1":
                viewHolder.mState.setText("책상태 : 최상");
                break;
            case "2":
                viewHolder.mState.setText("책상태 : 상");
                break;
            case "3":
                viewHolder.mState.setText("책상태 : 중");
                break;
            case "4":
                viewHolder.mState.setText("책상태 : 하");
                break;
            case "NULL":
                viewHolder.mState.setText("책상태 : 미등록");
                break;
        }
        switch (data.getDeliever()) {
            case "1":
                viewHolder.mDeliver.setText("직거래");
                break;
            case "2":
                viewHolder.mDeliver.setText("택배거래");
                break;
            case "NULL":
                viewHolder.mDeliver.setText("미등록");
                break;

        }


        viewHolder.mPublisher.setText(data.getPublisher());
        viewHolder.mPrice.setText(data.getPrice());
        Glide.with(activity).load(data.getImage_url()).centerCrop().override(300, 500).into(viewHolder.mBook_image);


        //클릭 이벤트 구현
        if (mListener != null) {
            final int pos = position;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }


    public interface BuyRecyclerViewClickListener {

        void onItemClicked(int position);

    }

    public void setOnClickListener(BuyRecyclerViewClickListener listener) {
        mListener = listener;
    }


}