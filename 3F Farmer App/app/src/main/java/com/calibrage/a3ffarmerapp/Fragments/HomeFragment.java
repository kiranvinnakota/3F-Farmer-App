package com.calibrage.a3ffarmerapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.calibrage.a3ffarmerapp.Activities.CollectionsActivity;
import com.calibrage.a3ffarmerapp.Activities.EncyclopediaActivity;
import com.calibrage.a3ffarmerapp.Activities.FertilizerActivity;
import com.calibrage.a3ffarmerapp.Activities.LabourActivity;
import com.calibrage.a3ffarmerapp.Activities.LabourRecommendationsActivity;
import com.calibrage.a3ffarmerapp.Activities.LoanActivity;
import com.calibrage.a3ffarmerapp.Activities.OtpActivity;
import com.calibrage.a3ffarmerapp.Activities.PaymentActivity;
import com.calibrage.a3ffarmerapp.Activities.PoleActivity;
import com.calibrage.a3ffarmerapp.Activities.QuickPayActivity;
import com.calibrage.a3ffarmerapp.Activities.RecommendationActivity;
import com.calibrage.a3ffarmerapp.Activities.RequestVisitActivity;
import com.calibrage.a3ffarmerapp.Activities.VisitActivity;
import com.calibrage.a3ffarmerapp.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        view.findViewById(R.id.collections_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), CollectionsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.recommendations_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.visit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), RequestVisitActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.loan_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), LoanActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.labour_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), LabourRecommendationsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.pole_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), PoleActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.fertilizer_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), FertilizerActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.payments_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.quickPay_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), QuickPayActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.knowledge_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), EncyclopediaActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.collection_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), EncyclopediaActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.fertilizer_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), EncyclopediaActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add("Menu item")
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Krishna")
                .setActionView(R.layout.toolbar)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

}
