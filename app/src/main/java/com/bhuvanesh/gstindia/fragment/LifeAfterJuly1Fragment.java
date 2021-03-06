package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.bhuvanesh.gstindia.adapter.LifeAfterJuly1Adapter;
import com.bhuvanesh.gstindia.model.LifeAfterJuly1;
import com.bhuvanesh.gstindia.utils.FileUtil;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhuvanesh on 7/3/2017.
 */

public class LifeAfterJuly1Fragment extends BaseFragment {


    private InterstitialAd mInterstitialAd;

    public static LifeAfterJuly1Fragment newInstance() {
        return new LifeAfterJuly1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life_after_july1, container, false);

        RecyclerView recyclerViewAfterJuly1 = view.findViewById(R.id.recyclerview_life_after_july1);
        recyclerViewAfterJuly1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mInterstitialAd = getInterstitialAdInstance(getContext());
        mInterstitialAd.loadAd(getAdRequest());

        String[] quesArray = getResources().getStringArray(R.array.array_after_july1_ques);
        String[] ansArray = getResources().getStringArray(R.array.array_after_july1_ans);
        List<LifeAfterJuly1> lifeAfterJuly1List = new ArrayList<>();

        for (int i = 0; i < quesArray.length; i++) {
            LifeAfterJuly1 item = new LifeAfterJuly1();
            item.title = quesArray[i];
            item.description = ansArray[i];
            lifeAfterJuly1List.add(item);
        }
        LifeAfterJuly1Adapter adapter = new LifeAfterJuly1Adapter();
        recyclerViewAfterJuly1.setAdapter(adapter);
        adapter.setData(lifeAfterJuly1List);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ((BaseActivity) getActivity()).setBackEnabled(true);
        (getActivity()).setTitle(getString(R.string.life_after_july_1));
        ((BaseActivity)getActivity()).setBackEnabled(true);
        ((BaseActivity)getActivity()).setTitle(getResources().getString(R.string.life_after_july_1)) ;
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
