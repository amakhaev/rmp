package com.rmp.mediator;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.mediaPlayer.MediaPlayerEventListener;
import com.rmp.mediator.mediaPlayer.MediaPlaylistFactory;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.mediator.ui.ControlPanelEventHandlerImpl;
import com.rmp.mediator.ui.MediaDetailEventHandlerImpl;
import com.rmp.mediator.ui.MediaDetailWatcher;
import com.rmp.mediator.ui.PlaylistEventHandlerImpl;
import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.widget.RMPWidget;
import com.rmp.widget.RMPWidgetBuilder;
import com.rmp.widget.components.controlPanel.TimeLabelOrder;
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
    private UIWatcherContainer watcherContainer;

    private RMPWidget widget;

    private PlaylistService playlistService;
    private StateService stateService;
    private MediaFileService mediaFileService;

    /**
     * Initialize new instance of {@link UIMediator}
     */
    public UIMediator() {
        this.watcherContainer = new UIWatcherContainer();
        this.asyncTaskExecutor = new AsyncTaskExecutor();

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
                    .setPlaylistDataWatcher(this.watcherContainer.getPlaylistDataWatcher())
                    .setPlaylistEventHandler(playlistHandler)
                    .setControlPanelDataWatcher(this.watcherContainer.getControlPanelDataWatcher())
                    .setControlPanelEventHandler(controlPanelEventHandler)
                    .setMediaDetailDataWatcher(this.watcherContainer.getMediaDetailDataWatcher())
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
        this.watcherContainer.getPlaylistDataWatcher().getPlaylistModelObserver().emit(this.playlistService.getAllPlaylists());

        this.watcherContainer.getPlaylistDataWatcher().getSelectedPlaylistObserver().emit(playlistService.getById(
                currentState.getPlaylistId()
        ));

        this.watcherContainer.getPlaylistDataWatcher().getReplaceMediaFilesObserver().emit(
                this.mediaFileService.getByPlaylistId(currentState.getPlaylistId())
        );

        this.watcherContainer.getPlaylistDataWatcher().getSelectedMediaFileIdObserver().emit(
                currentState.getPlaylistFileId()
        );
    }

    private void initializeControlPanelUI() {
        this.watcherContainer.getControlPanelDataWatcher().getTimelineValueChangedObserver().emit(0L);
        this.watcherContainer.getControlPanelDataWatcher().getTimelineValueChangedObserver().emit(0L);

        TimeLabelOrder uiLabelOrder = null;
        switch (this.stateService.getCurrentState().getTimeLabelOrder()) {
            case ASC:
                uiLabelOrder = TimeLabelOrder.ASC;
                break;
            case DESC:
                uiLabelOrder = TimeLabelOrder.DESC;
                break;
        }
        this.watcherContainer.getControlPanelDataWatcher().getTimeLabelOrderChangedObserver().emit(uiLabelOrder);
    }

    private void initializeMediaDetailUI() {
        this.watcherContainer.getMediaDetailDataWatcher().getMediaFileObserver().emit(
                this.mediaFileService.getById(this.stateService.getCurrentState().getPlaylistFileId())
        );
    }
}
