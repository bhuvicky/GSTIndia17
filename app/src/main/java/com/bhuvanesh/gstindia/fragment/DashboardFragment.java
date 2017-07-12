package com.bhuvanesh.gstindia.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.bhuvanesh.gstindia.cheapercostlierproduct.fragment.TaxComparisonViewPagerFragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
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
    private InterstitialAd interstitialAd;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ((BaseActivity) getActivity()).setBackEnabled(false);
        (getActivity()).setTitle(getString(R.string.dash_titlte));

        TextView textViewLifeAfterJuly1 = (TextView) view.findViewById(R.id.textview_rules);
        textViewLifeAfterJuly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(R.id.fragment_host, LifeAfterJuly1Fragment.newInstance());
            }
        });
        DisplayMetrics metrics = new DisplayMetrics();
        (getActivity()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        interstitialAd = getInterstitialAdInstance(getContext());
        pieChart = (PieChart) view.findViewById(R.id.piechart);
        pieChart.setDescription("");
        pieChart.setUsePercentValues(true);
        pieChart.setMinimumHeight(metrics.heightPixels / 2);
        pieChart.setCenterText("GST");
        pieChart.setCenterTextSize(24f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setHoleRadius(40);
        /*pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);*/
        createDataSet();

        AdView adView = (AdView) view.findViewById(R.id.adview);
        AdRequest adRequest = getAdRequest();
        if (adRequest != null) {
            adView.loadAd(adRequest);
            interstitialAd.loadAd(adRequest);
        }
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_dashboard);
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "click the piechart for items under respctive tax%", Snackbar.LENGTH_LONG);

        snackbar.show();

        TextView exploreBillsTextView = (TextView) view.findViewById(R.id.textview_explore_bill);
        exploreBillsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logDashboardEvent("Dashboard Screen Content", "Explore Bill", "favourite_app_feature",
                        "Explore Bill", FirebaseAnalytics.Event.SELECT_CONTENT);
                replace(R.id.fragment_host, BillFeedFragment.newInstance());
            }
        });
        TextView calculatorTextView = (TextView) view.findViewById(R.id.textview_calculator);
        calculatorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdRequest adRequest = new AdRequest.Builder().addTestDevice("33BE2250B43518CCDA7DE426D04EE232").build();
                System.out.println("log is test device = " + adRequest.isTestDevice(getActivity()));

                logDashboardEvent("Dashboard Screen Content", "GST Calculator", "favourite_app_feature",
                        "GST Calculator", FirebaseAnalytics.Event.SELECT_CONTENT);
                replace(R.id.fragment_host, GSTCalcFragment.newInstance());
            }
        });
        TextView faqTextview = (TextView) view.findViewById(R.id.textview_faq);
        faqTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(R.id.fragment_host, FAQFragment.newInstance());

            }
        });

        TextView textViewItemTax = (TextView) view.findViewById(R.id.textview_cheaper_costlier);
        textViewItemTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(R.id.fragment_host, TaxComparisonViewPagerFragment.newInstance());

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

        xVals.add(getString(R.string.lbl_gst_slab_zero));
        xVals.add(getString(R.string.lbl_gst_slab_five));
        xVals.add(getString(R.string.lbl_gst_slab_twelve));
        xVals.add(getString(R.string.lbl_gst_slab_eighteen));
        xVals.add(getString(R.string.lbl_gst_slab_twentyeight));

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_dash_board, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        ShareActionProvider myShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        ;
        // Create the share Intent
        String shareText = "Install Life After GSt app to get complete information about GST \n http://play.google.com/store/apps/details?id=com.bhuvanesh.gstindia \n #SteveNodeCreation";
        Intent shareIntent = new Intent(Intent.ACTION_SEND)
                .putExtra(android.content.Intent.EXTRA_SUBJECT, "Life After GST")
                .putExtra(android.content.Intent.EXTRA_TEXT, shareText)
                .setType("text/plain");
        if (myShareActionProvider != null)
            myShareActionProvider.setShareIntent(shareIntent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_send_feedback:
                composeEmail(new String[]{"gstindia017@gmail.com"}, "Life After GST Feedback");
                return true;
            case R.id.menu_langu:
                replace(R.id.fragment_host, LanguageListFragment.newInstance());
                return true;
        }
        return false;
    }

    @Override
    protected void onBackPress() {
        super.onBackPress();
        if (interstitialAd.isLoaded()) interstitialAd.show();
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
