package com.waifusystem.duplicate;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QuestActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button nextPersonButton;
    Button scanButton;
    ImageButton drawerButton;



    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    Cursor cursor;

    private Fragment fragment;

    String TAG = "setupSQL";
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);


        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        nextPersonButton = findViewById(R.id.next_character);
        scanButton = findViewById(R.id.scan_button);
        drawerButton = findViewById(R.id.btn_drawer);
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }


    private void setupSQL() {
        try {
            databaseHelper = new DatabaseHelper(this);
            db = databaseHelper.getReadableDatabase();
            cursor = db.query("PERSON",
                    new String[]{"_id", "POSITION", "NOWCHECKING", "CHECKED"},
                    "CHECKED = 0",
                    null, null, null, "POSITION ASC");

            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    this.id = cursor.getInt(0) - 1;
                    ImageAndDescriptionFragment imageAndDescriptionFragment = new ImageAndDescriptionFragment();
                    imageAndDescriptionFragment.setId(this.id);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, imageAndDescriptionFragment);
                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.commit();

                    if (cursor.getInt(2) == 1) {
                        setupHiddenButtonAndToProfileScanButton();
                    } else {
                        setupScanButton();
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "it's over anakin", Toast.LENGTH_SHORT).show();
                setupScanButton();
            }

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Uhhh something's wrong", Toast.LENGTH_SHORT).show();
        }
        scanButton.setVisibility(View.VISIBLE);
    }

    private void setupHiddenButtonAndToProfileScanButton() {
        nextPersonButton.setVisibility(View.VISIBLE);
        nextPersonButton.setActivated(true);
        scanButton.setText("View Profile");
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(QuestActivity.this, ProfileAndAudioActivity.class);
                intent1.putExtra(ProfileAndAudioActivity.ID, id);
                startActivity(intent1);
            }
        });
        nextPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("CHECKED", 1);
                db.update("PERSON", contentValues, "_id = ?", new String[]{Integer.toString(id + 1)});
                setupSQL();
                nextPersonButton.setVisibility(View.GONE);
            }
        });
    }

    private void setupScanButton() {
        scanButton.setText("SCAN RIGHT MEOW~");
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setupSQL();
    }

    private void init() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning QR code");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(false);
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
                personValue.put("NOWCHECKING", 1);
                db.update("PERSON", personValue, "_id = ?", new String[]{Integer.toString(id + 1)});

                //todo send the id
                String content = result.getContents();
                Intent intent1 = new Intent(this, ProfileAndAudioActivity.class);
                intent1.putExtra(ProfileAndAudioActivity.ID, Integer.parseInt(content));
                startActivity(intent1);
                finish();
            } else {
                Snackbar.make(findViewById(R.id.drawer_layout), R.string.scan_failed, Snackbar.LENGTH_SHORT).show();

            }

        } else {
//            Toast.makeText(this, "please allow us to use your camera", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FrameLayout frameLayout = findViewById(R.id.parent_container);
        InfoFragment infoFragment = new InfoFragment();
        switch (itemId) {
            case R.id.nav_scan:
                setupSQL();
                frameLayout.setBackgroundResource(android.R.color.transparent);
                Fragment fragment = new QuestFragment();
                fragmentTransaction.replace(R.id.parent_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.nav_banToChuc:
                hideTheMemes();
                frameLayout.setBackgroundResource(R.mipmap.background_test);
                setupInfoFragmentTransaction(fragmentTransaction, 0, infoFragment);
                break;
            case R.id.nav_moHinh:
                hideTheMemes();
                frameLayout.setBackgroundResource(R.mipmap.background_test);
                setupInfoFragmentTransaction(fragmentTransaction, 1, infoFragment);
                break;
            case R.id.nav_noiDung:
                hideTheMemes();
                frameLayout.setBackgroundResource(R.mipmap.background_test);
                setupInfoFragmentTransaction(fragmentTransaction, 2, infoFragment);
                break;
            case R.id.nav_thongTinLienHe:
                hideTheMemes();
                frameLayout.setBackgroundResource(R.mipmap.background_test);
                setupInfoFragmentTransaction(fragmentTransaction, 3, infoFragment);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hideTheMemes() {
        FrameLayout frameLayout = findViewById(R.id.parent_container);
        frameLayout.setBackgroundResource(R.color.white);
        nextPersonButton.setVisibility(View.GONE);
        scanButton.setVisibility(View.GONE);
    }

    private void setupInfoFragmentTransaction(FragmentTransaction fragmentTransaction, int id, InfoFragment infoFragment) {
        infoFragment.setId(id);
        fragmentTransaction.replace(R.id.parent_container, infoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
