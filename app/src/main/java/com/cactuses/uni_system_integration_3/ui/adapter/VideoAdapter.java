package com.cactuses.uni_system_integration_3.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cactuses.uni_system_integration_3.R;
import com.cactuses.uni_system_integration_3.model.Video;
import com.cactuses.uni_system_integration_3.ui.activity.VideoActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Alyona on 28.09.2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    public static final String EXTRA_URI =
            "com.alia.watchbest.ui.adapter.URI";
    private Context mContext;
    private List<Video> mVideos;

    public VideoAdapter(Context context, List<Video> videos) {
        mContext = context;
        mVideos = videos;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.list_item_video, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        Video video = mVideos.get(position);
        holder.bindVideo(video);
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }


    class VideoHolder extends RecyclerView.ViewHolder {
        private ImageView mThumbnailView;
        private TextView mTittleView;
        private TextView mLikesView;
        private Gson gson;

        private Video mVideo;

        public VideoHolder(View itemView) {
            super(itemView);
            mThumbnailView = (ImageView) itemView.findViewById(R.id.thumbnail);
            mTittleView = (TextView) itemView.findViewById(R.id.tittle);
            mLikesView = (TextView) itemView.findViewById(R.id.likes);


        }

        public void bindVideo(Video video) {
            mVideo = video;
            Picasso.with(mContext)
                    .load(mVideo.getThumbnailUrl())
                    .placeholder(R.drawable.ic_image_holder)
                    .into(mThumbnailView);
            mTittleView.setText(mVideo.getTitle());
            String likesNum = mContext.getString(R.string.likes_number, mVideo.getLikesCount());
            mLikesView.setText(likesNum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VideoActivity.class);
                    intent.putExtra(EXTRA_URI,mVideo.getCompleteUrl());
                    mContext.startActivity(intent);
                }
            });
        }
		
		public String getSomeString(){
			return mContext.getString(R.string.app_name);
		}

    }
}
