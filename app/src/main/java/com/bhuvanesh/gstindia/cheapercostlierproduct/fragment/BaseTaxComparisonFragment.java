package com.bhuvanesh.gstindia.cheapercostlierproduct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.GSTApplication;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.cheapercostlierproduct.adapter.CategoryItemsListAdapter;
import com.bhuvanesh.gstindia.cheapercostlierproduct.adapter.TaxComparisonViewPagerAdapter;
import com.bhuvanesh.gstindia.model.CategoryWiseItem;
import com.bhuvanesh.gstindia.utils.FileUtil;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Bhuvanesh on 7/7/2017.
 */

public class BaseTaxComparisonFragment extends BaseFragment {

    protected int mItemCategoryType;

    public static BaseTaxComparisonFragment newInstance(int type) {
        BaseTaxComparisonFragment fragment = new BaseTaxComparisonFragment();
        fragment.mItemCategoryType = type;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_tax_comparison, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        List<CategoryWiseItem> list;
        switch (mItemCategoryType) {
            case TaxComparisonViewPagerAdapter.TAB_CHEAPER:
                list = FileUtil.getFromAssetsFolder("cheaper.json",null, new TypeToken<List<CategoryWiseItem>>() {}.getType());
                break;
            case TaxComparisonViewPagerAdapter.TAB_COSTLIER:
                list = FileUtil.getFromAssetsFolder("costlier.json", null, new TypeToken<List<CategoryWiseItem>>() {}.getType());
                break;
            default:
                list = FileUtil.getFromAssetsFolder("unchanged.json", null, new TypeToken<List<CategoryWiseItem>>() {}.getType());
                break;
        }

        RecyclerView recyclerViewItemCategory = view.findViewById(R.id.recyclerview_item_category);
        recyclerViewItemCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        GstLoggerUtil.debug("hh",list.get(0).categoryName);
        CategoryItemsListAdapter adapter = new CategoryItemsListAdapter(getActivity());

        adapter.setData(list);


        recyclerViewItemCategory.setAdapter(adapter);
    }
}
