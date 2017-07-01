package com.bhuvanesh.gstindia;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class BaseFragment extends Fragment {

    public void replace(int containerId, BaseFragment fragment) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).replace(containerId, fragment);
        }
    }

    public void pop() {
        if (getActivity() != null) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
        }
    }

    public void popAllFragmentsUpto(int index) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).popAllFragmentsUpto(index);
        }
    }

    protected void onBackPress() {
        pop();
    }
}
