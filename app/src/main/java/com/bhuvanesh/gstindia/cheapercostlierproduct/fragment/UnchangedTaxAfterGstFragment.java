package com.bhuvanesh.gstindia.cheapercostlierproduct.fragment;

/**
 * Created by Bhuvanesh on 7/7/2017.
 */

public class UnchangedTaxAfterGstFragment extends BaseTaxComparisonFragment {

    public static UnchangedTaxAfterGstFragment newInstance(int type) {
        UnchangedTaxAfterGstFragment fragment = new UnchangedTaxAfterGstFragment();
        fragment.mItemCategoryType = type;
        return fragment;
    }

}
