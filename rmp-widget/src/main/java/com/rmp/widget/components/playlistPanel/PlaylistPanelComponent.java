package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.skins.PlaylistPanelSkin;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the playlist component of widget
 */
public class PlaylistPanelComponent {

    private PlaylistPanelSkin skin;

    @Getter
    private JPanel playlistPanel;

    /**
     * Initialize new instance of {@link PlaylistPanelComponent}
     *
     * @param skin - the skin of {@link PlaylistPanelComponent}
     * @param size - the size of {@link PlaylistPanelComponent}
     */
    PlaylistPanelComponent(PlaylistPanelSkin skin, Dimension size) {
        this.skin = skin;
        this.playlistPanel = new JPanel();
        this.playlistPanel.setPreferredSize(size);
    }

}
