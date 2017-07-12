package com.bhuvanesh.gstindia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.model.Language;
import com.bhuvanesh.gstindia.utils.GSTPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhuvanesh on 11-07-2017.
 */

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ViewHolder> {

    public interface OnLangSelectedListener {
        void onLangSelected(String langCode);
    }

    private List<Language> mLangList = new ArrayList<>();
    private int mSelectedLang;
    private OnLangSelectedListener mOnLangSelectedListener;

    public void setData(List<Language> list, int selectedLang) {
        mLangList = list;
        mSelectedLang = selectedLang;
        notifyDataSetChanged();
    }

    public void setOnLangSelectedListener(OnLangSelectedListener listener) {
        mOnLangSelectedListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Language item = mLangList.get(position);
        holder.textViewLangName.setText(item.langName);
        holder.imageViewSelected.setVisibility(mSelectedLang == position ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mLangList != null ? mLangList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewLangName;
        private ImageView imageViewSelected;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewLangName = itemView.findViewById(R.id.textview_lang_name);
            imageViewSelected = itemView.findViewById(R.id.imageview_selected);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GSTPreference.getInstance().setLanguage(getAdapterPosition());
                    GSTPreference.getInstance().setLanguageCode(mLangList.get(getAdapterPosition()).langCode);
                    mOnLangSelectedListener.onLangSelected(mLangList.get(getAdapterPosition()).langCode);
                }
            });
        }
    }
}
