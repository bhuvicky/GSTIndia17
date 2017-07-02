package com.bhuvanesh.gstindia.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.fragment.ProductListFragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class DashboardFragment extends BaseFragment {

    private PieChart pieChart;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        pieChart = view.findViewById(R.id.piechart);
        pieChart.setDescription("");
        pieChart.setUsePercentValues(true);
        pieChart.setMinimumHeight(600);
        pieChart.setCenterText("GST");
        pieChart.setCenterTextSize(24f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setHoleRadius(40);
        /*pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);*/
        createDataSet();

        return view;
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

        xVals.add("0% Slab");
        xVals.add("5% Slab");
        xVals.add("12% Slab");
        xVals.add("18% Slab");
        xVals.add("28% Slab");

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());


        /*dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);*/

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        /*dataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        dataSet.setColors(ColorTemplate.PASTEL_COLORS);*/

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (e == null)
                    return;
                switch (e.getXIndex()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        replace(R.id.fragment_host, ProductListFragment.newInstance(""));
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
}
