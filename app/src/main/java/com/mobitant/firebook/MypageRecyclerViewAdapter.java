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

import static com.mobitant.firebook.MainActivity.fragmentManager;
import static com.mobitant.firebook.MainActivity.transaction;
import static com.mobitant.firebook.SaleClick.bookAuthor;
import static com.mobitant.firebook.SaleClick.bookBarcode;
import static com.mobitant.firebook.SaleClick.bookImage;
import static com.mobitant.firebook.SaleClick.bookPrice;
import static com.mobitant.firebook.SaleClick.bookPublish;
import static com.mobitant.firebook.SaleClick.bookTitle;

public class MypageRecyclerViewAdapter extends RecyclerView.Adapter<MypageRecyclerViewAdapter.ViewHolder> {

    private Activity activity;
    private List<MyCompo> myCompo;

    public MypageRecyclerViewAdapter(Activity activity, List<MyCompo> myCompo) {
        this.activity = activity;
        this.myCompo = myCompo;

    }

    @Override
    public int getItemCount() {
        return myCompo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView page_image;
        private TextView page_title;
        private TextView page_price;
        private TextView page_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            page_image = itemView.findViewById(R.id.page_image_my);
            page_title = itemView.findViewById(R.id.book_title_page);
            page_price = itemView.findViewById(R.id.book_price_page);

            page_phone = itemView.findViewById(R.id.book_publish_page);

        }
    }


    @NonNull
    @Override
    public MypageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_page_recycler, viewGroup, false);
        MypageRecyclerViewAdapter.ViewHolder viewHolder = new MypageRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MypageRecyclerViewAdapter.ViewHolder viewHolder, final int i) {
        MyCompo data = myCompo.get(i);


        Glide.with(activity).load(data.getImage_url()).centerCrop().override(300,500).into(viewHolder.page_image);
        viewHolder.page_title.setText(data.getBook_text());
        viewHolder.page_price.setText(data.getBook_price()+"원");
        viewHolder.page_phone.setText(data.getBook_publish());


    }

    private void removeItemView(int position) {
        myCompo.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, myCompo.size()); // 지워진 만큼 다시 채워넣기.
    }

}