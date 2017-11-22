package com.cactuses.uni_system_integration_3.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cactuses.uni_system_integration_3.App;
import com.cactuses.uni_system_integration_3.R;
import com.cactuses.uni_system_integration_3.utils.AuthHelper;
import com.cactuses.uni_system_integration_3.utils.AuthView;


/**
 * Created by Alyona on 02.10.2017.
 */
/* Root Fragment for Feed page */
public class FeedFragment extends Fragment implements AuthView {
    private AuthHelper mAuthHelper;

    public FeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        mAuthHelper = new AuthHelper(getActivity(), this);

        mAuthHelper.tryLogIn();

        return view;
    }


    public void replaceFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.root_container, fragment);
        transaction.commit();
    }

    @Override
    public void openNewsFeed() {
        replaceFragment(VideoListFragment.newInstance(2));
    }

    @Override
    public void showLoginForm() {
        replaceFragment(new LoginFragment());
    }
}
