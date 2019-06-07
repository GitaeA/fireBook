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

import static com.mobitant.firebook.BuyClick.buyDeliver;
import static com.mobitant.firebook.BuyClick.buyState;
import static com.mobitant.firebook.BuyClick.*;
import static com.mobitant.firebook.MainActivity.fragmentManager;
import static com.mobitant.firebook.MainActivity.transaction;
import static com.mobitant.firebook.SaleClick.bookAuthor;
import static com.mobitant.firebook.SaleClick.bookBarcode;
import static com.mobitant.firebook.SaleClick.bookImage;
import static com.mobitant.firebook.SaleClick.bookPrice;
import static com.mobitant.firebook.SaleClick.bookPublish;
import static com.mobitant.firebook.SaleClick.bookTitle;

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
    public void onBindViewHolder(@NonNull BuyRecyclerViewAdapter.ViewHolder viewHolder, final int position) {

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

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Books data = booksList.get(position);
                buyTitle = data.getName();
                buyImage = data.getImage_url();
                buyPublisher = data.getPublisher();
                buyPrice = data.getPrice();
                buyDeliver = data.getDeliever();
                buyAuthor = data.getAuthor();
                buyState = data.getState();
                buyPhone = data.getPhone();
                buyMemo = data.getMemo();
//                bookAuthor = data.getBook_author();
//                bookImage = data.getImage_url();
//                bookPublish = data.getBook_publish();
//                bookTitle = data.getBook_text();
//                bookPrice = data.getBook_price();


                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, new BuyClick());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
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