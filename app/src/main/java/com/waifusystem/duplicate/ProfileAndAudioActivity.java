package com.waifusystem.duplicate;

import android.app.FragmentTransaction;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ProfileAndAudioActivity extends AppCompatActivity {


    ItemFragment itemFragment = new ItemFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    public static String ID;
    private int playAvailable = 1;

    TaskStackBuilder taskStackBuilder;
    SlideAdapter slideAdapter;

    ViewPager viewPager;

    Intent playerIntent;

    public static String Broadcast_NEW_PROFILE = "PlayNewAudio";

    static MediaPlayerService  mediaPlayerService;
    boolean serviceBound = false;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_audio);

//        toItemTransaction();
        taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(ScanAndMapActivity.class);

        int personId = -1;
        try {
            personId = getIntent().getExtras().getInt(ID);
        }catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "something bad happened... like null", Toast.LENGTH_SHORT).show();
//            personId = 0;
        }

        profileFragment.setProfile(personId);
        itemFragment.setProfileId(personId);
        slideAdapter = new SlideAdapter(getSupportFragmentManager(), profileFragment, itemFragment);
        viewPager = findViewById(R.id.fragment_container);
        viewPager.setAdapter(slideAdapter);
        playAudio(personId);
    }

    private void playAudio(int personId) {
        if (playAvailable > 0) {
            if (!serviceBound) {
                playerIntent = new Intent(this, MediaPlayerService.class);
                playerIntent.putExtra(ID, personId);
                bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                startService(playerIntent);
            } else {
                ItemFragment.mediaPlayer = null;
                Intent broadcastIntent = new Intent(Broadcast_NEW_PROFILE);
                broadcastIntent.putExtra(ID, personId);
                sendBroadcast(broadcastIntent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (playerIntent != null) {
            stopService(playerIntent);
        }
    }

}
