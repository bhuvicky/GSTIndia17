package com.bhuvanesh.gstindia;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class BaseFragment extends Fragment {
    private InterstitialAd interstitialAd;
    private AdRequest mAdRequestInstance;
    private InterstitialAd mInterstitialAdInstance;

    public AdRequest getAdRequest() {
        if (mAdRequestInstance == null) {
            if (BuildConfig.REPORT_TEST_AD) {
                String advertisingId = "D7485D34081F44384E25018239E6810B";
                GstLoggerUtil.println("log advertising id = " + advertisingId);
                mAdRequestInstance = new AdRequest.Builder()
                        .addTestDevice(advertisingId)
                        .build();


            } else {
                mAdRequestInstance = new AdRequest.Builder().build();
            }
        }

        return mAdRequestInstance;
    }

    public InterstitialAd getInterstitialAdInstance(Context context) {
        if (mInterstitialAdInstance == null) {
            mInterstitialAdInstance = new InterstitialAd(context);
            mInterstitialAdInstance.setAdUnitId("ca-app-pub-2950380730218514/7106047184");
        }
        return mInterstitialAdInstance;
    }

    public void replace(int containerId, BaseFragment fragment) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).replace(containerId, fragment);
        }
    }

    public void pop() {
        if (getActivity() != null) {

            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();

        }
    }

    public void popAllFragmentsUpto(int index) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).popAllFragmentsUpto(index);
        }
    }

    protected void onBackPress() {
        pop();
    }

    protected void logDashboardEvent(String content, String contentType, String propertyName, String propertyValue, String eventName) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CONTENT, content);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        GSTApplication.getAnalyticsInstance().setUserProperty(propertyName, propertyName);
        GSTApplication.getAnalyticsInstance().logEvent(eventName, bundle);
    }
}
