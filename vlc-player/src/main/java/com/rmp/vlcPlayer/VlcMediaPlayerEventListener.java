package com.rmp.vlcPlayer;

/**
 * Provides the event listener of media player
 */
public interface VlcMediaPlayerEventListener {

    /**
     * Handles the playing event. Called when media item started/proceeded playing
     */
    void onPlaying();

    /**
     * Handles the paused event. Called when media item paused
     */
    void onPaused();

    /**
     * Handles the stopped event. Called when media item stopped
     */
    void onStopped();

    /**
     * Handles the time changing event. Called when media item play time was changed
     *
     * @param newTime - the time value
     */
    void onTimeChanged(long newTime);

    /**
     * Handles the length changing event. Called when media item time length was changed
     *
     * @param newLength - the length time value
     */
    void onLengthChanged(long newLength);

    /**
     * Handles the finish event. Called when media item finished
     *
     * @param mediaPlayer - the media player
     */
    void onFinish(VlcMediaPlayer mediaPlayer);
}
