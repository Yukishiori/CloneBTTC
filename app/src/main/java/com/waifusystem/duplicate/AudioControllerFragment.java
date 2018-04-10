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
    int mins;
    int secs;


    public AudioControllerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_controller, container, false);

        timeMax = view.findViewById(R.id.time_max);

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
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                if (MediaPlayerService.mediaPlayer != null) {
                    seekBar.setProgress(MediaPlayerService.mediaPlayer.getCurrentPosition());
                    playButton.setImageResource(MediaPlayerService.play_pauseIcon);
                    setupStuff();
                    if (MediaPlayerService.mediaPlayer.isPlaying()) {
                        playButton.setImageResource(R.drawable.ic_pause_white_48px);
                    } else {
                        playButton.setImageResource(R.drawable.ic_play_arrow_white_48px);
                    }
                }
                handler.postDelayed(this, 10);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();


    }


    @SuppressLint("DefaultLocale")
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
            toastThis("");
        }
    }

    private void setupStuff() {
        seekBar.setMax(MediaPlayerService.mediaPlayer.getDuration());
        secs = (MediaPlayerService.mediaPlayer.getDuration() / 1000) % 60;
        mins = MediaPlayerService.mediaPlayer.getDuration() / 1000 / 60;
        timeMax.setText(String.format(("%1$02d : %2$02d"), mins, secs));
    }

    private void toggleButton() {
        if (MediaPlayerService.mediaPlayer.isPlaying()) {
            

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                playButton.setImageResource(R.drawable.play_to_pause);
//                pauseToPlayAnimation = (AnimatedVectorDrawable) playButton.getDrawable();
//                pauseToPlayAnimation.start();
//            } else {
                playButton.setImageResource(R.drawable.ic_play_arrow_white_48px);

//            }
            ProfileAndAudioActivity.mediaPlayerService.pauseMedia();
//                pauseClicked = true;
        } else {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                playButton.setImageResource(R.drawable.pause_to_play);
//                playToPauseAnimation = (AnimatedVectorDrawable) playButton.getDrawable();
//                playToPauseAnimation.start();
//            } else {
                playButton.setImageResource(R.drawable.ic_pause_white_48px);
//            }
            ProfileAndAudioActivity.mediaPlayerService.resumeMedia();
        }
    }

    public void toastThis(String content) {
        Toast.makeText(getActivity().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }


}
