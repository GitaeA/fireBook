package com.mobitant.firebook;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Login extends AppCompatActivity implements ServerResponse{
    Login main_this = this;
    EditText editText;
    EditText editText2;
    Button button;
    Button button2;
   static String sid;
    static String sphone;
    static String snick;
    static String recommendTitle;
    static int b;
    Recommend recommend = new Recommend();

    public static ArrayList<String> getTitleList() {
        return titleList;
    }

    static ArrayList<String>  titleList = new ArrayList();
    String spw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar abr = getSupportActionBar();
        abr.hide(); // Hide Action bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.loginBtn);
        button2=(Button)findViewById(R.id.joinBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_login();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Join.class);
                startActivity(intent);
            }
        });

    }

    public void bt_login(){
        sid = editText.getText().toString();
        spw = editText2.getText().toString();
        HashMap<String,String> user = new HashMap<>();
        user.put("id",sid);

        new Server().onDb("http://54.180.107.154:4000/loadReadBook", user, main_this);

        new Server().onDb("http://54.180.107.154:4000/logintest", user,  main_this);
        if(spw.equals(0)){

        }

    }

    private void loginProcess(String s){
        try {
            JSONArray jsonArray= new JSONArray(s);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            sphone = jsonObject.optString("phone");
            //sphone = jsonArray.getJSONObject(0).getString("phone");
            //sphone = String.valueOf(jsonArray.getJSONObject(0).get("phone"));
            snick = jsonArray.getJSONObject(0).getString("name");
            //snick = String.valueOf(jsonArray.getJSONObject(0).get("name"));

            Log.d("1",snick);
            Log.d("1",sphone);
            if(jsonArray.getJSONObject(0).getString("pw").equals(spw)){
                //아이디에 맞는 key pw값을 받와와 로그인 값 비교
                Toast.makeText(getApplicationContext(),"로그인 완료",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 확인하세요.",Toast.LENGTH_LONG).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadBookProcess(String s) {

        try {
            JSONArray jsonArray = new JSONArray(s);

            int a=0;

            for (int i = 0; i < jsonArray.length(); i++){
                titleList.add(jsonArray.getJSONObject(i).getString("title"));
                a++;
            }


            Random random = new Random();

            b = random.nextInt(a);

            recommendTitle = titleList.get(b);

            Messenger messenger =recommend.getMessenger();
            Message msg = Message.obtain();
            msg.obj = titleList; // 사진 주소 보내기
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }



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
                case "login":
                    loginProcess(jObject.getString("data"));
                    break;
                case "loadBook":
                    loadBookProcess(jObject.getString("data"));
                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}


