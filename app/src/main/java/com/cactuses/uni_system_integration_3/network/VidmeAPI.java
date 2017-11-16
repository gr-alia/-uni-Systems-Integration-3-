package com.cactuses.uni_system_integration_3.network;



import com.cactuses.uni_system_integration_3.model.AuthWrapper;
import com.cactuses.uni_system_integration_3.model.Wrapper;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alyona on 29.09.2017.
 */

public interface VidmeAPI {
    String BASE_URL = "https://api.vid.me/";

    @GET("videos/featured")
    Observable<Wrapper> getFeatured(@Query("offset") Integer offset, @Query("limit") Integer limit);

    @GET("videos/new")
    Observable<Wrapper> getNew(@Query("offset") Integer offset, @Query("limit") Integer limit);

    @Headers("Authorization: Basic Zm9vOmJhcg==")
    @POST("auth/create")
    Observable<AuthWrapper> createAuth(@Query("username") String uname, @Query("password") String pw);

    @GET("videos/feed")
    Observable<Wrapper> getFeed(@Header("AccessToken") String accessToken, @Query("offset") Integer offset, @Query("limit") Integer limit);

    @POST("auth/delete")
    Observable<AuthWrapper> deleteAuth(@Header("AccessToken") String accessToken);
}

