package com.rmp.widget.dataWatcher;

import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the watcher for playlist
 */
public interface PlaylistDataWatcher {

    /**
     * Gets the observer that watched on playlist models
     */
    DataObserver<List<UIPlaylistModel>> getPlaylistModelObserver();

}
