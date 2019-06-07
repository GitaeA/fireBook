package com.mobitant.firebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BuyClick extends Fragment implements ServerResponse {

    static String buyTitle;
    static String buyImage;
    static String buyPublisher;
    static String buyAuthor;
    static String buyPrice;
    static String buyDeliver;
    static String buyState;
    static String buyPhone;
    static String buyMemo;
    private TextView clickTitle;
    private ImageView clickImage;
    private TextView clickPublisher;
    private TextView clickAuthor;
    private TextView clickPrice;
    private TextView clickDeliver;
    private TextView clickState;
    private TextView clickPhone;
    private TextView clickMemo;
    private Button jang ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buy_click, container, false);
        jang = root.findViewById(R.id.buy_click_btn);
        clickTitle = root.findViewById(R.id.buy_title_text);
        clickImage = root.findViewById(R.id.book_buy_image);
        clickPublisher = root.findViewById(R.id.buy_publish_text);
        clickAuthor = root.findViewById(R.id.buy_author_text);
        clickPrice = root.findViewById(R.id.buy_price_text);
        clickDeliver = root.findViewById(R.id.buy_deliver_text);
        clickState = root.findViewById(R.id.buy_state_text);
        clickPhone = root.findViewById(R.id.buy_phone_text);
        clickMemo = root.findViewById(R.id.buy_memo_text);

        Glide.with(this).load(buyImage).into(clickImage);
        clickTitle.setText(buyTitle);
        clickPublisher.setText(buyPublisher);
        clickAuthor.setText(buyAuthor);
        clickPrice.setText(buyPrice);

        switch (buyDeliver) {

            case "1":
                clickDeliver.setText("직거래");
                break;
            case "2":
                clickDeliver.setText("택배거래");
                break;
            case "null":
                clickDeliver.setText("미등록");
                break;

        }

        switch (buyState) {

            case "1":
                clickState.setText("최상");
                break;
            case "2":
                clickState.setText("상");
                break;
            case "3":
                clickState.setText("중");
                break;
            case "4":
                clickState.setText("하");
                break;
            case "null":
                clickState.setText("미등록");
                break;

        }
        clickPhone.setText(buyPhone);
        clickMemo.setText(buyMemo);

        jang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;
    }

    @Override
    public void processFinish(String output) {

    }
}