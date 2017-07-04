package com.bhuvanesh.gstindia.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
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
import com.bhuvanesh.gstindia.adapter.ProductListAdapter;
import com.bhuvanesh.gstindia.model.Bill;
import com.bhuvanesh.gstindia.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bhuvanesh on 01-07-2017.
 */

public class ProductListFragment extends BaseFragment implements SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener {

    private Button zeroButton,fiveButton,twelveButton,eighteenButton,twentyEightButton;
    private RecyclerView productRecyclerView;
    private TextView taxTextView;
    private String gst;
    ProductListAdapter productListAdapter;
    List<Product> products;
    List<Product> filteredModelList;
    Map<String,Integer> tax=new HashMap<>();

    public static ProductListFragment newInstance(String gst){
        ProductListFragment productListFragment=new ProductListFragment();
        productListFragment.gst=gst;
         productListFragment.tax.put("zero",0);
        productListFragment.tax.put("five",5);
        productListFragment.tax.put("twelve",12);
        productListFragment.tax.put("eighteen",18);
        productListFragment.tax.put("twentyeight",28);


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
        ((BaseActivity)getActivity()).setBackEnabled(true);
        ((BaseActivity)getActivity()).setTitle("Product List");
        setHasOptionsMenu(true);
        filteredModelList=new ArrayList<>();
        taxTextView=view.findViewById(R.id.textview_tax);
        zeroButton=view.findViewById(R.id.button_zero);
        fiveButton=view.findViewById(R.id.button_five);
        twelveButton=view.findViewById(R.id.button_twelve);
        eighteenButton=view.findViewById(R.id.button_eighteen);
        twentyEightButton=view.findViewById(R.id.button_twenty_eight);
        productRecyclerView=view.findViewById(R.id.recycler_view_products);
        productListAdapter=new ProductListAdapter(getContext());
        productListAdapter.setData(data(gst));
        taxTextView.setText(tax.get(gst)+"% GST");
        productRecyclerView.setAdapter(productListAdapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst="zero";
                taxTextView.setText(tax.get(gst)+"% GST");
                productListAdapter.setData(data(gst));
            }
        });
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst="five";
                taxTextView.setText(tax.get(gst)+"% GST");

                productListAdapter.setData(data(gst));
            }
        });
        twelveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst="twelve";
                taxTextView.setText(tax.get(gst)+"% GST");
                productListAdapter.setData(data(gst));
            }
        });


    }

    private List<Product> data(String gst){
        products=new ArrayList<>();
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
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
        final MenuItem item = menu.findItem(R.id.action_search);
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

        if(s.length()>2){
               filteredModelList=(filteredModelList.size()==0)?filter(products,s):filter(filteredModelList,s);
            if(filteredModelList.size()==0){
                productListAdapter.setData(data(gst));
                Toast.makeText(getContext(),"No data found",Toast.LENGTH_LONG).show();
            }else {
                productListAdapter.setData(filteredModelList);
            }

        }else {productListAdapter.setData(data(gst));}
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
}
