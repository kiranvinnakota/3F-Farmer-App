package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.Adapters.RecommendationListAdapter;
import com.calibrage.a3ffarmerapp.Model.RecommendationListModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecommendationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFertlizer);

        RecommendationListAdapter adapter = new RecommendationListAdapter(getMovieList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
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
        textviewTitle.setText(R.string.recommendations);
/*        String header ="<b><font color='#1748DB'>" + getString(R.string.app_vzit) + "</font><b><font color='#32be16'>" + getString(R.string.app_doc) + "</font>";

        textviewTitle.setText(Html.fromHtml(header));*/

        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);

        abar.setDisplayHomeAsUpEnabled(true);

        abar.setHomeButtonEnabled(true);

        abar.show();

    }
    private List<RecommendationListModel> getMovieList() {
        List<RecommendationListModel> movieList = new ArrayList<>();
        // src Wikipedia
        movieList.add(new RecommendationListModel("PLOT00030", "Urea", "20 kgs", "comments","Pest","Rancher","20/05/2019"));
        movieList.add(new RecommendationListModel("PLOT0032", "Urea", "10 kgs", "comments","Disease","Roja","10/06/2019"));
        movieList.add(new RecommendationListModel("PLOT00033", "Urea", "30 kgs", "comments","Pest","Lakshmi","15/06/2019"));
        movieList.add(new RecommendationListModel("PLOT0034", "Urea", "25 kgs", "comments","Disease","Ramu","22/06/2019"));


        return movieList;
    }
}
