package com.calibrage.a3ffarmerapp.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.calibrage.a3ffarmerapp.Adapters.GetRecommendationsAdapter;
import com.calibrage.a3ffarmerapp.Model.GetRecommendationsModel;
import com.calibrage.a3ffarmerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;

public class GetRecommendationsActivity extends AppCompatActivity {
    public static  String TAG="GetRecommendationsActivity";
    private List<GetRecommendationsModel> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_recommendations);
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
        GetRecommendations();
    }
    private void GetRecommendations() {

        String url = BASE_URL+"Recommendations";
        //  String url="http://183.82.103.171:9096/API/api/GetActiveLookUp/9";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE Recommendations======" + response);
                try {
                    JSONArray alsoKnownAsArray = new JSONArray(response);
                    Log.d(TAG, "RESPONSE Recommendations1======" + alsoKnownAsArray);
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


    private void parseData(JSONArray array){
        for(int i = 0; i<array.length(); i++) {

            GetRecommendationsModel superHero = new GetRecommendationsModel();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                superHero.setFertilizer(json.getString("fertilizer"));
                superHero.setUoM(json.getString("uoM"));
                  superHero.setYear1(json.getString("year1"));
                 superHero.setYear17Above(json.getString("year17Above"));
                superHero.setYear2(json.getString("year2"));
                superHero.setYear3(json.getString("year3"));
                superHero.setYear4And5(json.getString("year4And5"));
                superHero.setYear6And7(json.getString("year6And7"));
                superHero.setYear8To17(json.getString("year8To17"));
                superHero.setRemarks(json.getString("remarks"));
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
}
