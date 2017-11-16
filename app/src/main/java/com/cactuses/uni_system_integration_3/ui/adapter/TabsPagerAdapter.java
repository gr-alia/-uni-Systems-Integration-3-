package com.cactuses.uni_system_integration_3.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cactuses.uni_system_integration_3.ui.fragment.FeedFragment;
import com.cactuses.uni_system_integration_3.ui.fragment.VideoListFragment;

/**
 * Created by Alyona on 28.09.2017.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Featured", "New", "Feed"};

    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 2){
            return new FeedFragment();
        }
        else
            return VideoListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
