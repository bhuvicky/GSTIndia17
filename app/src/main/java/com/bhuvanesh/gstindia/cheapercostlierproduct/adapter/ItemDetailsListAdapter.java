package com.bhuvanesh.gstindia.cheapercostlierproduct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.model.ItemDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhuvanesh on 7/7/2017.
 */
public class ItemDetailsListAdapter extends RecyclerView.Adapter<ItemDetailsListAdapter.ViewHolder> {

    private List<ItemDetails> mtemDetailsList;
    private Context mContext;

    public ItemDetailsListAdapter(Context context) {
        mContext = context;
        mtemDetailsList = new ArrayList<>();
    }

    public void setData(List<ItemDetails> list) {
        mtemDetailsList.clear();
        if (list!=null)
        mtemDetailsList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_individual_prod_tax, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemDetails item = mtemDetailsList.get(position);

        holder.textViewItemName.setText(item.itemName);
        holder.textViewAfterGstRate.setText(item.afterGST);
        holder.textViewBeforeGstRate.setText(item.beforeGST);

    }

    @Override
    public int getItemCount() {
        return mtemDetailsList != null ? mtemDetailsList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewItemName;
        private TextView textViewAfterGstRate;
        private TextView textViewBeforeGstRate;

        ViewHolder(View itemView) {
            super(itemView);

            textViewItemName = (TextView) itemView.findViewById(R.id.textview_item_name);
            textViewAfterGstRate = (TextView) itemView.findViewById(R.id.textview_tax_post_gst);
            textViewBeforeGstRate = (TextView) itemView.findViewById(R.id.textview_tax_pre_gst);
        }
    }
}
