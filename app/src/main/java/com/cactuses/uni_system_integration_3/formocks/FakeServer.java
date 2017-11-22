package com.cactuses.uni_system_integration_3.formocks;

/**
 * Created by Alyona on 22.11.2017.
 */

public class FakeServer {
    public int timeout = 90;

    public boolean fetchImages() {
        if (timeout < 100)
            return true;
        else return false;
    }
}
