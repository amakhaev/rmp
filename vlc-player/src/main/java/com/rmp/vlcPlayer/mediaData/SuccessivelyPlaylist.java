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
        this.selectedPathIndex = this.selectedPathIndex >= this.mediaFilePath.size() - 1 ? 0 : this.selectedPathIndex + 1;
        return this.getCurrentMedia();
    }

    /**
     * Gets the prev media from playlist
     */
    @Override
    public String getPrevMedia() {
        this.selectedPathIndex = this.selectedPathIndex == 0 ? this.mediaFilePath.size() - 1 : this.selectedPathIndex - 1;
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
     * Gets the count of media items
     */
    @Override
    public int getMediaCount() {
        return this.mediaFilePath == null ? 0 : this.mediaFilePath.size();
    }

    /**
     * Sets the current media file to play
     *
     * @param path - the path od media item
     */
    @Override
    public void setCurrentMedia(String path) {
        int foundIndex = this.mediaFilePath.indexOf(path);

        if (foundIndex != -1) {
            this.selectedPathIndex = foundIndex;
        }
    }

    /**
     * Indicates when playlist has next value
     */
    @Override
    public boolean hasNext() {
        return !this.mediaFilePath.isEmpty();
    }

    /**
     * Indicates when playlist has prev value
     */
    @Override
    public boolean hasPrev() {
        return !this.mediaFilePath.isEmpty();
    }
}
