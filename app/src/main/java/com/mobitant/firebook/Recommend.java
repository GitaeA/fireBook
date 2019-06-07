package com.mobitant.firebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recommend extends Fragment implements ServerResponse, SwipeRefreshLayout.OnRefreshListener {

    RecommendRecyclerViewAdapter rAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Recommend recommend = this ;
    List<RecommendedBook> recommend_items = new ArrayList<>();
    Button Recommend_button;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        new Server().onDb("http://54.180.107.154:4000/test",null,recommend);

        View main_activity = inflater.inflate(R.layout.fragment_recommend, container, false);

        //Recommend_button = main_activity.findViewById(R.id.recommend_Button);
        recyclerView = main_activity.findViewById(R.id.Recommend_rcy);
        mSwipeRefreshLayout =  main_activity.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

//        Recommend_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Server().onDb("http://54.180.107.154:4000/test",null,recommend);
//            }
//        });

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        return main_activity;

    }

    @Override
    public void processFinish(String output) {

        try {
            JSONArray jsonArray = new JSONArray(output);

            recommend_items.clear();

            for(int i=0; i < jsonArray.length(); i++){

                recommend_items.add(new RecommendedBook(jsonArray.getJSONObject(i).getString("title"),
                        jsonArray.getJSONObject(i).getString("image_url")));

            }

            rAdapter = new RecommendRecyclerViewAdapter(getActivity(), recommend_items);
            recyclerView.setAdapter(rAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onRefresh() {
        Log.d("Dd","dd");
        new Server().onDb("http://54.180.107.154:4000/test",null,recommend);
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
