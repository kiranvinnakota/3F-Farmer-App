package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.CommonUtil;

import static com.calibrage.a3ffarmerapp.util.CommonUtil.updateResources;

public class LanguageActivity extends AppCompatActivity {
    private String mLanguageCode = "te";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        final TextView rbEng = findViewById(R.id.english);
        final TextView rbTelugu = findViewById(R.id.telugu);

        rbEng.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                /**
                 * "en" is the localization code for our default English language.
                 */
                rbEng.setBackgroundColor(Color.rgb(60,180,110));
                updateResources(LanguageActivity.this, "en-US");
                SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 1);
                Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(refresh);
                finish();

            }
        });

/**
 * @param OnClickListner
 */
        rbTelugu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */

                rbTelugu.setBackgroundColor(Color.rgb(60,180,110));
               updateResources(LanguageActivity.this, "te");
                SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 2);
                Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(refresh);
                finish();

              /*  LocaleHelper.setLocale(LanguageActivity.this, mLanguageCode);

                //It is required to recreate the activity to reflect the change in UI.
                recreate();*/

            }
        });

    }

    public void onBackPressed() {
        //  super.onBackPressed();
        moveTaskToBack(true);

    }
}
