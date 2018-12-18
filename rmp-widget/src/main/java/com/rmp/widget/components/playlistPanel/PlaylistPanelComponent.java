package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.controls.iconButton.IconButton;
import com.rmp.widget.controls.roundPanel.RoundPanel;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.PlaylistPanelSkin;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Provides the playlist component of widget
 */
public class PlaylistPanelComponent {

    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);

    private PlaylistPanelSkin skin;

    @Getter
    private JPanel playlistPanel;
    private TitledBorder playlistTitle;

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

        this.initialize();
    }

    private void initialize() {
        this.playlistPanel.setLayout(new BorderLayout());
        this.playlistPanel.setBorder(EMPTY_BORDER);
        this.playlistPanel.setBackground(Colors.TRANSPARENT);

        this.playlistPanel.add(this.createMediaListPanel(), BorderLayout.CENTER);
        this.playlistPanel.add(this.createButtonsPanel(), BorderLayout.PAGE_END);
    }

    private JPanel createMediaListPanel() {
        this.playlistTitle = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Test");
        this.playlistTitle.setTitleColor(Colors.LIGHT_GRAY);

        JPanel mediaListPanel = new RoundPanel();
        mediaListPanel.setBackground(Color.BLACK);
        mediaListPanel.setBorder(this.playlistTitle);

        return mediaListPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new RoundPanel();
        buttonsPanel.setBackground(Colors.TRANSPARENT);
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Colors.TRANSPARENT));

        JButton playlistButton = new IconButton(
                30,
                this.skin.getPlaylistIcon(),
                this.skin.getPlaylistIcon()
        );

        buttonsPanel.add(playlistButton);

        JButton openFileButton = new IconButton(
                30,
                this.skin.getOpenFileIcon(),
                this.skin.getOpenFileIcon()
        );

        buttonsPanel.add(openFileButton);

        return buttonsPanel;
    }
}
