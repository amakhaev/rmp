package com.rmp.widget.dataWatcher;

import com.rmp.widget.readModels.UIMediaFileModel;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the watchers for playlist
 */
public interface PlaylistDataWatcher {

    /**
     * Gets the observer that watched on playlist models
     */
    ReplayDataObserver<List<UIPlaylistModel>> getPlaylistModelObserver();

    /**
     * Gets the observer that provides selected playlist
     */
    ReplayDataObserver<UIPlaylistModel> getSelectedPlaylistObserver();

    /**
     * Gets the observer that called when media file was added
     */
    ReplayDataObserver<UIMediaFileModel> getAddMediaFileObserver();

    /**
     * Gets the observer that called when media files were added
     */
    ReplayDataObserver<List<UIMediaFileModel>> getAddMediaFilesObserver();

    /**
     * Gets the observer that called when media file id was changed
     */
    ReplayDataObserver<Integer> getSelectedMediaFileIdObserver();
}
