package com.calibrage.a3ffarmerapp.Activities;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.calibrage.a3ffarmerapp.Adapters.GetRecommendationsAdapter;
import com.calibrage.a3ffarmerapp.Model.GetRecommendationsModel;
import com.calibrage.a3ffarmerapp.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;

public class GetRecommendationsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static  String TAG="GetRecommendationsActivity";
    private List<GetRecommendationsModel> listSuperHeroes;
    EditText fromText,toText;
    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    ArrayList<String> listdata =new ArrayList<String>();
    Spinner spin;
    DatePickerDialog picker;
    String fromString,toString;
    public static String plotSelection;
    String reformattedStrFrom,reformattedStrTo;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recommendations);
        text=(TextView)findViewById(R.id.text);
       recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
        //Initializing our superheroes list
       listSuperHeroes = new ArrayList<>();
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        fromText=(EditText) findViewById(R.id.from_date);
        fromText.setInputType(InputType.TYPE_NULL);
        fromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(GetRecommendationsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });
        toText=(EditText) findViewById(R.id.to_date);
        toText.setInputType(InputType.TYPE_NULL);
        toText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(GetRecommendationsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                toText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });
        Button buttonSubmit=(Button)findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listSuperHeroes.clear();
                fromString = fromText.getText().toString().trim();
                toString = toText.getText().toString().trim();

                if(fromString.equalsIgnoreCase("")||toString.equalsIgnoreCase(""))
                {
                    Toasty.error(GetRecommendationsActivity.this, "Please Enter From/To Date", Toast.LENGTH_SHORT).show();
                    /*timePeroidLinear.setVisibility(View.VISIBLE); //
                    recyclerView.setVisibility(View.GONE);*/
                }
                else
                {
                    try {
                        getCustomRecommendations(fromString,toString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // Toast.makeText(CollectionsActivity.this, "kiran", Toast.LENGTH_SHORT).show();
                  //  timePeroidLinear.setVisibility(View.GONE);
                    //     text.setVisibility(View.VISIBLE);

                }
/*
                        if (fromString.length() <= 0) {
                            Toast.makeText(CollectionsActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                        }
                        if (toString.length() <= 0) {
                            Toast.makeText(CollectionsActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                        }
                      getCollections();*/

               /* fromText.setText(null);
                toText.setText(null);*/
            }

        });
        GetRecommendations();

    }
    private void GetRecommendations() {

        String url = BASE_URL+"Farmer/GetPlotDetailsByFarmerCode/APWGBDAB00010001";
        //  String url="http://183.82.103.171:9096/API/api/GetActiveLookUp/9";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE Recommendations NEW======" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "RESPONSE Recommendations1======" + jsonObject);
                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    Log.i("LOG_RESPONSE alsoKnownAsArray", String.valueOf(alsoKnownAsArray.length()));
                    for(int i = 0; i<alsoKnownAsArray.length(); i++) {
                        JSONObject leagueData = alsoKnownAsArray.getJSONObject(i);
                        String plotCode = leagueData.getString("code");
                        Log.d(TAG, "RESPONSE plotCode======" + plotCode);

                        listdata.add(plotCode);
//
                    }


                    spin.setAdapter(new ArrayAdapter<String>(GetRecommendationsActivity.this, android.R.layout.simple_spinner_dropdown_item, listdata));
                  /*  String success = jsonObject.getString("isSuccess");
                    Log.d(TAG, "success Recommendations======" + success);*/


                   /* JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    parseData(alsoKnownAsArray);*/
                    //parseData(alsoKnownAsArray);
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
                    Toasty.error(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "AuthFailure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "NoConnection Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());
                    Toasty.error(getApplicationContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
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
    private void getCustomRecommendations(final String fromString, final String toString) throws ParseException {
        listSuperHeroes.clear();
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            reformattedStrFrom = myFormat.format(fromUser.parse(fromString));
            reformattedStrTo = myFormat.format(fromUser.parse(toString));
            Log.d("collection", "RESPONSE reformattedStr======" + reformattedStrFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String URL = "http://183.82.111.111/3FFarmerAPI/api/Recommendations/GetRecommendationsByPlotCode";


        RequestQueue queue= Volley.newRequestQueue(this);


        Map<String, String> jsonParams = new HashMap<String, String>();
       /* jsonParams.put( "plotCode","APAB0001000001");
        jsonParams.put( "fromDate","2016-01-01T10:10:52.5116115+05:30");
        jsonParams.put( "toDate","2019-08-01T10:10:52.5116115+05:30");*/
        jsonParams.put( "plotCode",plotSelection);
        jsonParams.put( "fromDate",reformattedStrFrom);
        jsonParams.put( "toDate",reformattedStrTo);

        Log.d(TAG,"Json==slot:"+ new JSONObject(jsonParams));

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, URL,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String result = response.toString();

                        Log.d("result=====",result);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(result);
                            Log.d(TAG,"RESPONSE result======"+ jsonObject);
                             String affectedRecords=jsonObject.getString("affectedRecords");
                            Log.d(TAG,"RESPONSE affectedRecords======"+ affectedRecords);
                            if(affectedRecords.contains("0") ){
                                recyclerView.setVisibility(View.GONE);
                                text.setVisibility(View.VISIBLE);

                            }else{
                                recyclerView.setVisibility(View.VISIBLE);
                                text.setVisibility(View.GONE);
                            }
                            JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                            Log.d(TAG,"RESPONSE result======"+ alsoKnownAsArray);

                            parseData(alsoKnownAsArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onResponse(String response) {
//                //This code is executed if the server responds, whether or not the response contains data.
//                //The String 'response' contains the server's response.
//                Log.i("LOG_RESPONSE ", response);
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d(TAG, "RESPONSE PLOT======" + jsonObject);
//                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
//                    Log.i("LOG_RESPONSE PLOT", String.valueOf(alsoKnownAsArray.length()));
//                    Log.d("data2===", "APAB0001000001");
//                 //   parseData(jsonObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }



            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
// Handle Error

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
// headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }



        };

        queue.add(postRequest);

    }

    private void parseData(JSONArray array){
        for(int i = 0; i<array.length(); i++) {

            GetRecommendationsModel superHero = new GetRecommendationsModel();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                superHero.setFertilizer(json.getString("plotCode"));
                superHero.setUoM(json.getString("recommendedFertilizerName"));
                  superHero.setYear1(json.getString("dosage"));
                 superHero.setYear17Above(json.getString("uom"));
                superHero.setYear2(json.getString("comments"));
                superHero.setYear3(json.getString("issuIdentified"));
                superHero.setYear4And5(json.getString("recommendedBy"));
                superHero.setYear6And7(json.getString("recommendedOn"));
           //     superHero.setYear8To17(json.getString("plotSize"));
            //    superHero.setRemarks(json.getString("location"));
                ArrayList<String> powers = new ArrayList<String>();

                //  JSONArray jsonArray = json.getJSONArray(Config.TAG_POWERS);

//                for(int j = 0; j<jsonArray.length(); j++){
//                    powers.add(((String) jsonArray.get(j))+"\n");
//                }
                superHero.setPowers(powers);
                //   System.out.println("kiran"+superHero);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(superHero);
        }

        //Finally initializing our adapter
        adapter = new GetRecommendationsAdapter(listSuperHeroes, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        plotSelection = spin.getItemAtPosition(spin.getSelectedItemPosition()).toString();
        Log.d("data===", plotSelection);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
