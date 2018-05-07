package com.waifusystem.duplicate;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioTrack;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class AudioControllerFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private FloatingActionButton playButton;

    private SeekBar seekBar;
    private TextView timeMax;
    private TextView timeNow;

     Handler handler;

     Runnable runnable = new Runnable() {
         @Override
         public void run() {
             if (MediaPlayerService.mediaPlayer != null) {
                 seekBar.setProgress(MediaPlayerService.mediaPlayer.getCurrentPosition());
                 setupStuff();
                 playButton.setImageResource(MediaPlayerService.play_pauseIcon);
             }
             handler.postDelayed(this, 10);
         }
     };


    public AudioControllerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_controller, container, false);



        timeMax = view.findViewById(R.id.time_max);
        timeNow = view.findViewById(R.id.time_now);

        playButton = view.findViewById(R.id.play);
        ImageButton rewindButton = view.findViewById(R.id.rewind);
        ImageButton fastForwardButton = view.findViewById(R.id.fast_forward);

        fastForwardButton.setOnClickListener(this);
        rewindButton.setOnClickListener(this);

        playButton.setOnClickListener(this);

        seekBar = view.findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input && MediaPlayerService.mediaPlayer != null) {
                    MediaPlayerService.mediaPlayer.seekTo(progress);
                    MediaPlayerService.resumePosition = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        updateSeekbar();
        return view;
    }


    public void updateSeekbar() {
        handler = new Handler();
        handler.post(runnable);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        playButton.setImageResource(MediaPlayerService.play_pauseIcon);
    }

    @Override
    public void onClick(View view) {
        if (MediaPlayerService.mediaPlayer != null) {
            switch (view.getId()) {
                case R.id.play:
//                    setupStuff();
                    toggleButton();
                    break;
                case R.id.rewind:
//                    setupStuff();
                    ProfileAndAudioActivity.mediaPlayerService.rewindMedia();
                    break;
                case R.id.fast_forward:
                    ProfileAndAudioActivity.mediaPlayerService.fastForwardMedia();
                    break;
                default:
                    break;
            }
        } else {
            toastThis("Something's wrong");
        }
    }

    @SuppressLint("DefaultLocale")
    private void setupStuff() {
        seekBar.setMax(MediaPlayerService.mediaPlayer.getDuration());
        int secs = (MediaPlayerService.mediaPlayer.getDuration() / 1000) % 60;
        int mins = MediaPlayerService.mediaPlayer.getDuration() / 1000 / 60;
        timeMax.setText(String.format(("%1$02d : %2$02d"), mins, secs));

        timeNow.setText(String.format(("%1$02d : %2$02d"), ((MediaPlayerService.mediaPlayer.getCurrentPosition()) / 1000 / 60 ),
                ((MediaPlayerService.mediaPlayer.getCurrentPosition() / 1000) % 60)));
    }

    private void toggleButton() {
        if (MediaPlayerService.mediaPlayer.isPlaying()) {
            ProfileAndAudioActivity.mediaPlayerService.pauseMedia();
        } else {
            ProfileAndAudioActivity.mediaPlayerService.resumeMedia();
        }
    }

    public void toastThis(String content) {
        Toast.makeText(getActivity().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
