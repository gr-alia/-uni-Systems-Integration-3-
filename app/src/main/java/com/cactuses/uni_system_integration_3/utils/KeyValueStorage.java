package com.cactuses.uni_system_integration_3.utils;

/**
 * Created by Alyona on 22.11.2017.
 */

public interface KeyValueStorage {
    String TOKEN_KEY = "com.alia.watchbest.KEY_STORED_TOKEN";

    void saveToken(String token);

    String getToken();
}
