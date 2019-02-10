package com.rmp.widget;

import com.rmp.widget.components.controlPanel.ControlPanelBuilder;
import com.rmp.widget.components.mediaDetailPanel.MediaDetailBuilder;
import com.rmp.widget.components.playlistDialog.NewPlaylistDialogComponent;
import com.rmp.widget.components.playlistDialog.OpenFileDialogComponent;
import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.components.rootPanel.RootPanelComponent;
import com.rmp.widget.components.rootPanel.RootPanelBuilder;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.eventHandler.MediaDetailEventHandler;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.RMPSkin;
import com.rmp.widget.skins.SkinFactory;
import com.rmp.widget.utilities.LocalizationUtils;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Provides the UI widget of Remote Media Player
 */
public class RMPWidget {

    private JFrame widget;

    private final RMPSkin skin;

    @Setter
    private Runnable closeWidgetAction;

    /**
     * Initialize new instance of {@link RMPWidget}
     */
    RMPWidget() {
        this.widget = new JFrame(LocalizationUtils.getString("rmp_title"));
        this.skin = SkinFactory.getRMPSkin();
    }

    /**
     * Shows the widget
     */
    public void showWidget() {
        this.widget.pack();
        this.widget.setVisible(true);
    }

    /**
     * Initialize widget
     *
     * @param playlistDataWatcher - the data watcher
     */
    void initialize(PlaylistDataWatcher playlistDataWatcher,
                    PlaylistEventHandler playlistEventHandler,
                    ControlPanelDataWatcher dataWatcher,
                    ControlPanelEventHandler eventHandler,
                    MediaDetailDataWatcher mediaDetailDataWatcher,
                    MediaDetailEventHandler mediaDetailEventHandler) {
        this.widget.setResizable(false);
        this.widget.setPreferredSize(this.skin.getSize());
        this.widget.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (closeWidgetAction != null) {
                    closeWidgetAction.run();
                }

                System.exit(0);
            }
        });

        RootPanelComponent rootPanelComponent = new RootPanelBuilder()
                .setPlaylistPanelBuilder(this.createPlaylistBuilder(playlistDataWatcher, playlistEventHandler))
                .setControlPanelBuilder(this.createButtonPanelBuilder(dataWatcher, eventHandler))
                .setMediaDetailBuilder(this.createMediaDetailPanelBuilder(mediaDetailDataWatcher, mediaDetailEventHandler))
                .build();
        this.widget.setContentPane(rootPanelComponent.getRootPanel());
    }

    private PlaylistPanelBuilder createPlaylistBuilder(PlaylistDataWatcher playlistDataWatcher,
                                                       PlaylistEventHandler playlistEventHandler
    ) {
        return new PlaylistPanelBuilder()
                .setDataWatcher(playlistDataWatcher)
                .setPlaylistDialogComponent(new NewPlaylistDialogComponent(this.widget))
                .setOpenFileDialogComponent(new OpenFileDialogComponent(this.widget))
                .setPlaylistEventHandler(playlistEventHandler);
    }

    private ControlPanelBuilder createButtonPanelBuilder(ControlPanelDataWatcher dataWatcher,
                                                         ControlPanelEventHandler eventHandler) {
        return new ControlPanelBuilder()
                .setControlPanelDataWatcher(dataWatcher)
                .setEventHandler(eventHandler);
    }

    private MediaDetailBuilder createMediaDetailPanelBuilder(MediaDetailDataWatcher dataWatcher,
                                                             MediaDetailEventHandler eventHandler) {
        return new MediaDetailBuilder()
                .setMediaDetailDataWatcher(dataWatcher)
                .setEventHandler(eventHandler);
    }
}
