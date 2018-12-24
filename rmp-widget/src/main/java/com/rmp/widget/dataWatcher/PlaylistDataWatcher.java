package com.rmp.widget.dataWatcher;

import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the ui for playlist
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

}
