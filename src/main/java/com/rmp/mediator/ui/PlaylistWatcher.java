package com.rmp.mediator.ui;

import com.rmp.widget.dataWatcher.ReplayDataObserver;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the implementation of watching on playlists
 */
public class PlaylistWatcher implements PlaylistDataWatcher {

    private ReplayDataObserver<List<UIPlaylistModel>> playlistModels;

    /**
     * Gets the observer that watched on playlist models
     */
    @Override
    public ReplayDataObserver<List<UIPlaylistModel>> getPlaylistModelObserver() {
        if (this.playlistModels == null) {
            this.playlistModels = new ReplayDataObserver<>();
        }

        return this.playlistModels;
    }
}