package com.waifusystem.duplicate;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QuestActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    Cursor cursor;

    ImageView imageView;
    String TAG = "setupSQL";
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        constraintLayout = findViewById(R.id.quest_layout);
        imageView = findViewById(R.id.quest_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

//        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
//        animationDrawable.setEnterFadeDuration(6000);
//        animationDrawable.setExitFadeDuration(6000);
//        animationDrawable.start();
    }

    private void setupSQL() {
        try {
            databaseHelper = new DatabaseHelper(this);
            db = databaseHelper.getReadableDatabase();
            cursor = db.query("PERSON",
                    new String[]{"_id", "POSITION", "CHECKED"},
                    "CHECKED = 0",
                    null, null, null, "POSITION ASC");

            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    int id = cursor.getInt(0) - 1;
                    this.id = id;

                    Log.d("tag", "setupSQL: " + id + " is " + cursor.getInt(2));
                    imageView.setImageResource(Profile.profiles[id].getItemImagePath());
                } else {
                    Log.d("", "setupSQL: hehe xd");
                }
            } else {
                Toast.makeText(getApplicationContext(), "it's over anakin", Toast.LENGTH_SHORT).show();
            }

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Uh oh something's wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupSQL();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void init() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning QR code");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //result.getContent == null when user don't allow camera
        if (result.getContents() != null) {
            if (Integer.parseInt(result.getContents()) == id) {
                //checked
                ContentValues personValue = new ContentValues();
                personValue.put("CHECKED", 1);
                db.update("PERSON", personValue, "_id = ?", new String[]{Integer.toString(id + 1)});

                //todo send the id
                String content = result.getContents();
                Intent intent1 = new Intent(this, ProfileAndAudioActivity.class);
                intent1.putExtra(ProfileAndAudioActivity.ID, Integer.parseInt(content));
                startActivity(intent1);
            } else {
                Snackbar.make(findViewById(R.id.quest_layout), R.string.scan_failed, Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "please allow us to use camera", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }
}
