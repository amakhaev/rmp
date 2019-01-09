package com.rmp.mediator;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.mediaPlayer.MediaPlayerEventListener;
import com.rmp.mediator.mediaPlayer.MediaPlaylistFactory;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.mediator.ui.*;
import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.widget.RMPWidget;
import com.rmp.widget.RMPWidgetBuilder;
import com.rmp.widget.components.controlPanel.TimeLabelOrder;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.Skin;
import lombok.extern.slf4j.Slf4j;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;

/**
 * Provides the com.rmp.mediator.mediator that working with UI
 */
@Slf4j
public class UIMediator {

    private AsyncTaskExecutor asyncTaskExecutor;

    private final ControlPanelDataWatcher controlPanelDataWatcher;
    private final PlaylistDataWatcher playlistDataWatcher;
    private final MediaDetailDataWatcher mediaDetailDataWatcher;
    private UIWatcherContainer watcherContainer;

    private RMPWidget widget;

    private PlaylistService playlistService;
    private StateService stateService;
    private MediaFileService mediaFileService;

    /**
     * Initialize new instance of {@link UIMediator}
     */
    public UIMediator() {
        this.asyncTaskExecutor = new AsyncTaskExecutor();

        this.controlPanelDataWatcher = new ControlPanelWatcher();
        this.playlistDataWatcher = new PlaylistWatcher();
        this.mediaDetailDataWatcher = new MediaDetailWatcher();
        this.watcherContainer = new UIWatcherContainer(
                this.controlPanelDataWatcher,
                this.playlistDataWatcher,
                this.mediaDetailDataWatcher
        );

        this.playlistService = new PlaylistService();
        this.stateService = new StateService();
        this.mediaFileService = new MediaFileService();

        this.initializeUI();
    }

    /**
     * Initializes the UI part
     */
    public void initialize() {
        if (this.widget == null) {
            PlaylistEventHandlerImpl playlistHandler = new PlaylistEventHandlerImpl(
                    this.asyncTaskExecutor,
                    this.watcherContainer
            );
            ControlPanelEventHandlerImpl controlPanelEventHandler = new ControlPanelEventHandlerImpl(
                    this.asyncTaskExecutor,
                    this.watcherContainer
            );

            this.widget = new RMPWidgetBuilder()
                    .setSkin(Skin.DEFAULT)
                    .setPlaylistDataWatcher(this.playlistDataWatcher)
                    .setPlaylistEventHandler(playlistHandler)
                    .setControlPanelDataWatcher(this.controlPanelDataWatcher)
                    .setControlPanelEventHandler(controlPanelEventHandler)
                    .setMediaDetailDataWatcher(this.mediaDetailDataWatcher)
                    .setMediaDetailEventHandler(new MediaDetailEventHandlerImpl())
                    .build();


            new NativeDiscovery().discover();

            VlcMediaPlayer mediaPlayer = this.createMediaPlayer();
            controlPanelEventHandler.setMediaPlayer(mediaPlayer);
            playlistHandler.setMediaPlayer(mediaPlayer);
        }
    }

    /**
     * Shows the UI widget
     */
    public void showUI() {
        SwingUtilities.invokeLater(() -> this.widget.showWidget());
    }

    private void initializeUI() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.initializePlaylistUI();
            this.initializeControlPanelUI();
            this.initializeMediaDetailUI();
        });
    }

    private VlcMediaPlayer createMediaPlayer() {
        return new VlcMediaPlayer(
                MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService),
                new MediaPlayerEventListener(this.watcherContainer, asyncTaskExecutor)
        );
    }

    private void initializePlaylistUI() {
        StateModel currentState = this.stateService.getCurrentState();
        this.watcherContainer.emitPlaylistsChanged(this.playlistService.getAllPlaylists());
        this.watcherContainer.emitSelectedPlaylistChanged(playlistService.getById(currentState.getPlaylistId()));
        this.watcherContainer.emitMediaFileReplaced(this.mediaFileService.getByPlaylistId(currentState.getPlaylistId()));
        this.watcherContainer.emitSelectedMediaFileChanged(currentState.getPlaylistFileId());
    }

    private void initializeControlPanelUI() {
        this.watcherContainer.emitTimelineValueChanged(0L);
        this.watcherContainer.emitTimelineLengthChanged(0L);

        TimeLabelOrder uiLabelOrder = null;
        switch (this.stateService.getCurrentState().getTimeLabelOrder()) {
            case ASC:
                uiLabelOrder = TimeLabelOrder.ASC;
                break;
            case DESC:
                uiLabelOrder = TimeLabelOrder.DESC;
                break;
        }
        this.watcherContainer.emitTimeLabelOrderChanged(uiLabelOrder);
    }

    private void initializeMediaDetailUI() {
        Integer mediaFileId = this.stateService.getCurrentState().getPlaylistFileId();
        this.watcherContainer.emitMediaDetailChanged(
                mediaFileId == null ? null : this.mediaFileService.getById(mediaFileId)
        );
    }
}
