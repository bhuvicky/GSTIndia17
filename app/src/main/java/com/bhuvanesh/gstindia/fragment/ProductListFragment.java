package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bhuvanesh.gstindia.R;

import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.adapter.ProductListAdapter;
import com.bhuvanesh.gstindia.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class ProductListFragment extends BaseFragment {

    private Button zeroButton,fiveButton,twelveButton,eighteenButton,twentyEightButton;
    private RecyclerView productRecyclerView;
    private String gst;
    public static ProductListFragment newInstance(String gst){
        ProductListFragment productListFragment=new ProductListFragment();
        productListFragment.gst=gst;
        return productListFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        zeroButton=view.findViewById(R.id.button_zero);
        fiveButton=view.findViewById(R.id.button_five);
        twelveButton=view.findViewById(R.id.button_twelve);
        eighteenButton=view.findViewById(R.id.button_eighteen);
        twentyEightButton=view.findViewById(R.id.button_twenty_eight);
        productRecyclerView=view.findViewById(R.id.recycler_view_products);
        final ProductListAdapter productListAdapter=new ProductListAdapter(getContext());
        productListAdapter.setData(data(gst));
        productRecyclerView.setAdapter(productListAdapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListAdapter.setData(data("zero"));
            }
        });
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListAdapter.setData(data("five"));
            }
        });
        twelveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListAdapter.setData(data("twelve"));
            }
        });


    }

    private List<Product> data(String gst){
        List<Product> products=new ArrayList<>();
        switch (gst){
            case "zero":
                String[] namesOf0Products=getActivity().getResources().getStringArray(R.array._0_products_name);
                String[] imagesOf0Products=getActivity().getResources().getStringArray(R.array._0_products_images);
                for(int i=0;i<namesOf0Products.length;i++){
                    Product product=new Product();
                    product.productName=namesOf0Products[i];
                    product.productImage=imagesOf0Products[i];
                    product.gst=gst;
                    products.add(product);
                }
                break;
            case "five":
                String[] namesOf5Products=getActivity().getResources().getStringArray(R.array._5_products_names);
                String[] imagesOf5Products=getActivity().getResources().getStringArray(R.array._5_products_images);
                for(int i=0;i<namesOf5Products.length;i++){
                    Product product=new Product();
                    product.productName=namesOf5Products[i];
                    product.productImage=imagesOf5Products[i];
                    product.gst=gst;
                    products.add(product);
                }
                break;
            case "twelve":
                String[] namesOf12Products=getActivity().getResources().getStringArray(R.array._12_products_names);
                String[] imagesOf12Products=getActivity().getResources().getStringArray(R.array._12_products_images);
                for(int i=0;i<namesOf12Products.length;i++){
                    Product product=new Product();
                    product.productName=namesOf12Products[i];
                    product.productImage=imagesOf12Products[i];
                    product.gst=gst;
                    products.add(product);
                }
                break;
        }

        return products;


    }




}
