package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.Adapters.FertilizerAdapter;
import com.calibrage.a3ffarmerapp.Adapters.PaymentHistoryAdapter;
import com.calibrage.a3ffarmerapp.Adapters.TableViewAdapter;
import com.calibrage.a3ffarmerapp.Model.FertilizerModel;
import com.calibrage.a3ffarmerapp.Model.MovieModal;
import com.calibrage.a3ffarmerapp.Model.PaymentHistoryModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PaymentHistoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Animation animationUp, animationDown;
    private RecyclerView recyclerView;
    String[] country = { "Last 15 days", "Last 30 days", "Full Financial year", "Since April 2017", "Custom Time Period"};
    Spinner spin;
    EditText fromText,toText;
    DatePickerDialog picker;
    LinearLayout timePeroidLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        PaymentHistoryModel[] myListData = new PaymentHistoryModel[] {


                new     PaymentHistoryModel( "15-Dec-18", "24768", "34587964258","Ravi teja","SBI"),
                new   PaymentHistoryModel( "5-Dec-18", "15463", "58967412568","Roja","Andhra Bank"),
                new    PaymentHistoryModel( "14-Nov-18", "17680", "48561245698","Chaitnya","SBI"),


        };
        timePeroidLinear=(LinearLayout)findViewById(R.id.linear2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        PaymentHistoryAdapter recyclerViewAdapter = new PaymentHistoryAdapter(this,myListData, animationUp, animationDown);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        fromText=(EditText) findViewById(R.id.from_date);
        fromText.setInputType(InputType.TYPE_NULL);
        fromText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PaymentHistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                return false;
            }
        });

    /*    subBtn=(Button)findViewById(R.id.buttonScan);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        toText=(EditText) findViewById(R.id.to_date);
        toText.setInputType(InputType.TYPE_NULL);
        toText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PaymentHistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                toText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                return false;
            }
        });

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
        textviewTitle.setText(R.string.view_pay);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String Text = String.valueOf(spin.getSelectedItem());
        //    subBtn.setVisibility(View.VISIBLE);
        // Toast.makeText(getApplicationContext(),Text , Toast.LENGTH_LONG).show();
        if(spin.getSelectedItem().toString().equals("Custom Time Period")){
            // Toast.makeText(getApplicationContext(),"hiddd" , Toast.LENGTH_LONG).show();
            timePeroidLinear.setVisibility(View.VISIBLE); //
            //  subBtn.setVisibility(View.VISIBLE);

//do something
        }else {
            timePeroidLinear.setVisibility(View.GONE);
            //   subBtn.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        spin.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

}
