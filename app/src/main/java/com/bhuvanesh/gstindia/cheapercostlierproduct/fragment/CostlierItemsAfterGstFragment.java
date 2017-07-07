package com.bhuvanesh.gstindia.cheapercostlierproduct.fragment;

import com.bhuvanesh.gstindia.BaseFragment;

/**
 * Created by Bhuvanesh on 7/5/2017.
 */

public class CostlierItemsAfterGstFragment extends BaseTaxComparisonFragment {

    public static CostlierItemsAfterGstFragment newInstance(int type) {
        CostlierItemsAfterGstFragment fragment = new CostlierItemsAfterGstFragment();
        fragment.mItemCategoryType = type;
        return fragment;
    }

}
