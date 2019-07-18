package com.calibrage.a3ffarmerapp.Fragments;


import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.Activities.YoutubePlayerActivity;
import com.calibrage.a3ffarmerapp.Adapters.VideoRecyclerAdapter;
import com.calibrage.a3ffarmerapp.Adapters.YoutubeVideoAdapter;
import com.calibrage.a3ffarmerapp.Model.VideoModel;
import com.calibrage.a3ffarmerapp.Model.YoutubeVideoModel;
import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.RecyclerViewOnClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class VideoFragment extends Fragment {
    private RecyclerView recyclerView;
    public static  String TAG="VideoFragment";
    private String embedUrl,idString,category,fileUrl,fileName;
    String[] strArray,categoryArray,fileUrlArray;
    private static final int REQUEST_PERMISSIONS = 101;
    ArrayList<VideoModel> mVideoList;
    VideoRecyclerAdapter mAdapter;
    RecyclerView mRecyclerView;
    GridLayoutManager recyclerViewLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView = view.findViewById(R.id.recyclerViewGallery);
        recyclerViewLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mVideoList = new ArrayList<>();
        getEncyclopedia();
        setUpRecyclerView();
       // checkPermission(fileUrl);
        return view;
    }
    private void getEncyclopedia() {
        //  String id="APWGBDAB00010001";

        String Id="1004";

        String url ="http://183.82.111.111/3FFarmerAPI/api/Encyclopedia/GetFilesByCategory/"+Id;

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,"RESPONSE======"+ response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG,"RESPONSE Encyclopedia======"+ jsonObject);

                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                        String alsoKnown = alsoKnownAsArray.getString(i);
                        JSONObject leagueData = alsoKnownAsArray.getJSONObject(i);
                        String fileType = leagueData.getString("fileType");
                         embedUrl = leagueData.getString("embedUrl");
                        category=leagueData.getString("category");
                        fileUrl=leagueData.getString("fileUrl");
                        fileName=leagueData.getString("fileName");
                        if (fileType.equals("Video")){

                                Log.v("kiran", embedUrl);
                             if (embedUrl.equals("null")) {
                                 Log.v("TAG --fileUrl ", fileUrl);
                             /*    checkPermission(fileUrl);
                                 file_download(fileUrl);*/

                            }else {
                                 idString=embedUrl.substring(32);
                                 strArray=new String[] {idString};
                              //
                                 categoryArray=new String[]{category};
                                 populateRecyclerView(strArray,categoryArray);
                                 Log.v("TAG --govindha ", embedUrl);}


                        }
                        Log.v("TAG --fileType", fileType);
                        Log.v("TAG --embedUrl", embedUrl);
                    }
                 //   Log.d(TAG,"RESPONSE Encyclopedia jsonArray======"+ jsonArray);


                    String success=jsonObject.getString("isSuccess");
                    Log.d(TAG,"success Encyclopedia======"+ success);
                    if (success.equals("true")){
                       /* Intent intent =new Intent(getContext(), OtpActivity.class);
                        intent.putExtra ( "Farmer id", farmerId.getText().toString() );
                        startActivity(intent);*/
                        //   Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (error instanceof NetworkError) {
                    Log.i("one:" + TAG, error.toString());
                    Toast.makeText(getContext(),"Network Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());
                    Toast.makeText(getContext(),"Server Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());
                    Toast.makeText(getContext(),"AuthFailure Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());
                    Toast.makeText(getContext(),"Parse Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());
                    Toast.makeText(getContext(),"NoConnection Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());
                    Toast.makeText(getContext(),"Timeout Error",Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Checking error in else");
                }
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
    public void file_download(String fileUrl) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/3f_form");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(fileUrl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Demo")
             //   .setDescription("Something useful. No, really.")
                .setDestinationInExternalPublicDir("/3f_form", "video");

        mgr.enqueue(request);

    }
    private void setUpRecyclerView() {

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void populateRecyclerView(String[] strArray, String[] categoryArray) {
        final ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList = generateDummyVideoList();
        YoutubeVideoAdapter adapter = new YoutubeVideoAdapter(getContext(), youtubeVideoModelArrayList);
        recyclerView.setAdapter(adapter);

        //set click event
        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(getContext(), new RecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //start youtube player activity by passing selected video id via intent
                startActivity(new Intent(getContext(), YoutubePlayerActivity.class)
                        .putExtra("video_id", youtubeVideoModelArrayList.get(position).getVideoId()));

            }
        }));
    }


    /**
     * method to generate dummy array list of videos
     *
     * @return
     */
    private ArrayList<YoutubeVideoModel> generateDummyVideoList() {
        ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList = new ArrayList<>();

        //get the video id array, title array and duration array from strings.xml
       // String[] videoIDArray = (String[]) getResources().getStringArray(Integer.parseInt(idString));
        String[] videoIDArray = strArray;
        String[] videoTitleArray = categoryArray;
       // String[] videoDurationArray = getResources().getStringArray(R.array.video_duration_array);

        //loop through all items and add them to arraylist
        for (int i = 0; i < videoIDArray.length; i++) {

            YoutubeVideoModel youtubeVideoModel = new YoutubeVideoModel();
            youtubeVideoModel.setVideoId(videoIDArray[i]);
             youtubeVideoModel.setTitle(videoTitleArray[i]);
         //   youtubeVideoModel.setDuration(videoDurationArray[i]);
            youtubeVideoModel.setVideoId(videoIDArray[i]);
            youtubeVideoModelArrayList.add(youtubeVideoModel);

        }

        return youtubeVideoModelArrayList;
    }
    private void checkPermission(String fileUrl) {
        if ((ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSIONS);
                }
            }
        } else {
            // all permission granted
            getAllVideoFromGallery();

        }
    }

    public void getAllVideoFromGallery() {
        Uri uri;
        Cursor mCursor;
        int COLUMN_INDEX_DATA, COLUMN_INDEX_NAME, COLUMN_ID, COLUMN_THUMB;
        String absolutePathOfFile = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME, MediaStore.Video.Media._ID, MediaStore.Video.Thumbnails.DATA};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        mCursor = getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
        COLUMN_INDEX_DATA = mCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        COLUMN_INDEX_NAME = mCursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        COLUMN_ID = mCursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        COLUMN_THUMB = mCursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);

        while (mCursor.moveToNext()) {
            absolutePathOfFile = mCursor.getString(COLUMN_INDEX_DATA);
            Log.e("Column", absolutePathOfFile);
            Log.e("Folder", mCursor.getString(COLUMN_INDEX_NAME));
            Log.e("column_id", mCursor.getString(COLUMN_ID));
            Log.e("thum", mCursor.getString(COLUMN_THUMB));
            VideoModel mVideo = new VideoModel();
            mVideo.setSelected(false);
            mVideo.setFilePath(absolutePathOfFile);
            mVideo.setVideoThumb(mCursor.getString(COLUMN_THUMB));
            mVideoList.add(mVideo);

        }

        mAdapter = new VideoRecyclerAdapter(mVideoList);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        getAllVideoFromGallery();
                    } else {
                        Toast.makeText(getContext(), "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}