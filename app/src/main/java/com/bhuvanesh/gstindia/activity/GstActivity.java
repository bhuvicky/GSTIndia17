package com.bhuvanesh.gstindia.activity;

import android.os.Bundle;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.fragment.DashboardFragment;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;

public class GstActivity extends BaseActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);
        setActionBar(R.id.toolbar_main);
        replace(R.id.fragment_host, DashboardFragment.newInstance());
    }
    public InterstitialAd getmInterstitialAd(){return mInterstitialAd;}
}
