package com.cactuses.uni_system_integration_3.integration;

import android.support.v7.app.AppCompatActivity;

import com.cactuses.uni_system_integration_3.BuildConfig;
import com.cactuses.uni_system_integration_3.ui.activity.MainActivity;
import com.cactuses.uni_system_integration_3.ui.fragment.VideoListFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.spy;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    @Test
    public void titleIsCorrect() throws Exception {
        AppCompatActivity activity = Robolectric.setupActivity(MainActivity.class);
        assertTrue(activity.getTitle().toString().equals("Integration Systems"));
    }
}