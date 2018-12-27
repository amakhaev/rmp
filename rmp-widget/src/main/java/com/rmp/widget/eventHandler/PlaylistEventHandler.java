package com.rmp.widget.eventHandler;

import java.io.File;
import java.util.List;

/**
 * Provides the handler of event from playlist component.
 */
public interface PlaylistEventHandler {

    /**
     * Handles the create playlist event
     *
     * @param name - the name of new playlist
     */
    void onPlaylistCreate(String name);

    /**
     * Handles the selecting of playlist from list
     *
     * @param id - the identifier of selected playlist
     */
    void onPlaylistSelected(int id);

    /**
     * Handles the selecting of files
     *
     * @param files - the files to be handled
     */
    void onFilesSelected(List<File> files);

    /**
     * Handles the deleting of media files
     *
     * @param mediaFileIds - the media files ids
     */
    void onMediaFilesDeleted(List<Integer> mediaFileIds);

    /**
     * Handles double click by media file item in playlist
     *
     * @param mediaFileId - the media file id
     */
    void onMediaItemDoubleClick(int mediaFileId);
}
