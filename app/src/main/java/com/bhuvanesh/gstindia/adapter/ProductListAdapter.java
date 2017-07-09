package com.bhuvanesh.gstindia.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.bhuvanesh.gstindia.utils.GstLoggerUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    public List<Product> mProductList = new ArrayList<>();
    private Context context;
    private ImageLoader imageLoader= GSTApplication.getInstance().getImageLoader();

    public ProductListAdapter(Context context) {
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.nameTextView.setText(product.item);

        if(product.URL!=null) {
            holder.productNImageView.setImageUrl(product.URL, imageLoader);
        }
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
    public List<Product> getProductList(){return mProductList;}
    public void addItem(Product product){
        mProductList.add(product);
        notifyDataSetChanged();
    }
    public void clear(){
        mProductList.clear();
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public NetworkImageView productNImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.textview_name_of_product);
            productNImageView = (NetworkImageView) itemView.findViewById(R.id.network_imageview_product);

        }
    }
     }



