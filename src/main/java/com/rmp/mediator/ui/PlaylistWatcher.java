package com.rmp.mediator.ui;

import com.rmp.widget.dataWatcher.ReplayDataObserver;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIMediaFileModel;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the implementation of watching on playlists
 */
public final class PlaylistWatcher implements PlaylistDataWatcher {

    private final ReplayDataObserver<List<UIPlaylistModel>> playlistModels;
    private final ReplayDataObserver<UIPlaylistModel> selectedPlaylist;
    private final ReplayDataObserver<UIMediaFileModel> addMediaFile;
    private final ReplayDataObserver<List<UIMediaFileModel>> addMediaFiles;
    private final ReplayDataObserver<Integer> selectedMediaFileId;

    public PlaylistWatcher() {
        this.playlistModels = new ReplayDataObserver<>();
        this.selectedPlaylist = new ReplayDataObserver<>();
        this.addMediaFile = new ReplayDataObserver<>();
        this.addMediaFiles = new ReplayDataObserver<>();
        this.selectedMediaFileId = new ReplayDataObserver<>();
    }

    /**
     * Gets the observer that watched on playlist models
     */
    @Override
    public ReplayDataObserver<List<UIPlaylistModel>> getPlaylistModelObserver() {
        return this.playlistModels;
    }

    /**
     * Gets the observer that provides selected playlist
     */
    @Override
    public ReplayDataObserver<UIPlaylistModel> getSelectedPlaylistObserver() {
        return this.selectedPlaylist;
    }

    /**
     * Gets the observer that called when media file is added
     */
    @Override
    public ReplayDataObserver<UIMediaFileModel> getAddMediaFileObserver() {
        return this.addMediaFile;
    }

    /**
     * Gets the observer that called when media files were added
     */
    @Override
    public ReplayDataObserver<List<UIMediaFileModel>> getReplaceMediaFilesObserver() {
        return this.addMediaFiles;
    }

    /**
     * Gets the observer that called when media file id was changed
     */
    @Override
    public ReplayDataObserver<Integer> getSelectedMediaFileIdObserver() {
        return this.selectedMediaFileId;
    }
}