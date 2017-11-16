package com.cactuses.uni_system_integration_3;

import android.app.Application;
import android.content.SharedPreferences;

import com.cactuses.uni_system_integration_3.network.RetrofitService;


/**
 * Created by Alyona on 29.09.2017.
 */

public class App extends Application {
    private boolean isActiveSession;
    private SharedPreferences mPrefs;

    public boolean isActiveSession() {
        return isActiveSession;
    }

    public void setActiveSession(boolean activeSession) {
        isActiveSession = activeSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.initInstance();
        initSession();
    }

    private void initSession() {
        mPrefs = getSharedPreferences(getString(R.string.pref_user_data), MODE_PRIVATE);
        boolean isContains = mPrefs.contains(getString(R.string.saved_token));
        setActiveSession(isContains);
    }

}
