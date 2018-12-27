package com.rmp.vlcPlayer.mediaData;

import java.util.List;

/**
 * Provides the media playlist
 */
public interface MediaPlaylist {

    /**
     * Gets the next media from playlist
     */
    String getNextMedia();

    /**
     * Gets the prev media from playlist
     */
    String getPrevMedia();

    /**
     * Gets the current media from playlist
     */
    String getCurrentMedia();

    /**
     * Gets the index of selected item
     */
    int getCurrentIndex();

    /**
     * Sets the current media file to play
     *
     * @param path - the path od media item
     */
    void setCurrentMedia(String path);

    /**
     * Indicates when playlist has next value
     */
    boolean hasNext();

    /**
     * Indicates when playlist has prev value
     */
    boolean hasPrev();
}
