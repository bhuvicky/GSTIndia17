package com.bhuvanesh.gstindia;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.google.firebase.analytics.FirebaseAnalytics;

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

    protected void logDashboardEvent(String content, String contentType, String propertyName, String propertyValue, String eventName) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CONTENT, content);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        GSTApplication.getAnalyticsInstance().setUserProperty(propertyName, propertyName);
        GSTApplication.getAnalyticsInstance().logEvent(eventName, bundle);
    }
}
