package com.bhuvanesh.gstindia.cheapercostlierproduct.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.cheapercostlierproduct.fragment.CheaperItemsAfterGstFragment;
import com.bhuvanesh.gstindia.cheapercostlierproduct.fragment.CostlierItemsAfterGstFragment;
import com.bhuvanesh.gstindia.cheapercostlierproduct.fragment.UnchangedTaxAfterGstFragment;

/**
 * Created by Bhuvanesh on 7/5/2017.
 */

public class TaxComparisonViewPagerAdapter extends FragmentStatePagerAdapter {

    public static final byte TAB_CHEAPER = 0;
    public static final byte TAB_COSTLIER = TAB_CHEAPER + 1;
    public static final byte TAB_UNCHANGED = TAB_COSTLIER;

    private static String[] TAB_TITLE;
    private SparseArray<BaseFragment> fragArray = new SparseArray<>();

    public TaxComparisonViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        TAB_TITLE = context.getResources().getStringArray(R.array.array_cheaper_costlier);
    }


    @Override
    public BaseFragment getItem(int position) {
        if (fragArray.get(position) == null) {
            switch (position) {
                case TAB_CHEAPER:
                    fragArray.put(position, CheaperItemsAfterGstFragment.newInstance(TAB_CHEAPER));
                    break;
                case TAB_COSTLIER:
                    fragArray.put(position, CostlierItemsAfterGstFragment.newInstance(TAB_COSTLIER));
                    break;
                default:
                    fragArray.put(position, UnchangedTaxAfterGstFragment.newInstance(TAB_UNCHANGED));
            }
        }
        return fragArray.get(position);
    }

    @Override
    public int getCount() {
        return TAB_TITLE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLE[position];
    }
}
