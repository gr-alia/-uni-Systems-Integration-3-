package com.cactuses.uni_system_integration_3;

import android.app.Application;
import android.content.SharedPreferences;

import com.cactuses.uni_system_integration_3.network.RetrofitService;


/**
 * Created by Alyona on 29.09.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.initInstance();

    }



}
