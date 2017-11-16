package com.cactuses.uni_system_integration_3.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.cactuses.uni_system_integration_3.network.NetworkSubscriber;

import java.util.HashSet;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Alyona on 29.09.2017.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract int getLayoutId();

    protected HashSet<Subscription> mSubscriptions = new HashSet<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container,
                false);
        return view;
    }

    @Override
    public void onDestroy() {
        for (Subscription subscription : mSubscriptions) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
    protected <T> Subscription subscribe(Observable<T> observable, Action1<T> onSuccess) {
        NetworkSubscriber<T> subscriber = new NetworkSubscriber<T>(getActivity()) {
            @Override
            public void onNext(T t) {
                onSuccess.call(t);
            }
        };

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        mSubscriptions.add(subscription);
        return subscriber;
    }
}
