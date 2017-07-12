package com.bhuvanesh.gstindia.cheapercostlierproduct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.bhuvanesh.gstindia.cheapercostlierproduct.adapter.TaxComparisonViewPagerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Bhuvanesh on 7/5/2017.
 */

public class TaxComparisonViewPagerFragment extends BaseFragment {


    private InterstitialAd mInterstitialAd;

    public static TaxComparisonViewPagerFragment newInstance() {
        return new TaxComparisonViewPagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tax_comparison_view_pager, container, false);
        setHasOptionsMenu(true);
        ((BaseActivity) getActivity()).setBackEnabled(true);
        (getActivity()).setTitle(getResources().getString(R.string.costlier_cheaper));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout_cheaper_costlier);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_cheaper_costlier_item);
        tabLayout.setupWithViewPager(viewPager);
        mInterstitialAd = getInterstitialAdInstance(getContext());
        mInterstitialAd.loadAd(getAdRequest());

        TaxComparisonViewPagerAdapter adapter = new TaxComparisonViewPagerAdapter(getActivity(),
                getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        return view;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                pop();
                if (mInterstitialAd.isLoaded()) mInterstitialAd.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onBackPress() {
        super.onBackPress();
        if (mInterstitialAd.isLoaded()) mInterstitialAd.show();

    }

}
