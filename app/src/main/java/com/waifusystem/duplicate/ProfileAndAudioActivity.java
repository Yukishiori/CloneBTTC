package com.waifusystem.duplicate;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileAndAudioActivity extends AppCompatActivity {

    ConstraintLayout thisConstraintLayout;
    AudioControllerFragment audioControllerFragment = new AudioControllerFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    public static String ID = "id";
    ImageButton leftButton;
    ImageButton rightButton;
    SlideAdapter slideAdapter;

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
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_audio);

        thisConstraintLayout = findViewById(R.id.profile_holder);

        int profileId = getIntent().getExtras().getInt(ID);
        Log.d("china", "onCreate: "+ profileId);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap backgroundImage = BitmapFactory.decodeResource(getResources(), Profile.profiles[profileId].getProfileImagePath(), options);
        ImageView imageView = findViewById(R.id.profile_image);
        imageView.setImageBitmap(backgroundImage);

        profileFragment.setProfile(profileId);

        slideAdapter = new SlideAdapter(getSupportFragmentManager(), profileFragment, audioControllerFragment);
        viewPager = findViewById(R.id.fragment_container);
        viewPager.setAdapter(slideAdapter);

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
            } else {
                Intent broadcastIntent = new Intent(Broadcast_NEW_PROFILE);
                broadcastIntent.putExtra(ID, personId);
                sendBroadcast(broadcastIntent);
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

}
