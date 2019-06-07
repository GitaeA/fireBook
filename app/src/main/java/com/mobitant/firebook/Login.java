package com.mobitant.firebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

public class Login extends AppCompatActivity implements ServerResponse{
    Login main_this = this;
    EditText editText;
    EditText editText2;
    Button button;
    Button button2;
   static String sid;
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
        new Server().onDb("http://54.180.107.154:4000/logintest", user,  main_this);
        if(spw.equals(0)){

        }

    }

    @Override
    public void processFinish(String output) {

        try {
            JSONArray jsonArray= new JSONArray(output);
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
}
