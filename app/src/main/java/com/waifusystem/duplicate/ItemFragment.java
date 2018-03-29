package com.waifusystem.duplicate;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements View.OnClickListener {

    private int profileId;
    Profile profile;
    public static MediaPlayer mediaPlayer;
    ImageButton playButton;

    ImageView imageView;

    Handler handler;
    Runnable runnable;
    SeekBar seekBar;
    TextView timeNow;
    TextView timeMax;
    int mins;
    int secs;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        handler = new Handler();
        timeMax = view.findViewById(R.id.time_max);
        timeNow= view.findViewById(R.id.time_now);


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
                if (input && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
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

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void updateSeekBarAndRotate() {
        if (mediaPlayer != null ) {
            secs = (mediaPlayer.getDuration() / 1000) % 60;
            mins = mediaPlayer.getDuration() / 1000 / 60;

            timeMax.setText(String.format(("%1$02d : %2$02d"), mins, secs));

            seekBar.setMax(mediaPlayer.getDuration());

            secs = (mediaPlayer.getCurrentPosition() / 1000) % 60;
            mins = mediaPlayer.getCurrentPosition() / 1000 / 60;
            timeNow.setText(String.format(("%1$02d : %2$02d"), mins, secs));
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            playButton.setImageResource(MediaPlayerService.play_pauseIcon);
            if (mediaPlayer.isPlaying()) {
                imageView.setRotation(imageView.getRotation() + 1);
            }
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBarAndRotate();
            }
        };
        handler.postDelayed(runnable, 10);
    }

    @Override
    public void onStart() {
        profile = Profile.profiles[profileId];
        super.onStart();
        View view = getView();
        if (view != null) {
            imageView = view.findViewById(R.id.imageView4);
            imageView.setImageResource(profile.getItemPicPath());
            TextView textView = view.findViewById(R.id.description);
            textView.setText(profile.getItemDescription());
//            playButton.setImageResource(MediaPlayerService.play_pauseIcon);
            updateSeekBarAndRotate();
        }
    }

    public void setProfileId(int id) {
        this.profileId = id;
    }


    @Override
    public void onClick(View view) {

        if (mediaPlayer != null) {
            switch (view.getId()) {
                case R.id.play:
                    toggleButton();
                    break;
                case R.id.rewind:
                    toastThis("but why");
                    ProfileAndAudioActivity.mediaPlayerService.rewindMedia();
                    break;
                case R.id.fast_forward:
                    ProfileAndAudioActivity.mediaPlayerService.fastForwardMedia();
                    break;
                default:
                    break;
            }
        } else {
            toastThis("Play time is over~");
        }
    }

    private void toggleButton() {
        if (mediaPlayer != null) {

            if (mediaPlayer.isPlaying()) {
                ProfileAndAudioActivity.mediaPlayerService.pauseMedia();
            } else {
                ProfileAndAudioActivity.mediaPlayerService.resumeMedia();

            }
        }
    }

    public void toastThis(String content) {
        Toast.makeText(getActivity().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }


}
