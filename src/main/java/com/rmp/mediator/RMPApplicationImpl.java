package com.rmp.mediator;

import com.google.inject.Inject;
import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.listeners.ControlPanelEventListener;
import com.rmp.mediator.listeners.GlobalKeyboardListener;
import com.rmp.mediator.listeners.MediaDetailEventListener;
import com.rmp.mediator.listeners.PlaylistEventListener;
import com.rmp.mediator.mediaPlayer.PlayerMediator;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
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
public class RMPApplicationImpl implements RMPApplication {

    private RMPWidget widget;
    private final PlayerMediator playerMediator;
    private final GlobalKeyboardListener keyboardListener;
    private final PlaylistService playlistService;
    private final StateService stateService;
    private final MediaFileService mediaFileService;
    private final PlaylistEventListener playlistHandler;
    private final ControlPanelEventListener controlPanelEventHandler;
    private final MediaDetailEventListener mediaDetailEventListener;

    /**
     * Initialize ew instance of {@link RMPApplicationImpl}
     */
    @Inject
    public RMPApplicationImpl(PlayerMediator playerMediator,
                              GlobalKeyboardListener keyboardListener,
                              PlaylistService playlistService,
                              StateService stateService,
                              MediaFileService mediaFileService,
                              PlaylistEventListener playlistHandler,
                              ControlPanelEventListener controlPanelEventHandler,
                              MediaDetailEventListener mediaDetailEventListener) {
        this.playerMediator = playerMediator;
        this.keyboardListener = keyboardListener;
        this.playlistService = playlistService;
        this.stateService = stateService;
        this.mediaFileService = mediaFileService;
        this.playlistHandler = playlistHandler;
        this.controlPanelEventHandler = controlPanelEventHandler;
        this.mediaDetailEventListener = mediaDetailEventListener;
    }

    /**
     * Shows the UI widget
     */
    @Override
    public void showUI() {
        SwingUtilities.invokeLater(this.widget::showWidget);
    }

    /**
     * Initialize new application
     */
    @Override
    public void initialize() {
        if (this.widget != null) {
            return;
        }

        this.widget = new RMPWidgetBuilder()
                .setPlaylistDataWatcher(this.playerMediator.getWatcherContainer().getPlaylistDataWatcher())
                .setPlaylistEventHandler(this.playlistHandler)
                .setControlPanelDataWatcher(this.playerMediator.getWatcherContainer().getControlPanelDataWatcher())
                .setControlPanelEventHandler(this.controlPanelEventHandler)
                .setMediaDetailDataWatcher(this.playerMediator.getWatcherContainer().getMediaDetailDataWatcher())
                .setMediaDetailEventHandler(this.mediaDetailEventListener)
                .build();


        new NativeDiscovery().discover();
        this.playerMediator.initialize();

        this.registerGlobalKeyboardListener();
        this.initializeUI();
    }

    private void initializeUI() {
        this.initializePlaylistUI();
        this.initializeControlPanelUI();
        this.initializeMediaDetailUI();
    }

    private void initializePlaylistUI() {
        StateModel currentState = this.stateService.getCurrentState();
        this.playerMediator.emitPlaylists(this.playlistService.getAllPlaylists());
        this.playerMediator.emitSelectedPlaylistChanged(this.playlistService.getById(currentState.getPlaylistId()));
        this.playerMediator.emitMediaFileAdded(this.mediaFileService.getByPlaylistId(currentState.getPlaylistId()));
        this.playerMediator.emitSelectedMediaFileChanged(currentState.getPlaylistFileId());
    }

    private void initializeControlPanelUI() {
        this.playerMediator.emitTimeChanged(0L);
        this.playerMediator.emitTimeLengthChanged(0L);

        StateModel stateModel = this.stateService.getCurrentState();
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

    private void initializeMediaDetailUI() {
        Integer mediaFileId = this.stateService.getCurrentState().getPlaylistFileId();
        this.playerMediator.emitMediaDetailChanged(
                mediaFileId == null ? null : this.mediaFileService.getById(mediaFileId)
        );
    }

    private void registerGlobalKeyboardListener() {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            log.error(ex.getMessage());
        }

        GlobalScreen.addNativeKeyListener(this.keyboardListener);
    }
}
