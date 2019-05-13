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

public class SaleRecyclerViewAdapter extends RecyclerView.Adapter<SaleRecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private List<BookCompo> compo;

    public SaleRecyclerViewAdapter(Activity activity, List<BookCompo> compo) {
        this.activity = activity;
        this.compo = compo;

    }

    @Override
    public int getItemCount() {
        return compo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView book_image;
        private TextView book_title;
        private TextView book_price;
        private TextView book_publish;
        private TextView book_author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_image = itemView.findViewById(R.id.book_image);
            book_title = itemView.findViewById(R.id.book_title);
            book_price = itemView.findViewById(R.id.book_price);
            book_publish = itemView.findViewById(R.id.book_publish);
            book_author = itemView.findViewById(R.id.book_author);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sale_recylce_adapter, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        BookCompo data = compo.get(i);

        // 데이터 결합
        // viewHolder.name.setText(data.getName());

        //  oee = PC.getPieChart();
        //data setting! haha hoho
        //  viewHolder.book_test.setText(data.getImage_url());
//        Glide.with(activity).load(data.getImage_url()).into(viewHolder.book_image);
        Glide.with(activity).load(data.getImage_url()).into(viewHolder.book_image);
        viewHolder.book_title.setText(data.getBook_text());
        viewHolder.book_price.setText(data.getBook_price() + "원");
        viewHolder.book_author.setText(data.getBook_author());
        viewHolder.book_publish.setText(data.getBook_publish());

         viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Compo data = compo.get(i);

                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout,new SaleClick());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
           //   click event




    private void removeItemView(int position) {
        compo.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, compo.size()); // 지워진 만큼 다시 채워넣기.
    }

}
