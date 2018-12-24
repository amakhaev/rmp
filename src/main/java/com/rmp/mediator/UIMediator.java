package com.rmp.mediator;

import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.mediator.ui.PlaylistEventHandlerImpl;
import com.rmp.mediator.ui.PlaylistWatcher;
import com.rmp.widget.RMPWidget;
import com.rmp.widget.RMPWidgetBuilder;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.Skin;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * Provides the com.rmp.mediator.mediator that working with UI
 */
@Slf4j
public class UIMediator {

    private AsyncTaskExecutor asyncTaskExecutor;

    private PlaylistDataWatcher playlistDataWatcher;
    private RMPWidget widget;

    /**
     * Initialize new instance of {@link UIMediator}
     */
    public UIMediator() {
        this.playlistDataWatcher = new PlaylistWatcher();
        this.asyncTaskExecutor = new AsyncTaskExecutor();

        this.initializeUI();
    }

    /**
     * Shows the UI widget
     */
    public void showUI() {
        if (this.widget == null) {
            PlaylistEventHandler handler = new PlaylistEventHandlerImpl(
                    this.playlistDataWatcher,
                    this.asyncTaskExecutor
            );

            this.widget = new RMPWidgetBuilder()
                    .setSkin(Skin.DEFAULT)
                    .setDataWatcher(this.playlistDataWatcher)
                    .setPlaylistEventHandler(handler)
                    .build();
        }

        SwingUtilities.invokeLater(() -> this.widget.showWidget());
    }

    private void initializeUI() {
        PlaylistService playlistService = new PlaylistService();
        StateService stateService=  new StateService();
        MediaFileService mediaFileService = new MediaFileService();

        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistDataWatcher.getPlaylistModelObserver().emit(playlistService.getAllPlaylists());
        });

        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistDataWatcher.getSelectedPlaylistObserver().emit(playlistService.getById(
                    stateService.getCurrentState().getPlaylistId()
            ));
        });

        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistDataWatcher.getReplaceMediaFilesObserver().emit(
                    mediaFileService.getByPlaylistId(stateService.getCurrentState().getPlaylistId())
            );
        });
    }
}
