package com.bhuvanesh.gstindia.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.R;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.bhuvanesh.gstindia.adapter.ProductListAdapter;
import com.bhuvanesh.gstindia.firebase.FirebaseStorageAccess;
import com.bhuvanesh.gstindia.model.Bill;
import com.bhuvanesh.gstindia.model.Product;
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class ProductListFragment extends BaseFragment implements SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener {

    private Button zeroButton, fiveButton, twelveButton, eighteenButton, twentyEightButton;
    private RecyclerView productRecyclerView;
    private TextView taxTextView;
    private String gst;
    ProductListAdapter productListAdapter;
    List<Product> products;
    List<Product> filteredModelList;
    Map<String, Integer> tax = new HashMap<>();
    private InterstitialAd mInterstitialAd;

    private ProgressBar productsProogressBar;
    public static ProductListFragment newInstance(String gst) {
        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.gst = gst;
        productListFragment.tax.put("zero", 0);
        productListFragment.tax.put("five", 5);
        productListFragment.tax.put("twelve", 12);
        productListFragment.tax.put("eighteen", 18);
        productListFragment.tax.put("twenty_eight", 28);

        return productListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ((BaseActivity) getActivity()).setBackEnabled(true);
        ((BaseActivity) getActivity()).setTitle("Product List");

        mInterstitialAd=getInterstitialAdInstance(getContext());
        mInterstitialAd.loadAd(getAdRequest());

        filteredModelList = new ArrayList<>();
        taxTextView = (TextView) view.findViewById(R.id.textview_tax);
        zeroButton = (Button) view.findViewById(R.id.button_zero);
        fiveButton = (Button) view.findViewById(R.id.button_five);
        twelveButton = (Button) view.findViewById(R.id.button_twelve);
        eighteenButton = (Button) view.findViewById(R.id.button_eighteen);
        twentyEightButton = (Button) view.findViewById(R.id.button_twenty_eight);
        productsProogressBar= (ProgressBar) view.findViewById(R.id.progressbar_products);
        productRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_products);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productListAdapter = new ProductListAdapter(getContext());
       // productListAdapter.setData();
        productRecyclerView.setAdapter(productListAdapter);
        prepareData(gst);

        taxTextView.setText(tax.get(gst) + "% GST");
        taxTextView.setText(getTitle(gst));


        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "zero";
                taxTextView.setText(getTitle(gst));
                prepareData("zero");
               // productListAdapter.setData();
            }
        });
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "five";
                taxTextView.setText(getTitle(gst));
                prepareData("five");
               // productListAdapter.setData();
            }
        });
        twelveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "twelve";
                taxTextView.setText(getTitle(gst));
                prepareData("twelve");
                //productListAdapter.setData();
            }
        });

        eighteenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "eighteen";
                taxTextView.setText(getTitle(gst));
                prepareData("eighteen");
            }
        });
        twentyEightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "twenty_eight";
                taxTextView.setText(getTitle(gst));
              prepareData("twentyeight");
            }
        });


    }

    private String getTitle(String gs) {
        return tax.get(gs) + "% GST";
    }

    private void prepareData(String gst) {
        productListAdapter.clear();
        productsProogressBar.setVisibility(View.VISIBLE);
        switch (gst) {
            case "zero":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                getData("zero");

                break;
            case "five":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                getData("five");
                break;

            case "twelve":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                getData("twelve");
               break;
            case "eighteen":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                getData("eighteen");
               break;
            default:
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                getData("twentyeight");

        }
    }



    public void getData(String gst) {

        Query productQuery = FirebaseDatabase.getInstance().getReference().child(gst).orderByChild("item");
        productQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                productListAdapter.addItem(dataSnapshot.getValue(Product.class));
                productsProogressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        android.support.v7.widget.SearchView searchView =
                (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchView!=null) {
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(ProductListFragment.this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                pop();
                if(mInterstitialAd.isLoaded())mInterstitialAd.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if (s.length() > 2) {
            filteredModelList = (filteredModelList.size() == 0) ? filter(productListAdapter.getProductList(), s) : filter(filteredModelList, s);
            if (filteredModelList.size() == 0) {
                prepareData(gst);
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
            } else {
                productListAdapter.setData(filteredModelList);
            }

        } else {
            prepareData(gst);
        }
        return true;
    }

    private List<Product> filter(List<Product> items, String query) {
        query = query.toLowerCase();

        final List<Product> filteredModelList = new ArrayList<>();
        for (Product item : items) {
            final String text = item.item.toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(item);
            }
        }
        return filteredModelList;
    }

    @Override
    protected void onBackPress() {
        super.onBackPress();
        if(mInterstitialAd.isLoaded())mInterstitialAd.show();

    }

}
