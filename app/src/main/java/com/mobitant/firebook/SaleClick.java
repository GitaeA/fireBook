package com.mobitant.firebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SaleClick extends Fragment implements ServerResponse {
    public static String bookBarcode ;
    public static String bookTitle;
    public static String bookPublish;
    public static String bookAuthor;
    public static String bookImage;
    public static String bookPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sale_click, container, false);







        return root;
    }

    @Override
    public void processFinish(String output) {

    }
}
