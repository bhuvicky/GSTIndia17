package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.activity.GstActivity;
import com.bhuvanesh.gstindia.adapter.FAQAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthikeyan on 05-07-2017.
 */

public class FAQFragment extends BaseFragment {


    private InterstitialAd mInterstitialAd;

    public static FAQFragment newInstance(){return new FAQFragment();}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq_questions,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ((BaseActivity)getActivity()).setBackEnabled(true);
        ((BaseActivity)getActivity()).setTitle("GST FAQ ?");

        mInterstitialAd=getInterstitialAdInstance(getContext());
        mInterstitialAd.loadAd(getAdRequest());

        RecyclerView questionRecyclerView= (RecyclerView) view.findViewById(R.id.recycler_view_question);
        questionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final String[] answers=getActivity().getResources().getStringArray(R.array.answers);

        FAQAdapter faqAdapter=new FAQAdapter(new FAQAdapter.IOnItemClickListener() {
            @Override
            public void onItemClick(int postion, String question) {
                replace(R.id.fragment_host,FAQAnswerFragment.newInstance(question,answers[postion]));
            }
        });
        questionRecyclerView.setAdapter(faqAdapter);
        List<String> question=new ArrayList<>();
        for(String ques:getActivity().getResources().getStringArray(R.array.questions)){
            question.add(ques);
        }

        faqAdapter.setData(question);

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                pop();
                if(mInterstitialAd.isLoaded())mInterstitialAd.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onBackPress() {
        super.onBackPress();
        if(mInterstitialAd.isLoaded())mInterstitialAd.show();

    }

}
