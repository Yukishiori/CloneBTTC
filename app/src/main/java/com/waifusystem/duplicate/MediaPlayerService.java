package com.waifusystem.duplicate;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Shiori on 3/22/2018.
 */

public class MediaPlayerService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, AudioManager.OnAudioFocusChangeListener {

    public static MediaPlayer mediaPlayer;
    private int profileId;
    public static int resumePosition;
    public AudioManager audioManager;

    IBinder localBinder = new LocalBinder();

    private boolean ongoingCall = false;
    private PhoneStateListener phoneStateListener;
    private TelephonyManager telephonyManager;

    public static final String ACTION_PLAY = "ACTION_PLAY";
    public static final String ACTION_PAUSE = "ACTION_PAUSE";
    public static final String ACTION_REW = "ACTION_REW";
    public static final String ACTION_FF = "ACTION_FF";
    public static final String ACTION_STOP = "ACTION_STOP";

    private static final int NOTIFICATION_ID = 101;

    private Profile profile;
    public static int play_pauseIcon;

    private void initAudioPlayer() {
        mediaPlayer = MediaPlayer.create(MediaPlayerService.this, profile.getAudioPath());
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //Set up MediaPlayer event listeners
        mediaPlayer.setOnCompletionListener(MediaPlayerService.this);
        mediaPlayer.setOnErrorListener(MediaPlayerService.this);
        mediaPlayer.setOnPreparedListener(MediaPlayerService.this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        callStateListener();
        registerBecomingNoisyReceiver();
        register_playNewAudio();
    }


    public void playMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            buildNotification(PlaybackStatus.PLAYING);
        }
    }

    public void stopMedia() {
        if (mediaPlayer == null) return;
        //todo check this one more
            if (mediaPlayer.isPlaying()) {
                buildNotification(PlaybackStatus.PAUSED);
                mediaPlayer.stop();
                mediaPlayer.release();
            }
    }

    public void pauseMedia() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            resumePosition = mediaPlayer.getCurrentPosition();
            buildNotification(PlaybackStatus.PAUSED);

        }
    }

    public void resumeMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(resumePosition);
            mediaPlayer.start();
            buildNotification(PlaybackStatus.PLAYING);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            profileId = intent.getExtras().getInt(ProfileAndAudioActivity.ID);
            profile = Profile.profiles[profileId];
        } catch (NullPointerException e) {
            stopSelf();
        }

        if (!requestAudioFocus()) {
            stopSelf();
        }

        if (mediaPlayer == null) {
            initAudioPlayer();
            buildNotification(PlaybackStatus.PAUSED);
        } else {
            if (mediaPlayer.isPlaying()) {
                buildNotification(PlaybackStatus.PLAYING);
            } else {
                buildNotification(PlaybackStatus.PAUSED);
            }
        }

        handleIncomingActions(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopMedia();
        stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Toast.makeText(getApplicationContext(), "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK" + extra, Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:

                Toast.makeText(getApplicationContext(), "MEDIA ERROR SERVER DIED " + extra, Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Toast.makeText(getApplicationContext(), "UNKNOWN MEDIA ERROR" + extra, Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public class LocalBinder extends Binder {
        public MediaPlayerService getAudioService() {
            return MediaPlayerService.this;
        }
    }

    @Override
    public void onAudioFocusChange(int focusState) {
        //Invoked when the audio focus of the system is updated.
        switch (focusState) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // resume playback
                if (mediaPlayer == null) initAudioPlayer();
                mediaPlayer.setVolume(1.0f, 1.0f);
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                // Lost focus for an unbounded amount of time: stop playback and release media player
                if (mediaPlayer.isPlaying()) pauseMedia();
//                mediaPlayer.release();
//                mediaPlayer = null;
//                removeNotification();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // Lost focus for a short time, but we have to stop
                // playback. We don't release the media player because playback
                // is likely to resume
                if (mediaPlayer.isPlaying()) pauseMedia();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // Lost focus for a short time, but it's ok to keep playing
                // at an attenuated level
                if (mediaPlayer.isPlaying()) mediaPlayer.setVolume(0.1f, 0.1f);
                break;
        }
    }

    private boolean requestAudioFocus() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    private void removeAudioFocus() {
        audioManager.abandonAudioFocus(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            stopMedia();
        }
        if (phoneStateListener != null) {
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
        }

        removeNotification();
        unregisterReceiver(becomingNoisyReceiver);
        unregisterReceiver(profileReceiver);

        removeAudioFocus();
    }

    //Becoming noisy
    private BroadcastReceiver becomingNoisyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //pause audio on ACTION_AUDIO_BECOMING_NOISY
            pauseMedia();
        }
    };

    private void registerBecomingNoisyReceiver() {
        //register after getting audio focus
        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        registerReceiver(becomingNoisyReceiver, intentFilter);
    }

    private void callStateListener() {
        // Get the telephony manager
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Starting listening for PhoneState changes
        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    //if at least one call exists or the phone is ringing
                    //pause the MediaPlayer
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                    case TelephonyManager.CALL_STATE_RINGING:
                        if (mediaPlayer != null) {
                            pauseMedia();
                            ongoingCall = true;
                        }
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        // Phone idle. Start playing.
                        if (mediaPlayer != null) {
                            if (ongoingCall) {
                                ongoingCall = false;
                                resumeMedia();
                            }
                        }
                        break;
                }
            }
        };
        // Register the listener with the telephony manager
        // Listen for changes to the device call state.
        telephonyManager.listen(phoneStateListener,
                PhoneStateListener.LISTEN_CALL_STATE);
    }

    private BroadcastReceiver profileReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            profileId = intent.getExtras().getInt(ProfileAndAudioActivity.ID);
                mediaPlayer.reset();
                initAudioPlayer();
                buildNotification(PlaybackStatus.PLAYING);
        }
    };

    private void register_playNewAudio() {
        //Register playNewMedia profileReceiver
        IntentFilter filter = new IntentFilter(ProfileAndAudioActivity.Broadcast_NEW_PROFILE);
        registerReceiver(profileReceiver, filter);
    }

    private void buildNotification(PlaybackStatus playbackStatus) {

        play_pauseIcon = android.R.drawable.ic_media_play;//needs to be initialized
        PendingIntent play_pauseAction = null;

        //Build a new notification according to the current state of the MediaPlayer
        if (playbackStatus == PlaybackStatus.PLAYING) {
            play_pauseIcon = R.drawable.ic_pause_white_48px;
            //create the pause action
            play_pauseAction = playbackAction(1);
        } else if (playbackStatus == PlaybackStatus.PAUSED) {
            play_pauseIcon = R.drawable.ic_play_arrow_white_48px;
            //create the play action
            play_pauseAction = playbackAction(0);
        }

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 16;
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), profile.getProfileImagePath(), option); //replace with your own image

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(getApplicationContext(), "baotangthaucam")
                .setShowWhen(false)
                //this is the start activity stuff
