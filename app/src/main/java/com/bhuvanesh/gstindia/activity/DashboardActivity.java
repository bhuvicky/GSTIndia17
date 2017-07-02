package com.bhuvanesh.gstindia.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.fragment.ProductListFragment;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setActionBar(R.id.toolbar_main);
        replace(R.id.flayout_container, ProductListFragment.newInstance("zero"));
    }
}
