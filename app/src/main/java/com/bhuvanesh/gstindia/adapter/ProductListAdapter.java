package com.bhuvanesh.gstindia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bhuvanesh.gstindia.GSTApplication;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.firebase.FirebaseStorageAccess;
import com.bhuvanesh.gstindia.model.Product;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    public List<Product> mProductList=new ArrayList<>();
    public ImageLoader imageLoader= GSTApplication.getInstance().getImageLoader();
    private Context context;
    public ProductListAdapter(Context context){
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         Product product= mProductList.get(position);
        holder.nameTextView.setText(product.productName);
        StorageReference storageReference=new FirebaseStorageAccess(context).getUrl(product.gst+"/"+product.productImage);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(holder.productNImageView);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
    public void setData(List<Product> productList) {
        mProductList.clear();
        mProductList = productList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
     public TextView nameTextView;
     public ImageView productNImageView;

    public ViewHolder(View itemView) {
        super(itemView);
        nameTextView=itemView.findViewById(R.id.textview_name_of_product);
        productNImageView=itemView.findViewById(R.id.imageview_product);

    }
}


}
