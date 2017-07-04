package com.bhuvanesh.gstindia.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        GstLoggerUtil.println("log this is a launcher activity");
        int secdelay=3;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreenActivity.this,GstActivity.class);
                startActivity(i);
                finish();
            }

        },secdelay*1000);
    }
}
