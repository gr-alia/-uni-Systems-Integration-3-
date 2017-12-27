package com.cactuses.uni_system_integration_3.ui.activity;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cactuses.uni_system_integration_3.App;
import com.cactuses.uni_system_integration_3.BuildConfig;
import com.cactuses.uni_system_integration_3.R;
import com.cactuses.uni_system_integration_3.ui.adapter.TabsPagerAdapter;
import com.cactuses.uni_system_integration_3.utils.PrefsKeyValueStorage;


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
        setTitle("Integration Systems");
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
        PrefsKeyValueStorage prefsKeyValueStorage = new PrefsKeyValueStorage(this);
        boolean visibility = true;
        if (prefsKeyValueStorage.getToken() == null){
            visibility = false;
        }
        menu.findItem(R.id.action_exit).setVisible(visibility);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                new AlertDialog.Builder(this)
                        .setTitle("About Watch Best")
                        .setMessage("Version: " + BuildConfig.VERSION_NAME)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
