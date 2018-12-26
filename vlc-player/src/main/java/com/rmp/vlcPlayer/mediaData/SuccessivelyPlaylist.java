package com.rmp.vlcPlayer.mediaData;

import java.util.Collections;
import java.util.List;

/**
 * Provides the playlist that returns media items in series
 */
public class SuccessivelyPlaylist implements MediaPlaylist {

    private int selectedPathIndex;
    private List<String> mediaFilePath;

    /**
     * Initialize new instance of {@link SuccessivelyPlaylist}
     */
    public SuccessivelyPlaylist(List<String> mediaFilePath, Integer selectedMediaFileIndex) {
        this.mediaFilePath = mediaFilePath == null ? Collections.emptyList() : mediaFilePath;
        this.selectedPathIndex = selectedMediaFileIndex == null ? 0 : selectedMediaFileIndex;
    }

    /**
     * Gets the next media from playlist
     */
    @Override
    public String getNextMedia() {
        this.selectedPathIndex = this.selectedPathIndex >= this.mediaFilePath.size() - 1 ? 0 : ++this.selectedPathIndex;
        return this.getCurrentMedia();
    }

    /**
     * Gets the prev media from playlist
     */
    @Override
    public String getPrevMedia() {
        this.selectedPathIndex = this.selectedPathIndex == 0 ? this.mediaFilePath.size() - 1 : --this.selectedPathIndex;
        return this.getCurrentMedia();
    }

    /**
     * Gets the current media from playlist
     */
    @Override
    public String getCurrentMedia() {
        return this.mediaFilePath == null || this.mediaFilePath.size() == 0 ?
                null : this.mediaFilePath.get(this.selectedPathIndex);
    }

    /**
     * Gets the index of selected item
     */
    @Override
    public int getCurrentIndex() {
        return this.selectedPathIndex;
    }

    /**
     * Sets the media files
     *
     * @param filePaths - the path to selected files
     */
    @Override
    public void setMediaFilePaths(List<String> filePaths) {
        this.mediaFilePath = filePaths;
        this.selectedPathIndex = 0;
    }
}
