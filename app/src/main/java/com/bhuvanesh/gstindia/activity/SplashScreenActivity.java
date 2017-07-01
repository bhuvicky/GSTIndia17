package com.bhuvanesh.gstindia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        GstLoggerUtil.println("log this is a launcher activity");
    }
}
