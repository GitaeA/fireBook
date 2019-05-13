package com.mobitant.firebook;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sale extends Fragment implements ServerResponse {
    public static RecyclerView recyclerView;
    private EditText search_text;
    private ImageButton barcode_search;
    private LinearLayoutManager llm;
    public SaleRecyclerViewAdapter recyclerViewAdapter;
    private Button search_button;
    private String search;
    Sale thiss = this;
    List<BookCompo> compo = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sale, container, false);
        recyclerView = root.findViewById(R.id.recycler);
        search_text = root.findViewById(R.id.search_text);
        barcode_search = root.findViewById(R.id.barcode);  //철녕이형
        search_button = root.findViewById(R.id.search_button);

        llm = new LinearLayoutManager(getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), llm.getOrientation()));
        recyclerView.setLayoutManager(llm);

        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                recyclerView.clearDisappearingChildren();
            }

            //
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                search = s.toString();
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("key", "7EF192D7C7D2600470252980FD233757C69B747264C145A0255E78ADB51650F9");
                parameter.put("query", search);
                parameter.put("output", "json");
                new Server().onDb("https://book.interpark.com/api/search.api", parameter, thiss);
            }
        });

        barcode_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new IntentIntegrator(getActivity()).initiateScan();
            }
        });


        return root;

    }

    @Override
    public void processFinish(String output) {
        compo.clear();
        try {
            JSONObject objChannel = new JSONObject(output);
            JSONArray arrItem = objChannel.getJSONArray("item");

            for (int i = 0; i < arrItem.length(); i++) {
                String front;
                front = arrItem.getJSONObject(i).getString("coverLargeUrl").substring(0, 3) + "ps";
                int len = arrItem.getJSONObject(i).getString("coverLargeUrl").length();
                String back = arrItem.getJSONObject(i).getString("coverLargeUrl").substring(4, len);
                compo.add(new BookCompo((front + back),
                        arrItem.getJSONObject(i).getString("title"),
                        arrItem.getJSONObject(i).getString("priceStandard"),
                        arrItem.getJSONObject(i).getString("publisher"),
                        arrItem.getJSONObject(i).getString("author"),
                        arrItem.getJSONObject(i).getString("isbn")
                ));
            }

            recyclerViewAdapter = new SaleRecyclerViewAdapter(getActivity(), compo);
            recyclerView.setAdapter(recyclerViewAdapter);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }
}


