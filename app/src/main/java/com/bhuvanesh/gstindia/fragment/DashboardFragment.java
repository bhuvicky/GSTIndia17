package com.bhuvanesh.gstindia.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.GSTApplication;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.cheapercostlierproduct.fragment.TaxComparisonViewPagerFragment;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class DashboardFragment extends BaseFragment {

    private PieChart pieChart;
    private TextView exploreBillsTextView;
    private TextView calculatorTextView;
    private TextView faqTextview;
    private InterstitialAd interstitialAd;
    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ((BaseActivity) getActivity()).setBackEnabled(false);
        ((BaseActivity) getActivity()).setTitle("GST India 2017");
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
         interstitialAd=new InterstitialAd(getContext());

        TextView textViewLifeAfterJuly1 = view.findViewById(R.id.textview_rules);
        textViewLifeAfterJuly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(R.id.fragment_host, LifeAfterJuly1Fragment.newInstance());
            }
        });
        pieChart = view.findViewById(R.id.piechart);
        pieChart.setDescription("");
        pieChart.setUsePercentValues(true);
        pieChart.setMinimumHeight(metrics.heightPixels/2);
        pieChart.setCenterText("GST");
        pieChart.setCenterTextSize(24f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setHoleRadius(40);
        /*pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);*/
        createDataSet();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdView adView=view.findViewById(R.id.adview);
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice("D7485D34081F44384E25018239E6810B")
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);
        interstitialAd.setAdUnitId("ca-app-pub-2950380730218514/7106047184");
        interstitialAd.loadAd(adRequest);
        RelativeLayout relativeLayout=view.findViewById(R.id.relative_layout_dashboard);
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "click pie chart to see items comes under respctive tax %", Snackbar.LENGTH_LONG);

        snackbar.show();

        exploreBillsTextView = view.findViewById(R.id.textview_explore_bill);
        exploreBillsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logDashboardEvent("Dashboard Screen Content", "Explore Bill", "favourite_app_feature",
                        "Explore Bill", FirebaseAnalytics.Event.SELECT_CONTENT);
                replace(R.id.fragment_host, BillFeedFragment.newInstance());
            }
        });
        calculatorTextView = view.findViewById(R.id.textview_calculator);
        calculatorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              AdRequest adRequest = new AdRequest.Builder().addTestDevice("33BE2250B43518CCDA7DE426D04EE232").build();
                System.out.println("log is test device = " +  adRequest.isTestDevice(getActivity()));

                logDashboardEvent("Dashboard Screen Content", "GST Calculator", "favourite_app_feature",
                        "GST Calculator", FirebaseAnalytics.Event.SELECT_CONTENT);
                replace(R.id.fragment_host, GSTCalcFragment.newInstance());
            }
        });
        faqTextview=view.findViewById(R.id.textview_faq);
        faqTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(R.id.fragment_host,FAQFragment.newInstance());

            }
        });

        TextView textViewItemTax = view.findViewById(R.id.textview_cheaper_costlier);
        textViewItemTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(R.id.fragment_host, TaxComparisonViewPagerFragment.newInstance());

            }
        });


        GSTApplication.getInstance().getInterstitialAdInstance().setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                GstLoggerUtil.println("log ad loaded");
                GSTApplication.getInstance().getInterstitialAdInstance().show();

            }
        });


    }

    private void createDataSet() {
        ArrayList<Entry> yvalues = new ArrayList<>();
        yvalues.add(new Entry(7f, 0));
        yvalues.add(new Entry(14f, 1));
        yvalues.add(new Entry(17f, 2));
        yvalues.add(new Entry(43f, 3));
        yvalues.add(new Entry(19f, 4));

        PieDataSet dataSet = new PieDataSet(yvalues, "");
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("0% Tax");
        xVals.add("5% Tax");
        xVals.add("12% Tax");
        xVals.add("18% Tax");
        xVals.add("28% Tax");

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (e == null)
                    return;
                switch (e.getXIndex()) {
                    case 0:
                        logDashboardEvent("Dashboard Screen Content", "0% Slab", "favourite_app_feature",
                                "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                        replace(R.id.fragment_host, ProductListFragment.newInstance("zero"));
                        break;
                    case 1:
                        logDashboardEvent("Dashboard Screen Content", "5% Slab", "favourite_app_feature",
                                "5% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                        replace(R.id.fragment_host, ProductListFragment.newInstance("five"));
                        break;
                    case 2:
                        logDashboardEvent("Dashboard Screen Content", "12% Slab", "favourite_app_feature",
                                "12% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                        replace(R.id.fragment_host, ProductListFragment.newInstance("twelve"));
                        break;
                    case 3:
                        logDashboardEvent("Dashboard Screen Content", "18% Slab", "favourite_app_feature",
                                "18% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                        replace(R.id.fragment_host, ProductListFragment.newInstance("eighteen"));
                        break;
                    case 4:
                        logDashboardEvent("Dashboard Screen Content", "28% Slab", "favourite_app_feature",
                                "28% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                        replace(R.id.fragment_host, ProductListFragment.newInstance("twentyeight"));
                        break;
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Legend l = pieChart.getLegend();
        l.setXEntrySpace(7);
        l.setYEntrySpace(8);
        l.setComputedLabels(xVals);
        l.setTextColor(Color.BLACK);
        l.setTextSize(12f);

        pieChart.animateXY(2500, 2500);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        // update pie chart
        pieChart.invalidate();
    }

    @Override
    protected void onBackPress() {
        super.onBackPress();
        if (interstitialAd.isLoaded())interstitialAd.show();
    }
}
