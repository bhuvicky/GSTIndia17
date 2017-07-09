package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;

/**
 * Created by Karthikeyan on 06-07-2017.
 */

public class FAQAnswerFragment extends BaseFragment {

    private String question,answer;
    public static FAQAnswerFragment newInstance(String question,String answer){
        FAQAnswerFragment faqAnswerFragment=new FAQAnswerFragment();
        faqAnswerFragment.question=question;
        faqAnswerFragment.answer=answer;
    return faqAnswerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq_answer,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ((BaseActivity)getActivity()).setBackEnabled(true);
        ((BaseActivity)getActivity()).setTitle("Answer");

        TextView questionTexview = (TextView) view.findViewById(R.id.texview_heading_question);
        TextView answerTextView= (TextView) view.findViewById(R.id.texview_answer);
        questionTexview.setText(question);
        answerTextView.setText(answer);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                pop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
