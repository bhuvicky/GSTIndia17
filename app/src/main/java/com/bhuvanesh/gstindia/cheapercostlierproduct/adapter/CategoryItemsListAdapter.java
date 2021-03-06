package com.bhuvanesh.gstindia.cheapercostlierproduct.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.model.CategoryWiseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhuvanesh on 7/7/2017.
 */

public class CategoryItemsListAdapter extends RecyclerView.Adapter<CategoryItemsListAdapter.ViewHolder> {

    private List<CategoryWiseItem> mCategoryWiseItem=new ArrayList<>();
    private Context mContext;

    public CategoryItemsListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<CategoryWiseItem> list) {
        mCategoryWiseItem.clear();
        if (list!=null)
        mCategoryWiseItem.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_category_wise_product_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryWiseItem item = mCategoryWiseItem.get(position);
        holder.textViewCategoryName.setText(item.categoryName);

        holder.adapter = new ItemDetailsListAdapter(mContext);
        holder.recyclerViewItemTax.setAdapter(holder.adapter);
        holder.adapter.setData(item.taxComparedItemList);
    }

    @Override
    public int getItemCount() {
        return mCategoryWiseItem != null ? mCategoryWiseItem.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerViewItemTax;
        private ItemDetailsListAdapter adapter;
        private TextView textViewCategoryName;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewCategoryName = (TextView) itemView.findViewById(R.id.textview_category_name);
            recyclerViewItemTax = (RecyclerView) itemView.findViewById(R.id.recycler_view_single_category_items);
            recyclerViewItemTax.setNestedScrollingEnabled(false);
            recyclerViewItemTax.setLayoutManager(new LinearLayoutManager(mContext));
        }
    }
}
