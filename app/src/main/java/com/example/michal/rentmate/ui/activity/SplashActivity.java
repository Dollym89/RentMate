package com.example.michal.rentmate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.michal.rentmate.R;

/**
 * Created by Michal on 17/04/2016.
 */
public class SplashActivity extends AppCompatActivity {

private static int SPLASH_SCREEN_DELAY = 2000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
//        TODO set which acctivity to start based on sharedPreferences !!!!!
        Intent intent = new Intent(SplashActivity.this,LogInActivity.class);
        startActivity(intent);
        finish();
      }
    },SPLASH_SCREEN_DELAY);
  }
}
