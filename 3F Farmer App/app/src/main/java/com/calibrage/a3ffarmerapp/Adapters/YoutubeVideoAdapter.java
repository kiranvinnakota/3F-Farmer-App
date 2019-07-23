package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.YoutubeVideoModel;
import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.holder.YoutubeViewHolder;
import com.calibrage.a3ffarmerapp.util.Constants;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;


import java.util.ArrayList;

import static com.calibrage.a3ffarmerapp.util.Constants.DEVELOPER_KEY;

/**
 * Created by sonu on 10/11/17.
 */

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList;


    public YoutubeVideoAdapter(Context context, ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList) {
        this.context = context;
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;
    }

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.youtube_video_custom_layout, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, final int position) {

        final YoutubeVideoModel youtubeVideoModel = youtubeVideoModelArrayList.get(position);
        holder.videoTitle.setText(youtubeVideoModel.getTitle());
       // holder.videoTitle.setText(youtubeVideoModel.getTitle());
//        holder.videoDuration.setText(youtubeVideoModel.getDuration());

        holder.videoThumbnailImageView.initialize(DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(youtubeVideoModel.getVideoId());
                //here is the magic to solve the logcat error
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailView.setVisibility(View.VISIBLE);
                      //  holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
        /*  initialize the thumbnail image view , we need to pass Developer Key */
      /*  holder.videoThumbnailImageView.initialize(DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(youtubeVideoModel.getVideoId());

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure");

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return youtubeVideoModelArrayList != null ? youtubeVideoModelArrayList.size() : 0;
    }
}
