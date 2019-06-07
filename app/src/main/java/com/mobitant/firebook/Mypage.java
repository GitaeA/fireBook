package com.mobitant.firebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Mypage extends Fragment implements ServerResponse {

    TextView Myphone;
    TextView Myname;
    TextView Myid;
    private Context context;

    private LinearLayoutManager layoutManager;
    Button logoutBtn;
    Activity root = getActivity();
    List<Mypage> myCompo = new ArrayList<>();
    public static RecyclerView myRecyclerView;
    public MypageRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mypage_activity = inflater.inflate(R.layout.fragment_my, container, false);

        Myphone = mypage_activity.findViewById(R.id.text_myPhone);
        Myname = mypage_activity.findViewById(R.id.text_myNick);
        Myid = mypage_activity.findViewById(R.id.text_myID);
        Myid.setText(Login.sid);
        Myname.setText(Login.snick);
        Myphone.setText(Login.sphone);
        context = container.getContext();

        logoutBtn = (Button) mypage_activity.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


//        myRecyclerView = mypage_activity.findViewById(R.id.buyRecyclerView);
////
//
//        layoutManager = new LinearLayoutManager(getActivity());
//        myRecyclerView.setLayoutManager(layoutManager);
//
//
////        myRecyclerViewAdapter = new MypageRecyclerViewAdapter(getActivity(), myCompo);
//        myRecyclerView.setAdapter(myRecyclerViewAdapter);

        return mypage_activity;
    }

    public void logout() {
        Toast.makeText(context, "로그아웃 완료", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), Login.class);
        Login.sid = null;
        startActivity(intent);
    }

    @Override
    public void processFinish(String output) {

//        JSONObject jObject = null;
//        try {
//            jObject = new JSONObject(output);
//            String code = jObject.getString("code");
//
//            switch (code) {
//                case "login":
//                    //loginProcess(jObject.getString("data"));
//                    break;
//                case "loadBook":
//                   // loadBookProcess(jObject.getString("data"));
//                    break;
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
}
