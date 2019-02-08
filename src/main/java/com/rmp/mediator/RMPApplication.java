package com.rmp.mediator;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.listeners.ControlPanelEventListener;
import com.rmp.mediator.listeners.GlobalKeyboardListener;
import com.rmp.mediator.listeners.MediaDetailEventListener;
import com.rmp.mediator.listeners.PlaylistEventListener;
import com.rmp.mediator.mediaPlayer.PlayerMediator;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.mediator.watchers.WatcherContainer;
import com.rmp.widget.RMPWidget;
import com.rmp.widget.RMPWidgetBuilder;
import com.rmp.widget.components.controlPanel.timelinePanel.TimeLabelOrder;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;

/**
 * Provides the com.rmp.mediator.mediator that working with UI
 */
@Slf4j
public class RMPApplication {

    private RMPWidget widget;
    private PlayerMediator playerMediator;

    /**
     * Shows the UI widget
     */
    public void showUI() {
        SwingUtilities.invokeLater(this.widget::showWidget);
    }

    /**
     * Initialize new application
     */
    public void initialize() {
        if (this.widget != null) {
            return;
        }

        WatcherContainer watcherContainer = new WatcherContainer();
        this.playerMediator = new PlayerMediator(new AsyncTaskExecutor(), watcherContainer);

        PlaylistEventListener playlistHandler = new PlaylistEventListener(this.playerMediator);
        ControlPanelEventListener controlPanelEventHandler = new ControlPanelEventListener(this.playerMediator);

        this.widget = new RMPWidgetBuilder()
                .setPlaylistDataWatcher(watcherContainer.getPlaylistDataWatcher())
                .setPlaylistEventHandler(playlistHandler)
                .setControlPanelDataWatcher(watcherContainer.getControlPanelDataWatcher())
                .setControlPanelEventHandler(controlPanelEventHandler)
                .setMediaDetailDataWatcher(watcherContainer.getMediaDetailDataWatcher())
                .setMediaDetailEventHandler(new MediaDetailEventListener())
                .build();


        new NativeDiscovery().discover();
        this.playerMediator.initialize();

        this.registerGlobalKeyboardListener();
        this.initializeUI();
    }

    private void initializeUI() {
        StateService stateService = new StateService();
        PlaylistService playlistService = new PlaylistService();
        MediaFileService mediaFileService = new MediaFileService();

        this.initializePlaylistUI(stateService, playlistService, mediaFileService);
        this.initializeControlPanelUI(stateService);
        this.initializeMediaDetailUI(stateService, mediaFileService);
    }

    private void initializePlaylistUI(StateService stateService,
                                      PlaylistService playlistService,
                                      MediaFileService mediaFileService) {
        StateModel currentState = stateService.getCurrentState();
        this.playerMediator.emitPlaylists(playlistService.getAllPlaylists());
        this.playerMediator.emitSelectedPlaylistChanged(playlistService.getById(currentState.getPlaylistId()));
        this.playerMediator.emitMediaFileAdded(mediaFileService.getByPlaylistId(currentState.getPlaylistId()));
        this.playerMediator.emitSelectedMediaFileChanged(currentState.getPlaylistFileId());
    }

    private void initializeControlPanelUI(StateService stateService) {
        this.playerMediator.emitTimeChanged(0L);
        this.playerMediator.emitTimeLengthChanged(0L);

        StateModel stateModel = stateService.getCurrentState();
        TimeLabelOrder uiLabelOrder = null;
        switch (stateModel.getTimeLabelOrder()) {
            case ASC:
                uiLabelOrder = TimeLabelOrder.ASC;
                break;
            case DESC:
                uiLabelOrder = TimeLabelOrder.DESC;
                break;
        }
        this.playerMediator.setTimeLabelOrder(uiLabelOrder);
        this.playerMediator.setMute(stateModel.isMute());
    }

    private void initializeMediaDetailUI(StateService stateService, MediaFileService mediaFileService) {
        Integer mediaFileId = stateService.getCurrentState().getPlaylistFileId();
        this.playerMediator.emitMediaDetailChanged(
                mediaFileId == null ? null : mediaFileService.getById(mediaFileId)
        );
    }

    private void registerGlobalKeyboardListener() {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            log.error(ex.getMessage());
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyboardListener(playerMediator));
    }
}
