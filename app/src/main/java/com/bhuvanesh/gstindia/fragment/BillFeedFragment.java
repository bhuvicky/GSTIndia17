package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;

/**
 * Created by Karthikeyan on 03-07-2017.
 */

public class BillFeedFragment extends BaseFragment {
    private RecyclerView billRecyclerView;
    private FloatingActionButton addFab;
    public static BillFeedFragment newInstance(){
        return new BillFeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill_feed,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
