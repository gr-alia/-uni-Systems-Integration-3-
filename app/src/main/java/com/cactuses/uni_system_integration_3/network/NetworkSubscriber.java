package com.cactuses.uni_system_integration_3.network;


import android.app.Activity;
import android.app.AlertDialog;

import rx.Subscriber;

/**
 * Created by Alyona on 29.09.2017.
 */

public abstract class NetworkSubscriber<T> extends Subscriber<T> {
    protected Activity mActivity;

    public NetworkSubscriber(Activity activity){
        mActivity = activity;
    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }


    private void handleError(Throwable e) {
        if (!mActivity.isFinishing()) {
            new AlertDialog.Builder(mActivity)
                    .setTitle("Error")
                    .setMessage(e.getMessage())
                    .show();
        }
    }
}
