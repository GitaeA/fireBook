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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class BuyClick extends Fragment implements ServerResponse {
    BuyClick buyClick;
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
    private Button jang;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buy_click, container, false);
        jang = root.findViewById(R.id.buy_click_btn);
        buyClick = this;
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

                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("title", buyTitle);
                parameter.put("image", buyImage);
                parameter.put("price", buyPrice);
                parameter.put("phone", buyPhone);
                parameter.put("user_id", Login.sid);
                new Server().onDb("http://54.180.107.154:4000/jang", parameter, buyClick);
                Toast.makeText(getContext(), "장바구니에 들어 갔습니다!!", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    @Override
    public void processFinish(String output) {

    }
}