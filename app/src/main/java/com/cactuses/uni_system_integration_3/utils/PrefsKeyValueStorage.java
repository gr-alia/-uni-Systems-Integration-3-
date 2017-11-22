package com.cactuses.uni_system_integration_3.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Alyona on 22.11.2017.
 */

public class PrefsKeyValueStorage implements KeyValueStorage {
    private static final String PREFS_TOKEN = "com.alia.watchbest.PREFERENCE_USER_TOKEN";
   private FragmentActivity mActivity;

    public PrefsKeyValueStorage(FragmentActivity activity) {
        mActivity = activity;
    }

    @Override
    public void saveToken(String token) {
        getSharedPrefs().edit().putString(TOKEN_KEY , token).commit();

    }

    @Override
    public String getToken() {
        SharedPreferences prefs = getSharedPrefs();
        if (prefs.contains(TOKEN_KEY)) {
            return prefs.getString(TOKEN_KEY, "");
        }
        return null;
    }
    public void deleteToken(){
        getSharedPrefs().edit().remove(TOKEN_KEY).commit();

    }

    private SharedPreferences getSharedPrefs() {
        return mActivity.getSharedPreferences(PREFS_TOKEN, Context.MODE_PRIVATE);
    }
}
