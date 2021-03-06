package com.bhuvanesh.gstindia.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.GSTApplication;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.utils.GSTPreference;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.google.gson.Gson;

import static android.R.attr.animation;
import static android.R.attr.manageSpaceActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!TextUtils.isEmpty(GSTPreference.getInstance().getLanguageCode()))
            GSTApplication.getInstance().updateLang(GSTPreference.getInstance().getLanguageCode());

        setContentView(R.layout.activity_splash_screen);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "earth.ttf");

        final float screen_width_half = metrics.widthPixels / 2;

        //final float distance = screen_width_half -;
        TextView oneTextView = (TextView) findViewById(R.id.textview_market);
        oneTextView.setTypeface(custom_font);
        final float distance = screen_width_half / 2;

        TextView oneTaxTextView = (TextView) findViewById(R.id.textview_one_tax);
        oneTaxTextView.setTypeface(custom_font);

        Animation animation = new TranslateAnimation(0, distance, 0, 0);
        animation.setDuration(2000);
        animation.setFillAfter(true);

        Animation animation1 = new TranslateAnimation(metrics.widthPixels, -distance,0, 0);
        animation1.setDuration(2000);
        animation1.setFillAfter(true);

        Animation animation2 = new AlphaAnimation(0.2f, 1f);
        animation2.setDuration(2000);
        animation2.setFillAfter(true);

        ImageView splashImageView = (ImageView) findViewById(R.id.image_view_slash);
        splashImageView.startAnimation(animation2);

        oneTextView.startAnimation(animation);
        oneTaxTextView.startAnimation(animation1);

        int secdelay = 3;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, GstActivity.class);
                startActivity(i);
                finish();
            }

        }, secdelay * 1000);
    }
}
