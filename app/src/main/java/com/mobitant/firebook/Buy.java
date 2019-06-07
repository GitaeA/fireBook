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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buy extends Fragment implements ServerResponse, BuyRecyclerViewAdapter.BuyRecyclerViewClickListener {

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

    public void search_click() {
        mSearch = search_content.getText().toString();
        HashMap<String, String> buy_search = new HashMap<>();
        buy_search.put("search", mSearch);
        new Server().onDb("http://54.180.109.133:4000/search", buy_search, buy);

    }


    @Override
    public void processFinish(String output) {

        try {
            JSONArray jsonArray = new JSONArray(output);

            booksList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {

                booksList.add(new Books(jsonArray.getJSONObject(i).getString("title"),
                        jsonArray.getJSONObject(i).getString("image_url"),
                        jsonArray.getJSONObject(i).getString("publisher"),
                        jsonArray.getJSONObject(i).getString("authors"),
                        jsonArray.getJSONObject(i).getString("state"),
                        jsonArray.getJSONObject(i).getString("price")));

            }

            buyRecyclerViewAdapter = new BuyRecyclerViewAdapter(getActivity(), booksList);
            recyclerView.setAdapter(buyRecyclerViewAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getContext(), position + " 번 아이템 클릭됨", Toast.LENGTH_SHORT).show();
    }
}