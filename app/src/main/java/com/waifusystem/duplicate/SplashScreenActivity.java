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
        //todo this is fine
        preferences = getSharedPreferences(PREFS_NAME, 0);
        if (preferences.contains("first")) {
            //todo add ScanMapAct here
            Toast.makeText(getApplicationContext(), "it's disguised toast here", Toast.LENGTH_SHORT).show();

        } else {
            //todo guide
            Toast.makeText(getApplicationContext(), "what's up guys it's moe here", Toast.LENGTH_SHORT).show();
            editor = getSharedPreferences(PREFS_NAME, 0).edit();
            editor.putBoolean("first", false);

            editor.apply();
        }

        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
        finish();
    }


}
