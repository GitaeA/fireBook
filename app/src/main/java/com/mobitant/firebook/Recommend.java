package com.mobitant.firebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recommend extends Fragment implements ServerResponse{

    private Button Recommend_button;
    RecommendRecyclerViewAdapter rAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Recommend main_this = this;
    List<RecommendedBook> recommend_items = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View mainActivity = inflater.inflate(R.layout.fragment_recommend, container, false);

        Recommend_button = mainActivity.findViewById(R.id.recommend_button);
        rAdapter = new RecommendRecyclerViewAdapter(recommend_items);
        recyclerView = mainActivity.findViewById(R.id.Recommend_rcy);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rAdapter);

        Recommend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Server().onDb("http://54.180.109.133:4000/ing", null, main_this );
            }
        });


        return inflater.inflate(R.layout.fragment_recommend, container, false);

    }

    @Override
    public void processFinish(String output) {

        try {
            JSONObject jsonObject = new JSONObject(output);

            for(int i=0; i < jsonObject.length(); i++){
                String bookName;
                bookName = jsonObject.toString();
                recommend_items.add(new RecommendedBook(bookName));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}