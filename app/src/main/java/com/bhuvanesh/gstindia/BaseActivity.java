package com.bhuvanesh.gstindia;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bhuvanesh.gstindia.utils.GstLoggerUtil;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    protected void setActionBar(int resId) {
        mToolbar = (Toolbar) findViewById(resId);
        setSupportActionBar(mToolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void setTitle(int resId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(resId);
        }
    }

    public void setBackEnabled(boolean enable) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
    }

    public void replace(int containerId, BaseFragment fragment) {
        replace(containerId, fragment, true);
    }

    public void replace(int containerId, DialogFragment fragment) {
        replace(containerId, fragment, true);
    }

    /**
     * Method for replacing fragment
     *
     * @param containerId Container of the fragment
     * @param fragment    The fragment to place in the container
     */
    public void replace(int containerId, BaseFragment fragment, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerId, fragment, Integer.toString(getSupportFragmentManager().getBackStackEntryCount()));
        if (addToBackStack) ft.addToBackStack(null);
        ft.commit();
        fm.executePendingTransactions();
        GstLoggerUtil.debug(BaseActivity.class.getSimpleName(), "BackStackEntryCount: " + fm.getBackStackEntryCount());
    }

    public void replace(int containerId, DialogFragment fragment, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerId, fragment, Integer.toString(getSupportFragmentManager().getBackStackEntryCount()));
        if (addToBackStack) ft.addToBackStack(null);
        ft.commit();
        fm.executePendingTransactions();
        GstLoggerUtil.debug(BaseActivity.class.getSimpleName(), "BackStackEntryCount: " + fm.getBackStackEntryCount());
    }

    public void pop() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
    }

    public void popAllFragmentsUpto(int index) {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count - index; ++i) {
            fm.popBackStack();
        }
    }
}
