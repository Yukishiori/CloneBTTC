package com.waifusystem.duplicate;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends android.support.v4.app.Fragment {
    private int profileId;
    private ImageView personPic;
    private TextView personName;
    private TextView description;
    private Profile profile;
    public static ImageButton playButton;

    private Handler handler = new Handler();
    private Runnable runnable;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        profile = Profile.profiles[profileId];
        configProfileStuff();
        recordSpin();
    }

    private void configProfileStuff() {
        View view = getView();
        if (view != null) {
            playButton = view.findViewById(R.id.playButton);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProfileAndAudioActivity.mediaPlayerService.resumeMedia();
                    playButton.setVisibility(View.INVISIBLE);
                    playButton.setActivated(false);
                }
            });

            personPic = view.findViewById(R.id.person_pic);
            personName = view.findViewById(R.id.person_name);
            description = view.findViewById(R.id.profile_description);
            personPic.setImageResource(profile.getProfilePicPath());
            personName.setText(profile.getName());
            description.setText(profile.getDescription());

        }
    }

    private void recordSpin() {
        if (ItemFragment.mediaPlayer != null && ItemFragment.mediaPlayer.isPlaying()) {
            Log.d(TAG, "recordSpin: yea yea yeah" );
            personPic.setRotation((float) (personPic.getRotation() + 0.2));
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                recordSpin();
            }
        };
        handler.postDelayed(runnable, 10);
    }

    public void setProfile(int id) {
        this.profileId = id;
    }

}
