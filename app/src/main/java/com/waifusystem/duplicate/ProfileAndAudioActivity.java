package com.waifusystem.duplicate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class ProfileAndAudioActivity extends AppCompatActivity {

    ItemFragment itemFragment = new ItemFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    public static String ID;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toProfileTransaction();
                    return true;
                case R.id.navigation_dashboard:
                    toItemTransaction();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_audio);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        toProfileTransaction();

//        itemFragment = (ItemFragment) getFragmentManager().findFragmentById(R.id.fragment_item);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        int id;
        try {
            id = getIntent().getExtras().getInt(ID);
        }catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "NPE", Toast.LENGTH_SHORT).show();
            id = 0;
        }
        profileFragment.setProfile(id);
        itemFragment.setProfileId(id);
    }

    private void toProfileTransaction() {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, profileFragment);
        ft.addToBackStack(null);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

    private void toItemTransaction() {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, itemFragment);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
}
