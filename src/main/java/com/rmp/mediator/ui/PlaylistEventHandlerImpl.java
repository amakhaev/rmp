package com.rmp.mediator.ui;

import com.rmp.mediator.service.PlaylistService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.widget.controller.PlaylistEventHandler;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the implementation of event handler from UI
 */
public class PlaylistEventHandlerImpl implements PlaylistEventHandler {

    private final PlaylistDataWatcher playlistDataWatcher;
    private final PlaylistService playlistService;
    private final AsyncTaskExecutor asyncTaskExecutor;

    /**
     * Initialize new instance of {@link PlaylistEventHandlerImpl}
     */
    public PlaylistEventHandlerImpl(PlaylistDataWatcher playlistDataWatcher,
                                    PlaylistService playlistService,
                                    AsyncTaskExecutor asyncTaskExecutor) {
        this.playlistDataWatcher = playlistDataWatcher;
        this.playlistService = playlistService;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    /**
     * Handles the create playlist event
     *
     * @param name - the name of new playlist
     */
    @Override
    public void onPlaylistCreate(String name) {
        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistService.createPlaylist(name);
            this.playlistDataWatcher.getPlaylistModelObserver().emit(this.playlistService.getAllPlaylists());
        });
    }
}
