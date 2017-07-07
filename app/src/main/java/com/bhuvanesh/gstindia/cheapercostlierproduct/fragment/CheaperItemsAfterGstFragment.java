package com.bhuvanesh.gstindia.cheapercostlierproduct.fragment;

/**
 * Created by Bhuvanesh on 7/7/2017.
 */

public class CheaperItemsAfterGstFragment extends BaseTaxComparisonFragment {

    public static CheaperItemsAfterGstFragment newInstance(int type) {
        CheaperItemsAfterGstFragment fragment = new CheaperItemsAfterGstFragment();
        fragment.mItemCategoryType = type;
        return fragment;
    }

}
