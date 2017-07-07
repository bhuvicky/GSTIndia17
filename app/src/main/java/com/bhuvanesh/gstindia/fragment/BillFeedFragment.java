package com.bhuvanesh.gstindia.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.bhuvanesh.gstindia.adapter.BillListAdapter;
import com.bhuvanesh.gstindia.database.FirebaseRTDB;
import com.bhuvanesh.gstindia.firebase.FirebaseStorageAccess;
import com.bhuvanesh.gstindia.model.Bill;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.bhuvanesh.gstindia.utils.UIUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Karthikeyan on 03-07-2017.
 */

public class BillFeedFragment extends BaseFragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private RecyclerView billRecyclerView;
    private FloatingActionButton addFab;
    private BillListAdapter billListAdapter;
    private int noOfColumns = 0;
    private List<Bill> billList = new ArrayList<>();
    private Query postsQuery;
    private ProgressBar paginationProgressBar;
    private InterstitialAd mInterstitialAd;

    public static BillFeedFragment newInstance() {
        return new BillFeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) getActivity()).setBackEnabled(true);
        ((BaseActivity) getActivity()).setTitle("Bill Explore");
        setHasOptionsMenu(true);
        mInterstitialAd=getInterstitialAdInstance(getContext());
        mInterstitialAd.loadAd(getAdRequest());
        billRecyclerView = view.findViewById(R.id.recycler_view_bills);
        paginationProgressBar = view.findViewById(R.id.progressbar);
        addFab = view.findViewById(R.id.fab_add);
        billListAdapter = new BillListAdapter(getContext(), new BillListAdapter.ItemClickListener() {
            @Override
            public void onClick(String url) {
                replace(R.id.fragment_host, BillViewFragment.newInstance(url));
            }
        });
        billRecyclerView.setAdapter(billListAdapter);
        noOfColumns = UIUtils.getNumOfColumns(getActivity(), 100);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), noOfColumns);
        billRecyclerView.setLayoutManager(gridLayoutManager);

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });
        newData();
        postsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                paginationProgressBar.setVisibility(View.GONE);
                billListAdapter.addData(dataSnapshot.getValue(Bill.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                paginationProgressBar.setVisibility(View.GONE);
                billListAdapter.addData(dataSnapshot.getValue(Bill.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                paginationProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                paginationProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                paginationProgressBar.setVisibility(View.GONE);
            }
        });
        postsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void newData() {
        postsQuery = FirebaseDatabase.getInstance().getReference().child("bills")
                .orderByKey().limitToFirst(100);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            UploadTask uploadTask = new FirebaseStorageAccess(getContext()).uploadPhoto(uri);
            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    Bill bill = new Bill();
                    bill.billUrl = task.getResult().getDownloadUrl().toString();
                    bill.time = System.currentTimeMillis();
                    new FirebaseRTDB().addBill(bill);

                }
            });
            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    Toast.makeText(getContext(), "Unable to upload your Invoice", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                pop();
                if(mInterstitialAd.isLoaded())mInterstitialAd.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onBackPress() {
        super.onBackPress();
        if(mInterstitialAd.isLoaded())mInterstitialAd.show();

    }
}
