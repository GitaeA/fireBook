package com.mobitant.firebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Join extends AppCompatActivity implements ServerResponse {

    Join main_this = this;
    EditText id;
    EditText pw;
    EditText pw2;
    EditText phone; // 폰번호
    EditText nick; // 닉네임 추가
    String sid;
    String spw;
    String spw2;
    String sphone;
    String snick;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar abr = getSupportActionBar();
        abr.hide(); // Hide Action bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        id = (EditText)findViewById(R.id.text_id);
        pw = (EditText)findViewById(R.id.text_pw);
        pw2=(EditText)findViewById(R.id.text_pw2);
        phone=(EditText)findViewById(R.id.text_phone);
        nick=(EditText)findViewById(R.id.text_nick);
        button = (Button)findViewById(R.id.Join);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_join();

            }
        });



    }
    public void bt_join() {
        sid = id.getText().toString();
        spw = pw.getText().toString();
        spw2 = pw2.getText().toString();
        sphone = phone.getText().toString();
        snick = nick.getText().toString();
        HashMap<String, String> user = new HashMap<>();
        user.put("id", sid);
        user.put("pw", spw);
        user.put("pw2", spw2);
        user.put("nick", snick);
        user.put("phone", sphone);
        // 닉네임 폰번호 추가

       if(sid==null) {
           Toast.makeText(getApplicationContext(), "아이디를 입력하세요!", Toast.LENGTH_LONG).show();
    }
       else{
           if (spw.equals(spw2) && !(spw.equals(""))) {
               new Server().onDb("http://54.180.107.154:4000/jointest", user, main_this); //서버로 보내기
               Toast.makeText(getApplicationContext(), "회원가입완료!", Toast.LENGTH_LONG).show();
               Intent intent = new Intent(Join.this, Login.class);
               startActivity(intent);
           } else {
               Toast.makeText(getApplicationContext(), "아이디랑 비밀번호를 확인하세요.", Toast.LENGTH_LONG).show();
           }
       }
    }

    @Override
    public void processFinish(String output) {

    }
}
