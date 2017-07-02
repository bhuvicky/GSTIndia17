package com.bhuvanesh.gstindia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.R;

public class GstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);

        setActionBar(R.id.toolbar_main);

        replace(R.id.fragment_host, DashboardFragment.newInstance());
    }
}
