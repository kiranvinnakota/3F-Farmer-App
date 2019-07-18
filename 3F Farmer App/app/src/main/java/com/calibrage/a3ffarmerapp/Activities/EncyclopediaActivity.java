package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.calibrage.a3ffarmerapp.Adapters.PagerAdapter;
import com.calibrage.a3ffarmerapp.Fragments.AudioFragment;
import com.calibrage.a3ffarmerapp.Fragments.PhotoFragment;
import com.calibrage.a3ffarmerapp.Fragments.VideoFragment;
import com.calibrage.a3ffarmerapp.R;
import com.google.android.material.tabs.TabLayout;


import android.net.Uri;

import android.view.Gravity;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class EncyclopediaActivity extends AppCompatActivity {

    //create class reference
    VideoView vid;
    //  Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        /*vid = (VideoView)findViewById(R.id.videoView);
        vid.setBackgroundResource(R.drawable.play_2);*/
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
       /* toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);*/
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFrag(new VideoFragment(),getString(R.string.videos));
    //   pagerAdapter.addFrag(new AudioFragment(), "Audios");
        pagerAdapter.addFrag(new PhotoFragment(),getString(R.string.doc));
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        DisplayActionBar();

    }

    private void DisplayActionBar() {
        final ActionBar abar = getSupportActionBar();
        abar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2980B9")));
        // abar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));//line under the action bar
        View viewActionBar = getLayoutInflater().inflate(R.layout.toolbar_all, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.custom_action_bar_title);
        textviewTitle.setText(R.string.knowledge);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }
  /*  public void playVideo(View v) {
        MediaController m = new MediaController(this);
        vid.setMediaController(m);

        String path = "android.resource://com.calibrage.a3ffarmerapp/"+R.raw.oilvideo;

        Uri u = Uri.parse(path);

        vid.setVideoURI(u);

        vid.start();
        vid.setBackgroundResource(0);




    }*/
}