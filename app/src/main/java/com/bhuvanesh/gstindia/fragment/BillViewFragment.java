package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jsibbold.zoomage.ZoomageView;

/**
 * Created by Karthikeyan on 04-07-2017.
 */

public class BillViewFragment extends BaseFragment {

    private ZoomageView billZoomageView;
    private String url;

    public static BillViewFragment newInstance(String url) {
        BillViewFragment billViewFragment = new BillViewFragment();
        billViewFragment.url = url;
        return billViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bil_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        billZoomageView = view.findViewById(R.id.zoom_image_bill);
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(url);
        Glide.with(getActivity())
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(billZoomageView);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                pop();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
