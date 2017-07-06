package com.bhuvanesh.gstindia.cheapercostlierproduct.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.cheapercostlierproduct.fragment.CostlierItemsAfterGstFragment;

/**
 * Created by Bhuvanesh on 7/5/2017.
 */

public class CheaperCostlierPagerAdapter extends FragmentStatePagerAdapter {

    private static final byte TAB_FEED = 0;
    private static final byte TAB_MY_TALENT = TAB_FEED + 1;

    private static String[] TAB_TITLE;

    public CheaperCostlierPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
//        TAB_TITLE = context.getResources().getStringArray(R.array.lbl_story_tab_title);
    }

    @Override
    public BaseFragment getItem(int position) {
        switch (position) {
            case TAB_FEED:
                return CostlierItemsAfterGstFragment.newInstance();
            default:
                return CostlierItemsAfterGstFragment.newInstance();
        }
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
