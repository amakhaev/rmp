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
     * Sets the media files
     *
     * @param filePaths - the path to selected files
     */
    void setMediaFilePaths(List<String> filePaths);
}
