package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.adapter.LifeAfterJuly1Adapter;
import com.bhuvanesh.gstindia.model.LifeAfterJuly1;
import com.bhuvanesh.gstindia.utils.FileUtil;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Bhuvanesh on 7/3/2017.
 */

public class LifeAfterJuly1Fragment extends BaseFragment {

    public static LifeAfterJuly1Fragment newInstance() {
        return new LifeAfterJuly1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_feed, container, false);

        RecyclerView recyclerViewAfterJuly1 = view.findViewById(R.id.recyclerview_life_after_july1);
        recyclerViewAfterJuly1.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<LifeAfterJuly1> list = FileUtil.getFromAssetsFolder("life_after_july1", null,
                new TypeToken<List<LifeAfterJuly1>> () {}.getType());

        LifeAfterJuly1Adapter adapter = new LifeAfterJuly1Adapter();
        if (list != null)
            adapter.setData(list);
        recyclerViewAfterJuly1.setAdapter(adapter);

        return view;
    }
}
