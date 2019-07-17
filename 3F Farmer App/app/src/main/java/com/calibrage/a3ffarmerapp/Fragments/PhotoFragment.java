package com.calibrage.a3ffarmerapp.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
import com.calibrage.a3ffarmerapp.Activities.PdfViewerActivity;
import com.calibrage.a3ffarmerapp.Adapters.FileAdapter;
import com.calibrage.a3ffarmerapp.Model.FileBean;
import com.calibrage.a3ffarmerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {

    ListView listView;
    String path;
    ArrayList<FileBean> list;
    FileAdapter adapter;
    public static  String TAG="PhotoFragment";
    private String embedUrl,idString,category,fileUrl,fileName;
    String[] strArray,categoryArray,fileUrlArray;
    private static final int REQUEST_PERMISSIONS = 101;
    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
       // setTitle("Pdf Reader");
        //Check if permission is granted(for Marshmallow and higher versions)
        if (Build.VERSION.SDK_INT >= 23)
            checkPermission();
        else
            initViews();
        listView = (ListView)view.findViewById(R.id.listView);
        list = new ArrayList<>();

        //get the absolute path of phone storage
       /* path = Environment.getExternalStorageDirectory().getAbsolutePath();

        //calling the initList that will initialize the list to be given to Adapter for binding data
        initList(path);*/


        adapter = new FileAdapter(getContext(), R.layout.list_item, list);

        //set the adapter on listView
        listView.setAdapter(adapter);

        //when user chooses a particular pdf file from list,
        //start another activity that will show the pdf
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), PdfViewerActivity.class);
                intent.putExtra("keyName", list.get(position).getFileName());
                intent.putExtra("keyPath",list.get(position).getFilePath());
                startActivity(intent);
            }
        });
        getEncyclopedia();
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
                       // Log.v("TAG --fileName ", fileName);

                        /*if (fileType.equals("Video")){

                            Log.v("kiran", embedUrl);
                            if (embedUrl.equals("null")) {
                                Log.v("TAG --fileUrl ", fileUrl);

                            }else {
                                idString=embedUrl.substring(32);
                                strArray=new String[] {idString};
                                //
                                categoryArray=new String[]{category};

                                Log.v("TAG --govindha ", embedUrl);}


                        }*/
                        if (fileType.equals("PDF")){
                            if (embedUrl.equals("null")) {
                                Log.v("TAG --fileUrl ", fileType);
                                Log.v("TAG --fileUrl ", fileUrl);
                                Log.v("TAG --fileName ", fileName);
                           //     path = Environment.getExternalStorageDirectory().getAbsolutePath();

                                //calling the initList that will initialize the list to be given to Adapter for binding data
                                initList(fileName,fileUrl);
                            }
                        }

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
    void initViews(){
        //views initialization


    }

    //initializing the ArrayList
    void initList(String fileName,String fileUrl){
        try{
            File file = new File(path);
            File[] fileArr = file.listFiles();


            list.add(new FileBean(fileName, fileUrl));
         /*   for(File file1 : fileArr){
                if(file1.isDirectory()){
                   // initList(file1.getAbsolutePath());
                }else{
                    fileName = file1.getName();
                    //choose only the pdf files
                    if(fileName.endsWith(".pdf")){
                        list.add(new FileBean(fileName, fileUrl));
                    }
                }

            }*/
        }catch(Exception e){
            Log.i("show","Something went wrong. "+e.toString());
        }
    }
    void checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //if permission granted, initialize the views
            initViews();
        } else {
            //show the dialog requesting to grant permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initViews();
                } else {
                    //permission is denied (this is the first time, when "never ask again" is not checked)
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        getActivity().finish();
                    }
                    //permission is denied (and never ask again is  checked)
                    else {
                        //shows the dialog describing the importance of permission, so that user should grant
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("You have forcefully denied Read storage permission.\n\nThis is necessary for the working of app." + "\n\n" + "Click on 'Grant' to grant permission")
                                //This will open app information where user can manually grant requested permission
                                .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        getActivity().finish();
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", getActivity().getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                })
                                //close the app
                                .setNegativeButton("Don't", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        getActivity().finish();
                                    }
                                });
                        builder.setCancelable(false);
                        builder.create().show();
                    }
                }
        }
    }
}

