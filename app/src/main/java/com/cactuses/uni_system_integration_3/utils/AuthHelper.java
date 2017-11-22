package com.cactuses.uni_system_integration_3.utils;


import android.support.v4.app.FragmentActivity;



/**
 * Created by Alyona on 21.11.2017.
 */

public class AuthHelper {
    private FragmentActivity mActivity;
    private AuthView mAuthView;

    public AuthHelper(FragmentActivity activity, AuthView authView) {
        mActivity = activity;
        mAuthView = authView;
    }

    public void tryLogIn() {
        PrefsKeyValueStorage prefsKeyValueStorage = new PrefsKeyValueStorage(mActivity);
        if (prefsKeyValueStorage.getToken() != null) {
            mAuthView.openNewsFeed();
        } else {
            mAuthView.showLoginForm();
        }
    }

}
