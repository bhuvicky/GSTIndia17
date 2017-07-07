package com.bhuvanesh.gstindia.cheapercostlierproduct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.cheapercostlierproduct.adapter.TaxComparisonViewPagerAdapter;

/**
 * Created by Bhuvanesh on 7/5/2017.
 */

public class TaxComparisonViewPagerFragment extends BaseFragment {

    public static TaxComparisonViewPagerFragment newInstance() {
        return new TaxComparisonViewPagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tax_comparison_view_pager, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tablayout_cheaper_costlier);
        ViewPager viewPager = view.findViewById(R.id.viewpager_cheaper_costlier_item);
        tabLayout.setupWithViewPager(viewPager);

        TaxComparisonViewPagerAdapter adapter = new TaxComparisonViewPagerAdapter(getActivity(),
                getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        return view;
    }
}
