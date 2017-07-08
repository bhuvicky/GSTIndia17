package com.bhuvanesh.gstindia.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseFragment;
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
        billZoomageView.setImageURI(Uri.parse(url));
//                StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(url);
//        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public void onComplete(@NonNull Task<Uri> task) {
//                billZoomageView.setImageURI(task.getResult());
//
//            }
//        });
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
