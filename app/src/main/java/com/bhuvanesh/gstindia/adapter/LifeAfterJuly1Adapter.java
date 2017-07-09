package com.bhuvanesh.gstindia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.model.LifeAfterJuly1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhuvanesh on 7/3/2017.
 */

public class LifeAfterJuly1Adapter extends RecyclerView.Adapter<LifeAfterJuly1Adapter.ViewHolder> {

    private List<LifeAfterJuly1> mList = new ArrayList<>();

    public void setData(List<LifeAfterJuly1> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public LifeAfterJuly1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_life_after_july1, parent, false));
    }

    @Override
    public void onBindViewHolder(LifeAfterJuly1Adapter.ViewHolder holder, int position) {
        LifeAfterJuly1 item = mList.get(position);
        holder.textViewTitle.setText(item.title);
        holder.textViewDescription.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

   class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewDescription;

        ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textview_title);
            textViewDescription = (TextView) itemView.findViewById(R.id.textview_description);
        }
    }
}
