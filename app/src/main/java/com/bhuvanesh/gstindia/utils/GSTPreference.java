package com.bhuvanesh.gstindia.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.bhuvanesh.gstindia.GSTApplication;

public final class GSTPreference {

    private static final String PREFERENCE_FILE_NAME = "Life After GST";

    private static final String PREFERENCE_KEY_LANGUAGE = "PREFERENCE_KEY_LANGUAGE";
    private static final String PREFERENCE_KEY_LANGUAGE_CODE = "PREFERENCE_KEY_LANGUAGE_CODE";
    private static final String PREFERENCE_KEY_FIRST_TIME_SETTINGS = "PREFERENCE_KEY_FIRST_TIME_SETTINGS";

    private static GSTPreference mInstance;
    private static SharedPreferences mSharedPreferences;

    private GSTPreference() {}

    public static synchronized GSTPreference getInstance() {
        if (mInstance == null) {
            mInstance = new GSTPreference();
            mSharedPreferences = GSTApplication.getInstance().getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mInstance;
    }

    public void setLanguage(int langId) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(PREFERENCE_KEY_LANGUAGE, langId);
        editor.apply();
    }

    public int getLanguage() {
        return mSharedPreferences.getInt(PREFERENCE_KEY_LANGUAGE, 0);
    }

    public void setLanguageCode(String langCode) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREFERENCE_KEY_LANGUAGE_CODE, langCode);
        editor.apply();
    }

    public String getLanguageCode() {
        return mSharedPreferences.getString(PREFERENCE_KEY_LANGUAGE_CODE, "");
    }

    public void setFirstTime(boolean bool) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(PREFERENCE_KEY_FIRST_TIME_SETTINGS, bool);
        editor.apply();
    }

    public boolean isFirstTime() {
        return mSharedPreferences.getBoolean(PREFERENCE_KEY_FIRST_TIME_SETTINGS, true);
    }
}