package com.mobitant.firebook;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mobitant.firebook.Sale.recyclerView;

public class MainActivity extends AppCompatActivity implements ServerResponse {
    String barcode_result;
    List<BookCompo> compo = new ArrayList<>();
    public SaleRecyclerViewAdapter recyclerViewAdapter;
    public static FragmentManager fragmentManager;
    public static FragmentTransaction transaction;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        // QR코드/ 바코드를 스캔한 결과
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        barcode_result = result.getContents();
        Log.i("test", barcode_result);
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("key", "7EF192D7C7D2600470252980FD233757C69B747264C145A0255E78ADB51650F9");
        parameter.put("query", barcode_result);
        parameter.put("queryType", "isbn");
        parameter.put("output", "json");
        new Server().onDb("https://book.interpark.com/api/search.api", parameter, this);
    }

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
//    public FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
//    private Recommend menu1Fragment = new Recommend();
//    private Sale menu2Fragment = new Sale();
    // private Menu3Fragment menu3Fragment = new Menu3Fragment();
    // private Menu4Fragment menu4Fragment = new Menu4Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar abr = getSupportActionBar();
        abr.hide(); // Hide Action bar

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.frame_layout, menu1Fragment).commitAllowingStateLoss();
        fragmentManager = getSupportFragmentManager();
         transaction = fragmentManager.beginTransaction();

         transaction.replace(R.id.frame_layout,new Recommend());
         transaction.addToBackStack(null);
         transaction.commit();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.frame_layout, new Recommend());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    }
                    case R.id.navigation_menu2: {
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.frame_layout, new Sale());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    }
                    case R.id.navigation_menu3: {
                       // transaction.replace(R.id.frame_layout, menu3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu4: {
                        // transaction.replace(R.id.frame_layout, menu4Fragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });
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
                        barcode_result
                ));
            }
            Log.i("ser", arrItem.getJSONObject(0).getString("title"));
            recyclerViewAdapter = new SaleRecyclerViewAdapter(this, compo);
            recyclerView.setAdapter(recyclerViewAdapter);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }
}
