package com.bhuvanesh.gstindia.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.GSTApplication;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jsibbold.zoomage.ZoomageView;

/**
 * Created by Karthikeyan on 04-07-2017.
 */

public class BillViewFragment extends BaseFragment {

    private ZoomageView billZoomageView;
    private String url;
    private ProgressBar loadImageProgressBar;

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
        ((BaseActivity) getActivity()).setBackEnabled(true);
        ((BaseActivity) getActivity()).setTitle(getString(R.string.explore_bill));

        billZoomageView = (ZoomageView) view.findViewById(R.id.zoom_image_bill);
        loadImageProgressBar=view.findViewById(R.id.progressbar_load_large_image);
        ImageLoader imageLoader= GSTApplication.getInstance().getImageLoader();
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                loadImageProgressBar.setVisibility(View.INVISIBLE);
                billZoomageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error in Loading Image",Toast.LENGTH_SHORT).show();
            }
        });
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
