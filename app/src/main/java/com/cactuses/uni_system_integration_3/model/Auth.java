package com.cactuses.uni_system_integration_3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alyona on 02.10.2017.
 */

public class Auth {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("expires")
    @Expose
    private String expires;
    //The user ID of the authentication token
    @SerializedName("user_id")
    @Expose
    private String userId;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
