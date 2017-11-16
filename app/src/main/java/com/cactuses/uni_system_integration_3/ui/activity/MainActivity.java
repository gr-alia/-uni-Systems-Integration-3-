package com.cactuses.uni_system_integration_3.ui.activity;

import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.cactuses.uni_system_integration_3.App;
import com.cactuses.uni_system_integration_3.R;
import com.cactuses.uni_system_integration_3.ui.adapter.TabsPagerAdapter;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private App mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        mApp = (App) getApplication();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TabsPagerAdapter pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_exit).setVisible(mApp.isActiveSession());
        return super.onPrepareOptionsMenu(menu);
    }
}
