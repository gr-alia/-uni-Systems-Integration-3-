package com.cactuses.uni_system_integration_3.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cactuses.uni_system_integration_3.App;
import com.cactuses.uni_system_integration_3.R;


/**
 * Created by Alyona on 02.10.2017.
 */
/* Root Fragment for Feed page */
public class FeedFragment extends Fragment {
    private FragmentTransaction mTransaction;

    public FeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        replaceFragment();

        return view;
    }

    public void replaceFragment() {
        mTransaction = getChildFragmentManager()
                .beginTransaction();
        App app = (App) getActivity().getApplication();
        if (app.isActiveSession()) {
            mTransaction.replace(R.id.root_container, VideoListFragment.newInstance(2));
            mTransaction.commit();
        } else {
            mTransaction.replace(R.id.root_container, new LoginFragment());
            mTransaction.commit();
        }
    }

}
