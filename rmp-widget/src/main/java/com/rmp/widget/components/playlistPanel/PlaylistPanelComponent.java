package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.components.playlistDialog.NewPlaylistDialogComponent;
import com.rmp.widget.controller.PlaylistEventHandler;
import com.rmp.widget.controls.button.IconButton;
import com.rmp.widget.controls.roundPanel.RoundPanel;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.PlaylistPanelSkin;
import com.rmp.widget.utilities.LocalizationUtils;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Provides the playlist component of widget
 */
public class PlaylistPanelComponent {

    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);

    private PlaylistDataWatcher dataWatcher;
    private PlaylistPanelSkin skin;

    @Setter
    private PlaylistEventHandler playlistEventHandler;

    @Getter
    private JPanel playlistPanel;
    private TitledBorder playlistTitle;
    private PlaylistContextMenu contextMenu;
    private NewPlaylistDialogComponent playlistDialogComponent;

    /**
     * Initialize new instance of {@link PlaylistPanelComponent}
     *
     * @param skin - the skin of {@link PlaylistPanelComponent}
     * @param size - the size of {@link PlaylistPanelComponent}
     */
    PlaylistPanelComponent(PlaylistPanelSkin skin, Dimension size, PlaylistDataWatcher dataWatcher) {
        this.skin = skin;
        this.dataWatcher = dataWatcher;

        this.playlistPanel = new JPanel();
        this.playlistPanel.setPreferredSize(size);

        this.initialize();
        this.subscribeToWatcherChanges();
    }

    /**
     * Sets the playlist dialog component
     *
     * @param playlistDialogComponent - the dialog to enter new playlist
     */
    void setPlaylistDialogComponent(NewPlaylistDialogComponent playlistDialogComponent) {
        this.playlistDialogComponent = playlistDialogComponent;
        if (this.playlistDialogComponent != null) {
            this.playlistDialogComponent.getSaveSubject().subscribe(playlistTitle -> {
                if (playlistEventHandler != null) {
                    playlistEventHandler.onPlaylistCreate(playlistTitle);
                }
            });
        }
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

        JPopupMenu playlistPopupMenu = this.createPlaylistPopupMenu();
        playlistButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                playlistPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        JButton openFileButton = new IconButton(
                30,
                this.skin.getOpenFileIcon(),
                this.skin.getOpenFileIcon()
        );

        buttonsPanel.add(playlistButton);
        buttonsPanel.add(openFileButton);

        return buttonsPanel;
    }

    private JPopupMenu createPlaylistPopupMenu() {
        this.contextMenu = new PlaylistContextMenu(this.skin.getCheckedFileIcon(), this.skin.getAddIcon());

        this.contextMenu.addButton(
                LocalizationUtils.getString("new_playlist"),
                true,
                () -> {
                    if (this.playlistDialogComponent != null) {
                        this.playlistDialogComponent.start();
                    }
                }
        );

        return this.contextMenu.update();
    }

    private void subscribeToWatcherChanges() {
        if (this.dataWatcher == null) {
            return;
        }

        if (this.dataWatcher.getPlaylistModelObserver() != null) {
            this.dataWatcher.getPlaylistModelObserver().subscribe(models -> {
                if (models != null && !models.isEmpty()) {
                    models.forEach(
                            (pl) -> this.contextMenu.addPlaylistButton(
                                    pl.getId(),
                                    pl.getTitle(),
                                    () -> {
                                        System.out.println("Playlist clicked");
                                    }
                            )
                    );
                    this.contextMenu.update();
                }
            });
        }
    }
}
