package com.calibrage.a3ffarmerapp.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import com.calibrage.a3ffarmerapp.Adapters.*;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.calibrage.a3ffarmerapp.Fragments.HomeFragment;
import com.calibrage.a3ffarmerapp.Fragments.MyRequestsFragment;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;
import java.util.Arrays;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;


public class SideMenuActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {
    public MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;

    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));

        // Initialize the views
        mViewHolder = new ViewHolder();

        // Handle toolbar actions
        handleToolbar();

        // Handle menu actions
        handleMenu();

        // Handle drawer actions
        handleDrawer();

        // Show main fragment in container
        goToFragment(new HomeFragment(), false);
        mMenuAdapter.setViewSelected(0, true);
        setTitle(mTitles.get(0));
    }

    private void handleToolbar() {
        setSupportActionBar(mViewHolder.mToolbar);
    }

    private void handleDrawer() {
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mViewHolder.mDuoDrawerLayout,
                mViewHolder.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mViewHolder.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();
        final ActionBar ab = getSupportActionBar();
        /* to set the menu icon image*/
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_btn);
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @SuppressLint("RestrictedApi")
    private void handleMenu() {
        mMenuAdapter = new MenuAdapter(mTitles);

        mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }

    @Override
    public void onFooterClicked() {
       // Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getApplicationContext(),LanguageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onHeaderClicked() {
        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show();
    }

    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.add(R.id.container, fragment).commit();
    }
    public void onBackPressed() {
        //  super.onBackPressed();
        moveTaskToBack(true);

    }
    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));

        // Set the right options selected
        mMenuAdapter.setViewSelected(position, true);

        // Navigate to the right fragment
        switch (position) {

                case 0:

                    goToFragment(new HomeFragment(), false);

                    break;
                case 1:

                    goToFragment(new MyRequestsFragment(), false);
                    break;
            case 2:

              Intent intent =new Intent(getApplicationContext(),InsideLanguageActivity.class);
              startActivity(intent);
                break;
                default:
              //  goToFragment(new HomeFragment(), false);

                break;
        }

        // Close the drawer
        mViewHolder.mDuoDrawerLayout.closeDrawer();
    }

    private class ViewHolder {
        private DuoDrawerLayout mDuoDrawerLayout;
        private DuoMenuView mDuoMenuView;
        private Toolbar mToolbar;

        ViewHolder() {
            mDuoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
            mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();
            mToolbar = (Toolbar) findViewById(R.id.toolbar);

            View header = mDuoMenuView.getHeaderView();
            Typeface faceRegular = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
            TextView textUsername = header.findViewById(R.id.duo_view_header_text_title);
            textUsername.setTypeface(faceRegular);
            textUsername.setText(R.string.name);
            TextView textrole = header.findViewById(R.id.duo_view_header_text_sub_title);
            textrole.setText("");

            View footer = mDuoMenuView.getFooterView();
            TextView logout = footer.findViewById(R.id.duo_view_footer_text);
            logout.setTypeface(faceRegular);
            logout.setText(R.string.log_off);
            logout.setTextColor(getResources().getColor(R.color.white));
            logout.setBackgroundColor(getResources().getColor(R.color.light_red));
            logout.setHeight(10);
        }
    }
}
