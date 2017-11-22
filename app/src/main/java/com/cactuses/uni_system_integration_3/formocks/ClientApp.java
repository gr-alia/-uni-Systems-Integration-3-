package com.cactuses.uni_system_integration_3.formocks;

/**
 * Created by Alyona on 22.11.2017.
 */

public class ClientApp {
    FakeServer mFakeServer;
    ScreenView mScreenView;

    public ClientApp(FakeServer fakeServer, ScreenView screenView) {
        mFakeServer = fakeServer;
        mScreenView = screenView;
    }

    public void isAuthorized(String token){
        if (token.isEmpty()){
            mScreenView.openLoginForm();
        }
        else launch();

    }
    public void launch(){
        mScreenView.showLoading();
        if (mFakeServer.fetchImages()){
            mScreenView.showImages();
        }
        else {
            mScreenView.showError();
        }
    }
}
