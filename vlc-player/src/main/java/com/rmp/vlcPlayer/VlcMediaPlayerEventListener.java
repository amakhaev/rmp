package com.rmp.vlcPlayer;

import uk.co.caprica.vlcj.player.MediaMeta;

/**
 * Provides the event listener of media player
 */
public interface VlcMediaPlayerEventListener {

    /**
     * Handles the playing event. Called when media item started/proceeded playing
     */
    void onPlaying(VlcMediaPlayer mediaPlayer);

    /**
     * Handles the stopped event. Called when media item stopped
     */
    void onStopped(VlcMediaPlayer mediaPlayer);

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

    /**
     * Handles the changing of media metadata
     *
     * @param metadata - the metadata of current item
     */
    void onMetadataChanged(MediaMeta metadata);

    /**
     * Handles the changing of items count in playlist
     *
     * @param totalCount - the total count of items
     */
    void onMediaItemTotalCountChanged(int totalCount);

    /**
     * Handles the changing of selected item in playlist
     *
     * @param selectedItemIndex - the index of selected item
     */
    void onSelectedMediaItemChanged(int selectedItemIndex);

    /**
     * Handles the changing of mute state
     *
     * @param isMuted - the mute state
     */
    void onMuteChanged(boolean isMuted);
}
