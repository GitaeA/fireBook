package com.mobitant.firebook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SaleClick extends Fragment implements ServerResponse {
    public static String bookBarcode ;
    public static String bookTitle;
    public static String bookPublish;
    public static String bookAuthor;
    public static String bookImage;
    public static String bookPrice;
    private EditText barcodeEditText;
    private EditText bookTitleEditText;
    private EditText bookPublishEditText;
    private EditText bookAuthorEditText;
    private EditText bookPriceEditText ;
    private ImageView bookImageView;
    private Spinner bookLanguageSpinner;
    public static Context context_list; //Spinner context

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sale_click, container, false);

        barcodeEditText = root.findViewById(R.id.edit_barcode);
        bookTitleEditText = root.findViewById(R.id.edit_title);
        bookPublishEditText = root.findViewById(R.id.edit_publish);
        bookAuthorEditText = root.findViewById(R.id.edit_author);
        bookPriceEditText = root.findViewById(R.id.edit_price);
        bookImageView = root.findViewById(R.id.book_image);
        bookLanguageSpinner = root.findViewById(R.id.book_language);

        Glide.with(this).load(bookImage).into(bookImageView);  //이미지 뷰 세팅

        //사용자가 Sale fragment에서 검색하여 클릭한 책 setting
        bookTitleEditText.setText(bookTitle);
        barcodeEditText.setText(bookBarcode);
        bookPublishEditText.setText(bookPublish);
        bookAuthorEditText.setText(bookAuthor);
        bookPriceEditText.setText(bookPrice);

        //Spinner setting

        ArrayList<String> booklist = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SaleClick.context_list, android.R.layout.simple_spinner_item, booklist);
        booklist.add("eng");
        booklist.add("kor");
        booklist.add("etc");
        booklist.add("en-US");
        booklist.add("en-GB");
        booklist.add("en-CA");
        bookLanguageSpinner.setAdapter(adapter);



        return root;
    }

    @Override
    public void processFinish(String output) {

    }
}
