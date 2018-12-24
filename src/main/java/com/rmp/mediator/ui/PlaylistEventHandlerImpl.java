package com.rmp.mediator.ui;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.widget.controller.PlaylistEventHandler;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;

/**
 * Provides the implementation of event handler from UI
 */
public class PlaylistEventHandlerImpl implements PlaylistEventHandler {

    private final PlaylistDataWatcher playlistDataWatcher;
    private final PlaylistService playlistService;
    private final AsyncTaskExecutor asyncTaskExecutor;
    private final StateService stateService;

    /**
     * Initialize new instance of {@link PlaylistEventHandlerImpl}
     */
    public PlaylistEventHandlerImpl(PlaylistDataWatcher playlistDataWatcher,
                                    AsyncTaskExecutor asyncTaskExecutor) {
        this.playlistDataWatcher = playlistDataWatcher;
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.playlistService = new PlaylistService();
        this.stateService = new StateService();
    }

    /**
     * Handles the create playlist event
     *
     * @param title - the name of new playlist
     */
    @Override
    public void onPlaylistCreate(String title) {
        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistService.createPlaylist(title);
            this.playlistDataWatcher.getPlaylistModelObserver().emit(this.playlistService.getAllPlaylists());
            this.onPlaylistSelected(this.playlistService.getByTitle(title).getId());
        });
    }

    /**
     * Handles the selecting of playlist from list
     *
     * @param id - the identifier of selected playlist
     */
    @Override
    public void onPlaylistSelected(int id) {
        this.asyncTaskExecutor.executeTask(() -> {
            StateModel currentState = this.stateService.getCurrentState();
            currentState.setPlaylistId(id);
            currentState = this.stateService.updateState(currentState);

            this.playlistDataWatcher.getSelectedPlaylistObserver().emit(
                    this.playlistService.getById(currentState.getPlaylistId())
            );
        });
    }
}
