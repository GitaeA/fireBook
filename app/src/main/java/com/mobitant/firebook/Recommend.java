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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recommend extends Fragment implements ServerResponse {

    String text;
    //Button Recommend_button;
    RecommendRecyclerViewAdapter rAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Recommend main_this = this;
    List<RecommendedBook> recommend_items = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {


        View main_activity = inflater.inflate(R.layout.fragment_recommend, container, false);

        Button Recommend_button;
        Recommend_button = main_activity.findViewById(R.id.recommend_Button);
        recyclerView = main_activity.findViewById(R.id.Recommend_rcy);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Recommend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Server().onDb("http://54.180.109.133:4000/test",null,main_this);
            }
        });


        return inflater.inflate(R.layout.fragment_recommend, container, false);

    }

    @Override
    public void processFinish(String output) {

        try {
            JSONArray jsonArray = new JSONArray(output);

            recommend_items.add(new RecommendedBook(jsonArray.getJSONObject(0).getString("title")
                    , jsonArray.getJSONObject(0).getString("image_url")));

            rAdapter = new RecommendRecyclerViewAdapter(getActivity(), recommend_items);
            recyclerView.setAdapter(rAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


//        try {
//            JSONArray jsonArray = new JSONArray(output);
//
//            for(int i=0; i < jsonArray.length(); i++){
//
//                recommend_items.add(new RecommendedBook(jsonArray.getJSONObject(0).getString("title"),
//                        jsonArray.getJSONObject(0).getString("image_url")));
//
//
//            }
//
//            rAdapter = new RecommendRecyclerViewAdapter(getActivity(), recommend_items);
//            recyclerView.setAdapter(rAdapter);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }
}