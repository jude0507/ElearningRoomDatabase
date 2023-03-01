package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.elearningapplication.Quarter.QuarterOne;
import com.example.elearningapplication.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreference = getSharedPreferences(Login.PREFS_Name, 0);
                boolean hasLoggedIn = sharedPreference.getBoolean("hasLoggedIn", false);
                if (hasLoggedIn){
                    startActivity(new Intent(SplashScreen.this, QuarterOne.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                }
            }
        },5000);

    }
}