package com.bhuvanesh.gstindia.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bhuvanesh.gstindia.GSTApplication;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.model.Bill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthikeyan on 03-07-2017.
 */

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder> {
    private int THUMBSIZE;

    public  interface ItemClickListener{
        void onClick(String url);
    }
    private ItemClickListener listener;
    private List<Bill> billList=new ArrayList<>();
    private Context context;
    private ImageLoader imageLoader= GSTApplication.getInstance().getImageLoader();
    public BillListAdapter(Context context,int size,ItemClickListener listener){
        this.context=context;
        this.listener=listener;
        this.THUMBSIZE=size;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Bill bill=billList.get(position);
       // holder.billImageView.setImageUrl(bill.billUrl,imageLoader);
        holder.billImageView.setDefaultImageResId(R.drawable.gray);
        if(bill.billUrl!=null){

        imageLoader.get(bill.billUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap thumbImage = ThumbnailUtils.extractThumbnail(response.getBitmap(), THUMBSIZE, THUMBSIZE);
                holder.billImageView.setImageBitmap(thumbImage);
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        }

        holder.billImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(billList.get(holder.getAdapterPosition()).billUrl);
            }
        });

    }

    public void setData(List<Bill> bills){

        billList.addAll(bills);
        notifyDataSetChanged();
    }
    public void addData(Bill bill){
        billList.add(bill);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return billList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        private NetworkImageView billImageView;
         ViewHolder(View itemView) {
            super(itemView);
            billImageView=  itemView.findViewById(R.id.network_imageview_bill);
        }
    }
}
