package com.mobitant.firebook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buy extends Fragment {

    EditText search_content;
    BuyRecyclerViewAdapter buyRecyclerViewAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Buy buy = this;
    List<Books> booksList = new ArrayList<>();
    Button buy_button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View main_activity = inflater.inflate(R.layout.fragment_buy, container, false);

        search_content = main_activity.findViewById(R.id.search_edit);

        //buy_button = main_activity.findViewById(R.id.);

        return main_activity;
    }


}
