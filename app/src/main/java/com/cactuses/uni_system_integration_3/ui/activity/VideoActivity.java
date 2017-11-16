package com.cactuses.uni_system_integration_3.ui.activity;


import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cactuses.uni_system_integration_3.R;
import com.cactuses.uni_system_integration_3.ui.adapter.VideoAdapter;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoActivity extends AppCompatActivity {
    private SimpleExoPlayerView mVideoView;
    private Uri mUri;
    private String mUriPath;
    private Handler mMainHandler;
    private DataSource.Factory mediaDataSourceFactory;
    private Boolean isAutoPlay;
    private SimpleExoPlayer mPlayer;
    private TrackSelector mTrackSelector;
    private BandwidthMeter mBandwidthMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mVideoView = (SimpleExoPlayerView) findViewById(R.id.video_view);
        mVideoView.requestFocus();

        isAutoPlay = true;

        mUriPath = getIntent().getStringExtra(VideoAdapter.EXTRA_URI);

        mUri = Uri.parse(mUriPath);

        //Create a default TrackSelector
        mMainHandler = new Handler();
        mBandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, null));
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPlayer == null) {
            initializePlayer();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void initializePlayer() {
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(mBandwidthMeter);
        mTrackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
        //Create the player
        mPlayer = ExoPlayerFactory.newSimpleInstance(this, mTrackSelector);

        mVideoView.setPlayer(mPlayer);
        //Create media sourse for hls stream
        MediaSource videoSource = new HlsMediaSource(mUri, mediaDataSourceFactory, mMainHandler, null);
        //Prepare the player with the source.
        mPlayer.prepare(videoSource);
        mPlayer.setPlayWhenReady(isAutoPlay);

    }

    private void releasePlayer() {
        if (mPlayer != null) {
            isAutoPlay = mPlayer.getPlayWhenReady();
            mPlayer.release();
            mPlayer = null;
            mTrackSelector = null;
        }
    }
}
