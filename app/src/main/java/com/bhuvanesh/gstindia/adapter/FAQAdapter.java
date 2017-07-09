package com.bhuvanesh.gstindia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.fragment.FAQFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthikeyan on 05-07-2017.
 */

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {

  private List<String> questionList=new ArrayList<>();
  public interface IOnItemClickListener{
      void onItemClick(int postion,String question);
  }
  private IOnItemClickListener iOnItemClickListener;
  public  FAQAdapter(IOnItemClickListener iOnItemClickListener){
      this.iOnItemClickListener=iOnItemClickListener;
  }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.questionTextView.setText(questionList.get(position));
        holder.questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnItemClickListener.onItemClick(holder.getAdapterPosition(),questionList.get(holder.getAdapterPosition()));
            }
        });

    }

   public void setData(List<String> quesyionList){
       this.questionList=quesyionList;
       notifyDataSetChanged();
   }
    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView questionTextView;
    public ViewHolder(View itemView) {
        super(itemView);
        questionTextView= (TextView) itemView.findViewById(R.id.textview_faq_question);    }
}

}
