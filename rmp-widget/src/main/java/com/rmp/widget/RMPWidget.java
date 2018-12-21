package com.rmp.widget;

import com.rmp.widget.components.playlistDialog.NewPlaylistDialogComponent;
import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.components.rootPanel.RootPanelComponent;
import com.rmp.widget.components.rootPanel.RootPanelBuilder;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.RMPSkin;
import com.rmp.widget.utilities.LocalizationUtils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Provides the UI widget of Remote Media Player
 */
public class RMPWidget {

    private JFrame widget;

    private final RMPSkin skin;

    /**
     * Initialize new instance of {@link RMPWidget}
     *
     * @param skin - the skin that should be used when UI is shows
     * @param playlistDataWatcher - the playlist data watcher
     */
    RMPWidget(RMPSkin skin, PlaylistDataWatcher playlistDataWatcher) {
        this.widget = new JFrame(LocalizationUtils.getString("rmp_title"));
        this.skin = skin;
        this.initialize(playlistDataWatcher);
    }

    /**
     * Shows the widget
     */
    public void showWidget() {
        this.widget.pack();
        this.widget.setVisible(true);
    }

    private void initialize(PlaylistDataWatcher playlistDataWatcher) {
        this.widget.setResizable(false);
        this.widget.setPreferredSize(this.skin.getSize());
        this.widget.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        RootPanelComponent rootPanelComponent = new RootPanelBuilder()
                .setSkin(this.skin)
                .setDataWatcher(this.createPlaylistBuilder(playlistDataWatcher))
                .build();
        this.widget.setContentPane(rootPanelComponent.getRootPanel());
    }

    private PlaylistPanelBuilder createPlaylistBuilder(PlaylistDataWatcher playlistDataWatcher) {
        return new PlaylistPanelBuilder()
                .setDataWatcher(playlistDataWatcher)
                .setPlaylistDialogComponent(new NewPlaylistDialogComponent(this.widget));
    }
}
