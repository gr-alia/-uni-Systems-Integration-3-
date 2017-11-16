package com.cactuses.uni_system_integration_3.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.cactuses.uni_system_integration_3.App;
import com.cactuses.uni_system_integration_3.BuildConfig;
import com.cactuses.uni_system_integration_3.R;
import com.cactuses.uni_system_integration_3.model.Video;
import com.cactuses.uni_system_integration_3.model.Wrapper;
import com.cactuses.uni_system_integration_3.network.RetrofitService;
import com.cactuses.uni_system_integration_3.network.VidmeAPI;
import com.cactuses.uni_system_integration_3.ui.EndlessRecyclerOnScrollListener;
import com.cactuses.uni_system_integration_3.ui.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Alyona on 28.09.2017.
 */

public class VideoListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "VideoListFragment";
    private static final String ARG_NUM = "ARG_NUM";

    private RecyclerView mRecyclerView;
    private VideoAdapter mVideoAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    private List<Video> mVideos = new ArrayList<>();
    private Wrapper mDataSet;
    private VidmeAPI apiInstance;

    private int pageNumber;
    private SharedPreferences mPrefs;

    public VideoListFragment() {

    }

    public static VideoListFragment newInstance(int num) {
        Bundle args = new Bundle();
        args.putInt(ARG_NUM, num);
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        apiInstance = RetrofitService.getInstance().getApi();
        Bundle args = getArguments();
        pageNumber = args.getInt(ARG_NUM);

        mPrefs = getActivity().getSharedPreferences(
                getString(R.string.pref_user_data), Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeColors(
                Color.RED, Color.GREEN, Color.BLUE);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.video_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mVideoAdapter = new VideoAdapter(getContext(), mVideos);
        mRecyclerView.setAdapter(mVideoAdapter);

        loadData(checkPageNum(0));
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                Integer offset = mDataSet.getPage().getOffset() + 10;
                loadData(checkPageNum(offset));
            }
        });
        return view;
    }

    private void loadData(Observable<Wrapper> observable) {
        subscribe(observable, wrapper -> {
            mDataSet = wrapper;
            updateVideos();
            mRefreshLayout.setRefreshing(false);
        });
    }

    private void updateVideos() {
        mVideos.addAll(mDataSet.getVideos());
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private Observable<Wrapper> checkPageNum(Integer offset) {
        //offset of item in response
        switch (pageNumber) {
            case 0:
                return apiInstance.getFeatured(offset, 10);
            case 1:
                return apiInstance.getNew(offset, 10);
            case 2:
                String accessToken = mPrefs.getString(getString(R.string.saved_token), null);
                return apiInstance.getFeed(accessToken, offset, 10);
            default:
                return null;
        }
    }

    @Override
    public void onRefresh() {
        mVideos.clear();
        loadData(checkPageNum(0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        subscribe(apiInstance.deleteAuth(mPrefs.getString(getString(R.string.saved_token), null)), authWrapper -> {
                    if (authWrapper.getStatus()) {
                        mPrefs.edit().clear().commit();
                        App app = (App) getActivity().getApplication();
                        app.setActiveSession(false);
                        if (getParentFragment() != null) {
                            FeedFragment feedFragment = (FeedFragment) getParentFragment();
                            feedFragment.replaceFragment();
                        }
                        getActivity().invalidateOptionsMenu();
                        Toast.makeText(getContext(), "Logout is success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Logout was failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
