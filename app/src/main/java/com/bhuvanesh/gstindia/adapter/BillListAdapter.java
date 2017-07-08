package com.bhuvanesh.gstindia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    public  interface ItemClickListener{
        void onClick(String url);
    }
    private ItemClickListener listener;
    private List<Bill> billList=new ArrayList<>();
    private Context context;
    private ImageLoader imageLoader= GSTApplication.getInstance().getImageLoader();
    public BillListAdapter(Context context,ItemClickListener listener){
        this.context=context;
        this.listener=listener;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Bill bill=billList.get(position);
        holder.billImageView.setImageUrl(bill.billUrl,imageLoader);

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
            billImageView=itemView.findViewById(R.id.network_imageview_bill);
        }
    }
}
