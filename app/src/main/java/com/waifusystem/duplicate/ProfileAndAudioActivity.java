package com.waifusystem.duplicate;

import android.app.FragmentContainer;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileAndAudioActivity extends AppCompatActivity {

    ItemFragment itemFragment = new ItemFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    public static String ID;


    Intent playerIntent;

    public static String Broadcast_NEW_PROFILE = "PlayNewAudio";

    static MediaPlayerService  mediaPlayerService;
    boolean serviceBound = false;

    public static MediaPlayer mediaPlayer;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) iBinder;
            mediaPlayerService = binder.getAudioService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBound = false;
        }
    };

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
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        int personId = -1;
        try {
            personId = getIntent().getExtras().getInt(ID);
        }catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "something bad happened... like null", Toast.LENGTH_SHORT).show();
//            personId = 0;
        }

        profileFragment.setProfile(personId);
        itemFragment.setProfileId(personId);
        profileChange(personId);

    }

    private void toProfileTransaction() {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, profileFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }

    private void toItemTransaction() {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, itemFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void profileChange(int personId) {
        if (!serviceBound) {
            playerIntent = new Intent(this, MediaPlayerService.class);
            playerIntent.putExtra(ID, personId);
            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            startService(playerIntent);
        } else {
            mediaPlayer = null;
            Intent broadcastIntent = new Intent(Broadcast_NEW_PROFILE);
            broadcastIntent.putExtra(ID, personId);
            sendBroadcast(broadcastIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacks(runnable);

        //todo remember to add this
        stopService(playerIntent);
    }

//    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "china ?", Toast.LENGTH_SHORT).show();
    }
}