//                .setContentIntent(pendingIntent)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2))
                .setColor(getResources().getColor(R.color.colorPrimaryDark))
                .setLargeIcon(largeIcon)
                .setSmallIcon(play_pauseIcon)
                .setContentText("BTTC's character")
                .setContentTitle(profile.getName())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_skip_previous_white_36px, "rewind", playbackAction(3))
                .addAction(play_pauseIcon, "pause", play_pauseAction)
                .addAction(R.drawable.ic_skip_next_white_36px, "fast_forward", playbackAction(2));
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(NOTIFICATION_ID, notificationCompat.build());
    }


    private void removeNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private PendingIntent playbackAction(int actionNumber) {
        Intent playbackAction = new Intent(this, MediaPlayerService.class);
        switch (actionNumber) {
            case 0:
                playbackAction.setAction(ACTION_PLAY);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 1:
                playbackAction.setAction(ACTION_PAUSE);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 2:
                playbackAction.setAction(ACTION_FF);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 3:
                playbackAction.setAction(ACTION_REW);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 4:
                playbackAction.setAction(ACTION_STOP);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            default:
                break;
        }
        return null;
    }

    private void handleIncomingActions(Intent playbackAction) {
        if (playbackAction == null || playbackAction.getAction() == null) return;
        String actionString = playbackAction.getAction();
        if (actionString.equalsIgnoreCase(ACTION_PLAY)) {
            resumeMedia();
        } else if (actionString.equalsIgnoreCase(ACTION_PAUSE)) {
            pauseMedia();
        } else if (actionString.equalsIgnoreCase(ACTION_FF)) {
            fastForwardMedia();
        } else if (actionString.equalsIgnoreCase(ACTION_REW)) {
            rewindMedia();
        } else if (actionString.equalsIgnoreCase(ACTION_STOP)) {
            removeNotification();
        }
    }

    public void fastForwardMedia() {
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
        resumePosition += 5000;
        if (!mediaPlayer.isPlaying()) {
            buildNotification(PlaybackStatus.PAUSED);
        }
    }

    public void rewindMedia() {
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
        resumePosition -= 5000;
        if (!mediaPlayer.isPlaying()) {
            buildNotification(PlaybackStatus.PAUSED);
        }
    }

}
