package com.calibrage.a3ffarmerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.util.CommonUtil;

import static com.calibrage.a3ffarmerapp.util.CommonUtil.updateResources;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
    }
    private void selectLanguage() {

        // set the custom forgotPasswordDialog components - text, image and button
        final TextView rbEng = findViewById(R.id.english);
        final TextView rbTelugu = findViewById(R.id.telugu);


/**
 * @param OnClickListner
 */
        rbEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "en" is the localization code for our default English language.
                 */
                updateResources(LanguageActivity.this, "en-US");
           //     SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 1);
                Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(refresh);
                finish();

            }
        });

/**
 * @param OnClickListner
 */
        rbTelugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */
                updateResources(LanguageActivity.this, "te");
              //  SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 2);
                Intent refresh = new Intent(getApplicationContext(), LanguageActivity.class);
                startActivity(refresh);
                finish();

            }
        });



    }

}
