package com.example.todoapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapplication.R;

/* ------------------------------------------------------------- *
 * Activity is used for performing as welcome Screen which goes
 * after 2500 mill sec
 * ------------------------------------------------------------- */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /* ------------------------------------------------------------- *
         * Responsible for the closing the activity after 2500 mill sec.
         * ------------------------------------------------------------- */
        new Handler().postDelayed(this::openActivity, 2500);
    }

    /* ------------------------------------------------------------- *
     * Function is used for opening the new activity after time delay.
     * ------------------------------------------------------------- */
    private void openActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}