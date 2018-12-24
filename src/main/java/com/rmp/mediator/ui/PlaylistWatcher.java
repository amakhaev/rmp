package com.rmp.mediator.ui;

import com.rmp.widget.dataWatcher.ReplayDataObserver;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIMediaFileModel;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the implementation of watching on playlists
 */
public class PlaylistWatcher implements PlaylistDataWatcher {

    private ReplayDataObserver<List<UIPlaylistModel>> playlistModels;
    private ReplayDataObserver<UIPlaylistModel> selectedPlaylist;
    private ReplayDataObserver<UIMediaFileModel> addMediaFile;
    private ReplayDataObserver<List<UIMediaFileModel>> addMediaFiles;

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

    /**
     * Gets the observer that provides selected playlist
     */
    @Override
    public ReplayDataObserver<UIPlaylistModel> getSelectedPlaylistObserver() {
        if (this.selectedPlaylist == null) {
            this.selectedPlaylist = new ReplayDataObserver<>();
        }

        return this.selectedPlaylist;
    }

    /**
     * Gets the observer that called when media file is added
     */
    @Override
    public ReplayDataObserver<UIMediaFileModel> getAddMediaFileObserver() {
        if (this.addMediaFile == null) {
            this.addMediaFile = new ReplayDataObserver<>();
        }

        return this.addMediaFile;
    }

    /**
     * Gets the observer that called when media files were added
     */
    @Override
    public ReplayDataObserver<List<UIMediaFileModel>> getAddMediaFilesObserver() {
        if (this.addMediaFiles == null) {
            this.addMediaFiles = new ReplayDataObserver<>();
        }

        return this.addMediaFiles;
    }
}