package com.calibrage.a3ffarmerapp.Activities;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public String mJSONURLString = "http://183.82.111.111/3FFarmerAPI/api/Farmer/";
    TextInputEditText farmerId;
    public static  String TAG="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        //Scan Button
        Typeface faceBold = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Bold.ttf");
        Typeface faceRegular = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
        farmerId = findViewById(R.id.farmer_id_edittxt);
        farmerId.setTypeface(faceRegular);
        TextView or = findViewById(R.id.or_txt);
        or.setTypeface(faceRegular);
        Button buttonBarCodeScan = findViewById(R.id.buttonScan);
        buttonBarCodeScan.setTypeface(faceBold);

        buttonBarCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initiate scan with our custom scan activity
                new IntentIntegrator(LoginActivity.this).setCaptureActivity(ScannerActivity.class).initiateScan();
            }
        });

       Button loginBtn=(Button)findViewById(R.id.login_btn);
        loginBtn.setTypeface(faceBold);
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              /* Intent intent =new Intent(getApplicationContext(),OtpActivity.class);
               startActivity(intent);*/

               Getstate();
           }
       });

    }

//    public void version_api() {
//        String id=farmerId.getText().toString();
//        String versionURL = mJSONURLString+id;
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
private void Getstate() {
  //  String id="APWGBDAB00010001";

    String Id=farmerId.getText().toString();
    if (Id.matches("")) {
        Toast.makeText(getApplicationContext(), "Please enter Farmer Id", Toast.LENGTH_SHORT).show();
        return;
    }
    String url ="http://183.82.111.111/3FFarmerAPI/api/Farmer/"+Id;

    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(TAG,"RESPONSE======"+ response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.d(TAG,"RESPONSE======"+ jsonObject);
                String success=jsonObject.getString("isSuccess");
                Log.d(TAG,"success======"+ success);
              if (success.equals("true")){
                  Intent intent =new Intent(getApplicationContext(),OtpActivity.class);
                  intent.putExtra ( "Farmer id", farmerId.getText().toString() );
                  startActivity(intent);
               //   Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
              }else{
                  Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
              }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
    int socketTimeout = 30000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    stringRequest.setRetryPolicy(policy);
    requestQueue.add(stringRequest);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //show dialogue with result
                showResultDialogue(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //method to construct dialogue with scan results
    public void showResultDialogue(final String result) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Scan Result")
                .setMessage("Scanned result is " + result)
                .setPositiveButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Scan Result", result);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(LoginActivity.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .show();
    }
}