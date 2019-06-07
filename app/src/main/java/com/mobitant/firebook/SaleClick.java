package com.mobitant.firebook;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class SaleClick extends Fragment implements ServerResponse {
    public static String bookBarcode;
    public static String bookTitle;
    public static String bookPublish;
    public static String bookAuthor;
    public static String bookImage;
    public static String bookPrice;
    public static int book_id = 5343;
    private TextView barcodeTextView;
    private TextView bookTitleTextView;
    private TextView bookPublishTextView;
    private TextView bookAuthorTextView;
    private TextView bookPriceTextView;
    private ImageView bookImageView;
    private Spinner bookLanguageSpinner;
    private Spinner bookDeliverSpinner;
    public int deliverCase;
    private EditText bookSalePriceEditText;
    private Button state1;
    private Button state2;
    private Button state3;
    private Button state4;
    private TextView stateTextView;
    public int bookState;
    private EditText memoEditText;
    private Button enrollmentButton;
    SaleClick thiss = this;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sale_click, container, false);

        barcodeTextView = root.findViewById(R.id.edit_barcode);
        bookTitleTextView = root.findViewById(R.id.edit_title);
        bookPublishTextView = root.findViewById(R.id.edit_publish);
        bookAuthorTextView = root.findViewById(R.id.edit_author);
        bookPriceTextView = root.findViewById(R.id.edit_price);
        bookImageView = root.findViewById(R.id.book_image);
        bookLanguageSpinner = root.findViewById(R.id.book_language);
        bookDeliverSpinner = root.findViewById(R.id.deliver_spinner);
        bookSalePriceEditText = root.findViewById(R.id.edit_sale_price);
        state1 = root.findViewById(R.id.choi_btn);
        state2 = root.findViewById(R.id.upper_btn);
        state3 = root.findViewById(R.id.middle_btn);
        state4 = root.findViewById(R.id.down_btn);
        stateTextView = root.findViewById(R.id.state_textview);
        enrollmentButton = root.findViewById(R.id.enrollment_btn);
        memoEditText = root.findViewById(R.id.edit_memo);
        Glide.with(this).load(bookImage).into(bookImageView);  //이미지 뷰 세팅

        //사용자가 Sale fragment에서 검색하여 클릭한 책 setting
        bookTitleTextView.setText(bookTitle);
        barcodeTextView.setText(bookBarcode);
        bookPublishTextView.setText(bookPublish);
        bookAuthorTextView.setText(bookAuthor);
        bookPriceTextView.setText(bookPrice);


        //Spinner setting

        ArrayList<String> booklist = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, booklist);
        booklist.add("eng");
        booklist.add("kor");
        booklist.add("etc");
        booklist.add("en-US");
        booklist.add("en-GB");
        booklist.add("en-CA");
        bookLanguageSpinner.setAdapter(adapter);

        ArrayList<String> bookDeliver = new ArrayList<>();
        ArrayAdapter<String> deliverAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, bookDeliver);

        bookDeliver.add("직거래");
        bookDeliver.add("택배거래");


        bookDeliverSpinner.setAdapter(deliverAdapter);

        bookDeliverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if ((bookDeliverSpinner.getSelectedItem().toString()).equals("직거래")) //판매자가 spinner에서 선택한 값
                    deliverCase = 1;        //직거래  DB table에 넘겨줄 숫자
                else
                    deliverCase = 2;  //택배거래
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        state1.setBackgroundColor(Color.BLUE);
        state2.setBackgroundColor(Color.GRAY);
        state3.setBackgroundColor(Color.GRAY);
        state4.setBackgroundColor(Color.GRAY);


        state1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateTextView.setText("새 상품같이 깨끗한 상품");
                bookState = 1;
                state1.setBackgroundColor(Color.BLUE);
                state2.setBackgroundColor(Color.GRAY);
                state3.setBackgroundColor(Color.GRAY);
                state4.setBackgroundColor(Color.GRAY);
            }
        });

        state2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateTextView.setText("사용흔적이 약간 있으나,비교적 깨끗한 상품");
                state1.setBackgroundColor(Color.GRAY);
                state2.setBackgroundColor(Color.BLUE);
                state3.setBackgroundColor(Color.GRAY);
                state4.setBackgroundColor(Color.GRAY);
                bookState = 2;
            }
        });

        state3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateTextView.setText("사용흔적 많으나, 손상 없는 상품");
                bookState = 3;
                state1.setBackgroundColor(Color.GRAY);
                state2.setBackgroundColor(Color.GRAY);
                state3.setBackgroundColor(Color.BLUE);
                state4.setBackgroundColor(Color.GRAY);
            }
        });

        state4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateTextView.setText("사용흔적이 많고, 손상이 있는 상품");
                bookState = 4;
                state1.setBackgroundColor(Color.GRAY);
                state2.setBackgroundColor(Color.GRAY);
                state3.setBackgroundColor(Color.GRAY);
                state4.setBackgroundColor(Color.BLUE);
            }
        });

        enrollmentButton.setOnClickListener(new View.OnClickListener() {  //최종등록
            @Override
            public void onClick(View v) {

                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("sid", Login.sid);  // 회원 아이디
                parameter.put("book_id", String.valueOf(book_id));
                parameter.put("isbn", bookBarcode);
                parameter.put("authors", bookAuthor);
                parameter.put("title", bookTitle);
                parameter.put("language", bookLanguageSpinner.getSelectedItem().toString());
                parameter.put("image", bookImage);
                parameter.put("memo", memoEditText.getText().toString());
                parameter.put("price", bookSalePriceEditText.getText().toString());
                parameter.put("publish", bookPublish);
                parameter.put("state", String.valueOf(bookState));
                parameter.put("deliver", String.valueOf(deliverCase));

                new Server().onDb("http://54.180.107.154/sale",parameter,thiss);
                book_id++;
                Toast.makeText(getContext(),"등록이 완료 되었습니다!!!",Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }

    @Override
    public void processFinish(String output) {

    }
}
