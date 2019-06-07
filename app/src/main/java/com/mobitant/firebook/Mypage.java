package com.mobitant.firebook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Mypage extends Fragment {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mypage_activity = inflater.inflate(R.layout.fragment_my, container, false);

//        recyclerView = mypage_activity.findViewById(R.id.buyRecyclerView);
//
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);

        return mypage_activity;
    }
}
