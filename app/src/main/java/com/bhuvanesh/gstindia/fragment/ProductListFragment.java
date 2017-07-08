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

        ((BaseActivity) getActivity()).setBackEnabled(true);
        ((BaseActivity) getActivity()).setTitle("Product List");
        setHasOptionsMenu(true);
        mInterstitialAd=getInterstitialAdInstance(getContext());
        mInterstitialAd.loadAd(getAdRequest());

        filteredModelList = new ArrayList<>();
        taxTextView = view.findViewById(R.id.textview_tax);
        zeroButton = view.findViewById(R.id.button_zero);
        fiveButton = view.findViewById(R.id.button_five);
        twelveButton = view.findViewById(R.id.button_twelve);
        eighteenButton = view.findViewById(R.id.button_eighteen);
        twentyEightButton = view.findViewById(R.id.button_twenty_eight);

        productRecyclerView = view.findViewById(R.id.recycler_view_products);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productListAdapter = new ProductListAdapter(getContext());
        productListAdapter.setData(prepareData(gst));
        productRecyclerView.setAdapter(productListAdapter);

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
                productListAdapter.setData(prepareData("five"));
            }
        });
        twelveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "twelve";
                taxTextView.setText(getTitle(gst));
                productListAdapter.setData(prepareData("twelve"));
            }
        });

        eighteenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "eighteen";
                taxTextView.setText(getTitle(gst));
                productListAdapter.setData(prepareData("eighteen"));
            }
        });

        twentyEightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst = "twenty_eight";
                taxTextView.setText(getTitle(gst));
                productListAdapter.setData(prepareData("twenty_eight"));
            }
        });


    }

    private String getTitle(String gs) {
        return tax.get(gs) + "% GST";
    }

    private List<Product> prepareData(String gst) {
        switch (gst) {
            case "zero":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                return productDetails(R.array._0_products_name, R.array._0_products_images, gst);
            case "five":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                return productDetails(R.array._5_products_names, R.array._5_products_images, gst);
            case "twelve":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                return productDetails(R.array._12_products_names, R.array._12_products_images, gst);
            case "eighteen":
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                return productDetails(R.array._18_products_names, R.array._18_products_images, gst);
            default:
                logDashboardEvent("Product Screen Content", "0% Slab", "favourite_app_feature",
                        "0% Slab", FirebaseAnalytics.Event.SELECT_CONTENT);
                return productDetails(R.array._28_products_names, R.array._28_products_images, gst);
        }
    }

    private List<Product> productDetails(int arrayResIdNames, int arrayResIdImages, String gst) {
        products = new ArrayList<>();
        String[] namesOf12Products = getActivity().getResources().getStringArray(arrayResIdNames);
        String[] imagesOf12Products = getActivity().getResources().getStringArray(arrayResIdImages);
        for (int i = 0; i < namesOf12Products.length; i++) {
            final Product product = new Product();
            product.productName = namesOf12Products[i];
            product.productImage = imagesOf12Products[i];
            product.gst = gst;
            StorageReference storageReference = new FirebaseStorageAccess(getContext()).getUrl(product.gst + "/" + product.productImage);

            storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    GstLoggerUtil.debug("hh", task.getResult().toString());
                    product.url = task.getResult().toString();
                    productListAdapter.addItem(product);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
            return products;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        android.support.v7.widget.SearchView searchView =
                (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(ProductListFragment.this);
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
            filteredModelList = (filteredModelList.size() == 0) ? filter(products, s) : filter(filteredModelList, s);
            if (filteredModelList.size() == 0) {
                productListAdapter.setData(prepareData(gst));
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
            } else {
                productListAdapter.setData(filteredModelList);
            }

        } else {
            productListAdapter.setData(prepareData(gst));
        }
        return true;
    }

    private List<Product> filter(List<Product> items, String query) {
        query = query.toLowerCase();

        final List<Product> filteredModelList = new ArrayList<>();
        for (Product item : items) {
            final String text = item.productName.toLowerCase();
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
