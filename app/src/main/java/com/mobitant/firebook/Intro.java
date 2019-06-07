package com.mobitant.firebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class Intro extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar abr = getSupportActionBar();
        abr.hide(); // Hide Action bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(Intro.this,Login.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                progressBar.setVisibility(View.GONE);
            }
        }, 4500);


    }
}

