package com.mobitant.firebook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SaleClick extends Fragment implements ServerResponse {
    public static String bookBarcode;
    public static String bookTitle;
    public static String bookPublish;
    public static String bookAuthor;
    public static String bookImage;
    public static String bookPrice;
    private TextView barcodeTextView;
    private TextView bookTitleTextView;
    private TextView bookPublishTextView;
    private TextView bookAuthorTextView;
    private TextView bookPriceTextView;
    private ImageView bookImageView;
    private Spinner bookLanguageSpinner;
    private Spinner bookDeliverSpinner;
    private int deliverCase;

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


        return root;
    }

    @Override
    public void processFinish(String output) {

    }
}
