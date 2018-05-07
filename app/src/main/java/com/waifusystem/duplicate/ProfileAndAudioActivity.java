package com.waifusystem.duplicate;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileAndAudioActivity extends AppCompatActivity {

    ConstraintLayout thisConstraintLayout;
    AudioControllerFragment audioControllerFragment;
    ProfileFragment profileFragment;

    public static String ID = "id";
    ImageButton leftButton;
    ImageButton rightButton;
    FragmentSlideAdapter fragmentSlideAdapter;

    ViewPager viewPager;

    Intent playerIntent;

    public static String Broadcast_NEW_PROFILE = "PlayNewAudio";

    static MediaPlayerService mediaPlayerService;
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
            mediaPlayerService.stopMedia();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_audio);
        if (savedInstanceState == null) {
            this.audioControllerFragment = new AudioControllerFragment();
            this.profileFragment = new ProfileFragment();
        }
        thisConstraintLayout = findViewById(R.id.profile_holder);

        int profileId = getIntent().getExtras().getInt(ID);

        ImageView imageView = findViewById(R.id.profile_image);
        imageView.setImageResource(Profile.profiles[profileId].getProfileImagePath());
//
        profileFragment.setProfile(profileId);

        fragmentSlideAdapter = new FragmentSlideAdapter(getSupportFragmentManager(), profileFragment, audioControllerFragment);
        viewPager = findViewById(R.id.fragment_container);
        viewPager.setAdapter(fragmentSlideAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1) {
                    leftButton.setVisibility(View.VISIBLE);
                    rightButton.setVisibility(View.GONE);
                } else {
                    leftButton.setVisibility(View.GONE);
                    rightButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        leftButton = findViewById(R.id.left_button);
        rightButton = findViewById(R.id.right_button);
        playAudio(profileId);

    }

    private void playAudio(int personId) {

            if (!serviceBound) {
                playerIntent = new Intent(this, MediaPlayerService.class);
                playerIntent.putExtra(ID, personId);
                bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                startService(playerIntent);
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerIntent != null) {
            stopService(playerIntent);
        }
    }


    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.left_button:
                viewPager.setCurrentItem(0);
                break;
            case R.id.right_button:
                viewPager.setCurrentItem(1);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayerService.stopMedia();
        MediaPlayerService.mediaPlayer = null;
        MediaPlayerService.resumePosition = 0;
    }
}
