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
    private List<BookCompo> myCompo;

    public MypageRecyclerViewAdapter(Activity activity, List<BookCompo> myCompo) {
        this.activity = activity;
        this.myCompo = myCompo;

    }

    @Override
    public int getItemCount() {
        return myCompo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView my_book_image;
        private TextView my_book_title;
        private TextView my_book_price;
        private TextView my_book_publish;
        private TextView my_book_author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            book_image = itemView.findViewById(R.id.book_image);
//            book_title = itemView.findViewById(R.id.book_title);
//            book_price = itemView.findViewById(R.id.book_price);
//            book_publish = itemView.findViewById(R.id.book_publish);
//            book_author = itemView.findViewById(R.id.book_author);
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
        BookCompo data = myCompo.get(i);

        // 데이터 결합
        // viewHolder.name.setText(data.getName());

        //  oee = PC.getPieChart();
        //data setting! haha hoho
        //  viewHolder.book_test.setText(data.getImage_url());
//        Glide.with(activity).load(data.getImage_url()).into(viewHolder.book_image);
//        Glide.with(activity).load(data.getImage_url()).centerCrop().override(300,500).into(viewHolder.book_image);
//        viewHolder.book_title.setText(data.getBook_text());
//        viewHolder.book_price.setText(data.getBook_price() + "원");
//        viewHolder.book_author.setText(data.getBook_author());
//        viewHolder.book_publish.setText(data.getBook_publish());


    }

    private void removeItemView(int position) {
        myCompo.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, myCompo.size()); // 지워진 만큼 다시 채워넣기.
    }

}
