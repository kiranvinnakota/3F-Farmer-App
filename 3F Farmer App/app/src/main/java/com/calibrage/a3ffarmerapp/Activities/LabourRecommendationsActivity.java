package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.Adapters.LabourRecommendationAdapter;
import com.calibrage.a3ffarmerapp.Adapters.RecommendationAdapter;
import com.calibrage.a3ffarmerapp.Model.RecommendationModel;
import com.calibrage.a3ffarmerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LabourRecommendationsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_recommendations);
        ImageView backImg=(ImageView)findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SideMenuActivity.class);
                startActivity(intent);
            }
        });
        RecommendationModel[] myListData = new RecommendationModel[] {
                new RecommendationModel("Plot252019","2 hec ","Chinnakoduru","Near Shiavalayam"),
                new RecommendationModel("Plot242019","1 hec  ","Dundigal","Opposite govt school"),
                new RecommendationModel("Plot232019","3 hec ","Baswapur","Flowers market"),
                new RecommendationModel("Plot222019","4 hec","Medipally","Main road"),

        };

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LabourRecommendationAdapter adapter = new LabourRecommendationAdapter(this,myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floating_btn_add_labour);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),LabourActivity.class);
                startActivity(intent);
            }
        });
    }

}
