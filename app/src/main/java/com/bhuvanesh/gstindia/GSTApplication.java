package com.bhuvanesh.gstindia;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bhuvanesh.gstindia.utils.LRUBitmapCache;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class GSTApplication extends Application {

    private static GSTApplication mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private LRUBitmapCache mLruBitmapCache;


    //onCreate will call only one time
    @Override

    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized GSTApplication getInstance() {
        return mInstance;
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
