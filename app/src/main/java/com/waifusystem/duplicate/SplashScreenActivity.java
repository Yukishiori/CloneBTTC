package com.waifusystem.duplicate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPref";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(PREFS_NAME, 0);
        if (preferences.contains("first")) {
            Intent intent = new Intent(this, QuestActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, GuideActivity.class);
            editor = getSharedPreferences(PREFS_NAME, 0).edit();
            editor.putBoolean("first", false);
            editor.apply();
            startActivity(intent);

        }
        finish();
    }


}
