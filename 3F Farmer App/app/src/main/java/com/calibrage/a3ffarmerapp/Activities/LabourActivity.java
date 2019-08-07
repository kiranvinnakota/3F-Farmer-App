package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

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
import com.calibrage.a3ffarmerapp.Adapters.SpinnerAdapter;
import com.calibrage.a3ffarmerapp.Model.StateVO;
import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.integration.android.IntentIntegrator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import fr.ganfra.materialspinner.MaterialSpinner;

import static com.calibrage.a3ffarmerapp.util.UrlConstants.BASE_URL;


public class LabourActivity extends AppCompatActivity implements OnItemSelectedListener {
   Calendar myCalendar;
    EditText edittext;
    EditText chooseTime,ageTxt,villageTxt;
    List<String> categories;
    TextInputEditText age,village;
    final String[] select_labour_type = {
             "hired labour","farm labour "};
    List<String> list = new ArrayList<String>();
    Spinner spin;
    private ProgressDialog dialog;
    public static  String TAG="LabourActivity";
    ArrayList<String> listdata =new ArrayList<String>();
    MultiSelectionSpinner multiSelectionSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour);
        dialog = new ProgressDialog(this);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent =new Intent(getApplicationContext(),LabourRecommendationsActivity.class);
              startActivity(intent);
            }
        });
        myCalendar = Calendar.getInstance();
      /*  String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        categories = new ArrayList<String>();
        categories.add("APWGT13718234003");
        categories.add("APWGT13718234004");
        categories.add("APWGT13718234005");
        categories.add("APWGT13718234006");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      Spinner  spinner1 = (MaterialSpinner) findViewById(R.id.spinner);
        spinner1.setAdapter(adapter);*/

        multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner_labour_type);

        spin = (Spinner) findViewById(R.id.spinner_new);
        spin.setOnItemSelectedListener(this);
    //    spinner.setAdapter(arrayAdapter);
      /*  ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < select_labour_type.length; i++) {

            StateVO stateVO = new StateVO();
            stateVO.setTitle(select_labour_type[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }
        SpinnerAdapter myAdapter = new SpinnerAdapter(LabourActivity.this, 0,
                listVOs);
        spinner.setAdapter(myAdapter);*/


        Spinner frequencySpinner = (Spinner) findViewById(R.id.frequency);

        // Spinner click listener
        frequencySpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories3 = new ArrayList<String>();
        categories3.add("1 week");
        categories3.add("2 week");
        categories3.add("3 week");
        categories3.add("4 week");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        frequencySpinner.setAdapter(dataAdapter3);

        Spinner labourSpinner = (Spinner) findViewById(R.id.labour_duration);

        // Spinner click listener
        labourSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories2 = new ArrayList<String>();
        categories2.add("3 months");
        categories2.add("6 months");
        categories2.add("9 months");
        categories2.add("12 months");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        labourSpinner.setAdapter(dataAdapter2);

       /* ageTxt= (EditText) findViewById(R.id.ageEditText);
        villageTxt= (EditText) findViewById(R.id.villageEditText);*/
         edittext= (EditText) findViewById(R.id.date_display);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
/*edittext.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        new DatePickerDialog(LabourActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        return false;
    }
});*/
edittext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        new DatePickerDialog(LabourActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      //  return false;
    }
});

        /* chooseTime = findViewById(R.id.time);
         chooseTime.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {
                 TimePickerDialog timePickerDialog = new TimePickerDialog(LabourActivity.this, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                         chooseTime.setText(hourOfDay + ":" + minutes);
                     }
                 }, 0, 0, false);
                 timePickerDialog.show();
                 return false;
             }
         });*/
        Button buttonBarCodeScan = findViewById(R.id.buttonSubmit);


        buttonBarCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initiate scan with our custom scan activity
           //   finish();
                Toasty.success(getApplicationContext(), "Labour Request Submitted Successfully", Toast.LENGTH_LONG).show();
                LabourActivity.this.finish();


            }
        });
        GetRecommendations();
        GetSpinnerLabourType();
    }
    private void GetRecommendations() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        String url = BASE_URL+"Farmer/GetPlotDetailsByFarmerCode/"+Constants.FARMER_CODE;
        //  String url="http://183.82.103.171:9096/API/api/GetActiveLookUp/9";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE Recommendations NEW======" + response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
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


                    spin.setAdapter(new ArrayAdapter<String>(LabourActivity.this, android.R.layout.simple_spinner_dropdown_item, listdata));
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
    private void GetSpinnerLabourType() {
        dialog.setMessage("Loading, please wait....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        String url = BASE_URL+"TypeCdDmt/6";
        //  String url="http://183.82.103.171:9096/API/api/GetActiveLookUp/9";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE Recommendations NEW======" + response);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "RESPONSE Recommendations1======" + jsonObject);
                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    Log.i("LOG_RESPONSE alsoKnownAsArray", String.valueOf(alsoKnownAsArray.length()));
                    for(int i = 0; i<alsoKnownAsArray.length(); i++) {
                        JSONObject leagueData = alsoKnownAsArray.getJSONObject(i);
                        String desc = leagueData.getString("desc");
                        Log.d(TAG, "RESPONSE plotCode======" + desc);

                        list.add(desc);

                        //set items to spinner from list
                     //   multiSelectionSpinner.setItems(list);
//
                    }


                    multiSelectionSpinner.setAdapter(new ArrayAdapter<String>(LabourActivity.this, android.R.layout.simple_spinner_dropdown_item, list));
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
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
       /* ageTxt.setText("25");
        villageTxt.setText("Markook, Telangana");*/

        // Showing selected spinner item
      //  Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
      /*  ageTxt.setText("");
        villageTxt.setText("");*/
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}

