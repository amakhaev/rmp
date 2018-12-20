package com.rmp.mediator.watcher;

import com.rmp.widget.dataWatcher.DataObserver;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the implementation of watching on playlists
 */
public class PlaylistWatcher implements PlaylistDataWatcher {

    private DataObserver<List<UIPlaylistModel>> playlistModels;

    /**
     * Gets the observer that watched on playlist models
     */
    @Override
    public DataObserver<List<UIPlaylistModel>> getPlaylistModelObserver() {
        if (this.playlistModels == null) {
            this.playlistModels = new DataObserver<>();
        }

        return this.playlistModels;
    }
}