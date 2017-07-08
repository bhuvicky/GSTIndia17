package com.bhuvanesh.gstindia;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class GSTApplication extends Application {

    private static GSTApplication mInstance;
    private static FirebaseAnalytics mAnalyticsInstance;

    //onCreate will call only one time
    @Override

    public void onCreate() {
        super.onCreate();
        mInstance = this;

        if (BuildConfig.REPORT_CRASHES)
            Fabric.with(this, new Crashlytics());

        mAnalyticsInstance = FirebaseAnalytics.getInstance(this);
    }

    public static synchronized GSTApplication getInstance() {
        return mInstance;
    }

    public static synchronized FirebaseAnalytics getAnalyticsInstance() {
        mAnalyticsInstance.setAnalyticsCollectionEnabled(true);
        return mAnalyticsInstance;
    }

}
