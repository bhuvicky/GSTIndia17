package com.bhuvanesh.gstindia;

import android.app.Application;
import android.provider.Settings;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.bhuvanesh.gstindia.utils.LRUBitmapCache;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class GSTApplication extends Application {

    private static GSTApplication mInstance;
    private static FirebaseAnalytics mAnalyticsInstance;
    private AdRequest mAdRequestInstance;
    private InterstitialAd mInterstitialAdInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private LRUBitmapCache mLruBitmapCache;


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

    /*public AdRequest getAdRequest() {
        if (mAdRequestInstance == null) {
            if (BuildConfig.REPORT_TEST_AD) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(getInstance());
                            if (info != null) {
                                String advertisingId = info.getId();
                                GstLoggerUtil.println("log advertising id = " + advertisingId);
                                mAdRequestInstance =  new AdRequest.Builder()
                                        .addTestDevice(advertisingId)
                                        .build();
                            } else {
                                System.out.println("log info null");
                            }

                        } catch (IOException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            else {
                mAdRequestInstance = new AdRequest.Builder().build();
            }
        }
        return mAdRequestInstance;
    }
*/

    public AdRequest getAdRequest() {
        if (mAnalyticsInstance == null) {
            System.out.println("log ad ins null");
            if (BuildConfig.REPORT_TEST_AD) {
                System.out.println("log test device debug mode");
                mAdRequestInstance = new AdRequest.Builder()
                        .addTestDevice("33BE2250B43518CCDA7DE426D04EE232").build();
            }
        } else {
            System.out.println("log ad ins not null");
        }
        return mAdRequestInstance;
    }
    public InterstitialAd getInterstitialAdInstance() {
        if (mInterstitialAdInstance == null) {
            mInterstitialAdInstance = new InterstitialAd(this);
            mInterstitialAdInstance.setAdUnitId("ca-app-pub-2950380730218514/7106047184");
        }
        return mInterstitialAdInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            getLruBitmapCache();
            mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
        }
        return this.mImageLoader;
    }

    public LRUBitmapCache getLruBitmapCache() {
        if (mLruBitmapCache == null)
            mLruBitmapCache = new LRUBitmapCache();
        return this.mLruBitmapCache;
    }
}
