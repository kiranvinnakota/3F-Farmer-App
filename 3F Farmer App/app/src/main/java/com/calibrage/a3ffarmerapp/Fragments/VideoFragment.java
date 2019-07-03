package com.calibrage.a3ffarmerapp.Fragments;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Adapters.YoutubeRecyclerAdapter;
import com.calibrage.a3ffarmerapp.Model.YoutubeVideo;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment {
    public static final String API_KEY = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";

    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public static final String VIDEO_ID = "-m3V8w_7vhk";

    RecyclerView recyclerView;
    Vector<YoutubeVideo> youtubeVideos = new Vector<YoutubeVideo>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);


        VideoView videoview = (VideoView)view.findViewById(R.id.VideoView);

        String path = "android.resource://com.calibrage.a3ffarmerapp/"+R.raw.oilvideo;

        Uri u = Uri.parse(path);

        videoview.setVideoURI(u);
        videoview.start();
        return view;
    }
}