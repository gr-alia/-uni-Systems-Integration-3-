package com.cactuses.uni_system_integration_3.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alyona on 29.09.2017.
 */

public class RetrofitService {
    private static RetrofitService instance;

    private VidmeAPI serviceImpl;

    private RetrofitService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VidmeAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();

        serviceImpl = retrofit.create(VidmeAPI.class);
    }

    public static void initInstance() {
        instance = new RetrofitService();
    }
    public static RetrofitService getInstance() {
        return instance;
    }
    public VidmeAPI getApi(){
        return serviceImpl;
    }
}
