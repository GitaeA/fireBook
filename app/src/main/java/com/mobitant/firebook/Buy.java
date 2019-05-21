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
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buy extends Fragment implements ServerResponse{

    EditText search_content;
    BuyRecyclerViewAdapter buyRecyclerViewAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Buy buy = this;
    List<Books> booksList = new ArrayList<>();
    Button buy_search_button;
    String mSearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View main_activity = inflater.inflate(R.layout.fragment_buy, container, false);

        buy_search_button = main_activity.findViewById(R.id.search_button);
        recyclerView = main_activity.findViewById(R.id.buyRecyclerView);
        search_content = main_activity.findViewById(R.id.search_edit);


        buy_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_click();
            }
        });

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return main_activity;
    }

    public void search_click(){
        mSearch = search_content.getText().toString();
        HashMap<String,String> buy_search = new HashMap<>();
        buy_search.put("search",mSearch);
        new Server().onDb("http://54.180.109.133:4000/search", buy_search,  buy);

    }


    @Override
    public void processFinish(String output) {

    }
}
