package com.mobitant.firebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mypage extends Fragment implements ServerResponse {

    TextView Myphone;
    TextView Myname;
    TextView Myid;
    private Context context;
    private LinearLayoutManager llm;
    private LinearLayoutManager layoutManager;
    Button logoutBtn;
    Activity root = getActivity();
    List<MyCompo> myCompo = new ArrayList<>();
    public static RecyclerView myRecyclerView;
    public MypageRecyclerViewAdapter myRecyclerViewAdapter;
    private Button myBuyBtn;
    private Button mySellBtn;
    Mypage mypage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mypage_activity = inflater.inflate(R.layout.fragment_my, container, false);
        mypage = this;
        Myphone = mypage_activity.findViewById(R.id.text_myPhone);
        Myname = mypage_activity.findViewById(R.id.text_myNick);
        Myid = mypage_activity.findViewById(R.id.text_myID);
        Myid.setText(Login.sid);
        Myname.setText(Login.snick);
        Myphone.setText(Login.sphone);
        context = container.getContext();
        myBuyBtn = mypage_activity.findViewById(R.id.buy_btn);
        mySellBtn = mypage_activity.findViewById(R.id.sell_btn);
        logoutBtn = (Button) mypage_activity.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        myRecyclerView = mypage_activity.findViewById(R.id.myRecyclerView);

        llm = new LinearLayoutManager(getActivity());
        myRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), llm.getOrientation()));
        myRecyclerView.setLayoutManager(llm);
//


        myBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("my_id", Login.sid);
                new Server().onDb("http://54.180.107.154:4000/mybuy", parameter, mypage);
            }
        });


        mySellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("my_id", Login.sid);
                new Server().onDb("http://54.180.107.154:4000/mysale", parameter, mypage);
            }
        });

        return mypage_activity;
    }

    public void logout() {
        Toast.makeText(context, "로그아웃 완료", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), Login.class);
        Login.sid = null;
        startActivity(intent);
    }


    private void myBuy(String s) {
        try {
            myCompo.clear();
            JSONArray jsonArray = new JSONArray(s);
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i < jsonArray.length(); i++)
                myCompo.add(new MyCompo(jsonArray.getJSONObject(i).getString("jimage"),
                        jsonArray.getJSONObject(i).getString("jtitle"),
                        jsonArray.getJSONObject(i).getString("jprice"),
                        jsonArray.getJSONObject(i).getString("jphone")));
            myRecyclerViewAdapter = new MypageRecyclerViewAdapter(getActivity(),myCompo);
            myRecyclerView.setAdapter(myRecyclerViewAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void mySale(String s) {

        try {
            myCompo.clear();
            JSONArray jsonArray = new JSONArray(s);
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i < jsonArray.length(); i++)
                myCompo.add(new MyCompo(jsonArray.getJSONObject(i).getString("image_url"),
                        jsonArray.getJSONObject(i).getString("title"),
                        jsonArray.getJSONObject(i).getString("price"),
                        jsonArray.getJSONObject(i).getString("authors")));
            myRecyclerViewAdapter = new MypageRecyclerViewAdapter(getActivity(),myCompo);
            myRecyclerView.setAdapter(myRecyclerViewAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void processFinish(String output) {

        JSONObject jObject = null;
        try {
            jObject = new JSONObject(output);
            String code = jObject.getString("code");

            switch (code) {
                case "mybuy":
                    myBuy(jObject.getString("data"));
                    break;
                case "mysale":
                    mySale(jObject.getString("data"));
                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}