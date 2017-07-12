package com.bhuvanesh.gstindia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.gstindia.BaseActivity;
import com.bhuvanesh.gstindia.BaseFragment;
import com.bhuvanesh.gstindia.GSTApplication;
import com.bhuvanesh.gstindia.R;
import com.bhuvanesh.gstindia.adapter.LanguageListAdapter;
import com.bhuvanesh.gstindia.model.Language;
import com.bhuvanesh.gstindia.utils.FileUtil;
import com.bhuvanesh.gstindia.utils.GSTPreference;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Bhuvanesh on 09-07-2017.
 */

public class LanguageListFragment extends BaseFragment {

    public static LanguageListFragment newInstance() {
        return new LanguageListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language_list, container, false);
        (getActivity()).setTitle(getString(R.string.lbl_select_lang));
        ((BaseActivity)getActivity()).setBackEnabled(true);

        if (GSTPreference.getInstance().isFirstTime()) {
            GSTPreference.getInstance().setFirstTime(false);
            GSTPreference.getInstance().setLanguage(1);
        }

        List<Language> languageList = FileUtil.getFromAssetsFolder("language.json",null, new TypeToken<List<Language>>() {}.getType());
        RecyclerView recyclerViewLang = view.findViewById(R.id.recyclerview_language_list);
        recyclerViewLang.setLayoutManager(new LinearLayoutManager(getActivity()));

        LanguageListAdapter adapter = new LanguageListAdapter();
        adapter.setData(languageList, GSTPreference.getInstance().getLanguage());
        recyclerViewLang.setAdapter(adapter);

        adapter.setOnLangSelectedListener(new LanguageListAdapter.OnLangSelectedListener() {
            @Override
            public void onLangSelected(String langCode) {
                GSTApplication.getInstance().updateLang(langCode);
                sendUserProperties("language_preference", langCode);
                onBackPress();
            }
        });

        return view;
    }
}

