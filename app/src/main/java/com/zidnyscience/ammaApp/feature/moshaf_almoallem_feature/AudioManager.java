package com.zidnyscience.ammaApp.feature.moshaf_almoallem_feature;

import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class AudioManager {
    private static AudioManager currentPlayingInstance;
    private static boolean isAnyAudioPlaying = false;
    private MediaPlayer mediaPlayer;
    private boolean isPrepared = false;
    private AudioState currentState = AudioState.IDLE;
    private AudioStateListener stateListener;
    private Handler seekBarHandler = new Handler();
    private Runnable seekBarRunnable;

    public enum AudioState {
        LOADING, READY, PLAYING, PAUSED, COMPLETED, FAILED, IDLE
    }

    public interface AudioStateListener {
        void onStateChanged(AudioState newState);
    }

    public void setAudioStateListener(AudioStateListener listener) {
        this.stateListener = listener;
    }

    public void loadAudio(String url, Runnable onLoadingStart, Runnable onSuccess, Runnable onFailure) {
        stopCurrentPlayingInstance();

        mediaPlayer = new MediaPlayer();
        isPrepared = false;
        currentState = AudioState.LOADING;
        notifyStateChange();

        if (onLoadingStart != null) {
            onLoadingStart.run();
        }

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            Log.e("AudioManager", "Error setting data source", e);
            handleFailure(onFailure);
            return;
        }

        mediaPlayer.setOnPreparedListener(mp -> {
            isPrepared = true;
            currentState = AudioState.READY;
            notifyStateChange();
            if (onSuccess != null) {
                onSuccess.run();
            }
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            currentState = AudioState.COMPLETED;
            isAnyAudioPlaying = false;
            notifyStateChange();
        });

        mediaPlayer.setOnErrorListener((mp, what, extra) -> {
            handleFailure(onFailure);
            return true;
        });

        currentPlayingInstance = this;
    }

    private void stopCurrentPlayingInstance() {
        if (currentPlayingInstance != null && currentPlayingInstance != this) {
            currentPlayingInstance.pauseAudio();
            isAnyAudioPlaying = false;
            currentPlayingInstance = null;
        }
    }

    private void handleFailure(Runnable onFailure) {
        currentState = AudioState.FAILED;
        isAnyAudioPlaying = false;
        notifyStateChange();
        if (onFailure != null) {
            onFailure.run();
        }
    }

    public void retryLoadAudio(String url, Runnable onLoadingStart, Runnable onSuccess, Runnable onFailure) {
        loadAudio(url, onLoadingStart, onSuccess, onFailure);
    }

    public void playAudio() {
        if (isPrepared && mediaPlayer != null && !mediaPlayer.isPlaying()) {
            stopCurrentPlayingInstance();
            mediaPlayer.start();
            currentState = AudioState.PLAYING;
            isAnyAudioPlaying = true;
            notifyStateChange();
            currentPlayingInstance = this;
        }
    }

    public void pauseAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            currentState = AudioState.PAUSED;
            isAnyAudioPlaying = false;
            notifyStateChange();
            stopSeekBarUpdate();
        }
    }

    public void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isPrepared = false;
            currentState = AudioState.IDLE;
            isAnyAudioPlaying = false;
            notifyStateChange();
            stopSeekBarUpdate();
            if (currentPlayingInstance == this) {
                currentPlayingInstance = null;
            }
        }
    }

    public AudioState getCurrentState() {
        return currentState;
    }

    public static boolean isAnyAudioPlaying() {
        return isAnyAudioPlaying;
    }

    private void notifyStateChange() {
        if (stateListener != null) {
            stateListener.onStateChanged(currentState);
        }
    }

    public void startSeekBarUpdate(final SeekBar seekBar, final TextView timeText) {
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) return;

        seekBar.setMax(mediaPlayer.getDuration());

        seekBarRunnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int currentPos = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPos);
                    timeText.setText(millisecondsToTimer(currentPos));
                    seekBarHandler.postDelayed(this, 100);
                }
            }
        };
        seekBarHandler.postDelayed(seekBarRunnable, 0);
    }

    void stopSeekBarUpdate() {
        if (seekBarRunnable != null) {
            seekBarHandler.removeCallbacks(seekBarRunnable);
            seekBarRunnable = null;
        }
    }

    public void resetAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private String millisecondsToTimer(int milliseconds) {
        int minutes = (milliseconds / 1000) / 60;
        int seconds = (milliseconds / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
