package com.bhuvanesh.gstindia.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.TransformationMethod;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;

import static android.R.attr.animation;
import static android.R.attr.manageSpaceActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        GstLoggerUtil.println("log this is a launcher activity");
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "earth.ttf");

        final float screen_width_half = metrics.widthPixels/2;

        //final float distance = screen_width_half -;
        TextView oneTextView= (TextView) findViewById(R.id.textview_market);
        oneTextView.setTypeface(custom_font);
        final float distance = screen_width_half/2;

        TextView oneTaxTextView= (TextView) findViewById(R.id.textview_one_tax);
        oneTaxTextView.setTypeface(custom_font);
        Animation animation = new TranslateAnimation(0, distance,0, 0);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        Animation animation1 = new TranslateAnimation(metrics.widthPixels, -299,0, 0);
        animation1.setDuration(2000);
        animation1.setFillAfter(true);

        oneTextView.startAnimation(animation);
        oneTaxTextView.startAnimation(animation1);
         int secdelay=4;
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
