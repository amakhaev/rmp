package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.components.playlistDialog.NewPlaylistDialogComponent;
import com.rmp.widget.components.playlistDialog.OpenFileDialogComponent;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import com.rmp.widget.controls.button.IconButton;
import com.rmp.widget.controls.panels.RoundPanel;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.PlaylistPanelSkin;
import com.rmp.widget.skins.SkinFactory;
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
    private final PlaylistPanelSkin skin;

    @Setter
    private PlaylistEventHandler playlistEventHandler;

    @Getter
    private JPanel playlistPanel;
    private TitledBorder playlistTitle;
    private PlaylistContextMenu contextMenu;
    private NewPlaylistDialogComponent playlistDialogComponent;
    private MediaListComponent mediaListComponent;

    @Setter
    private OpenFileDialogComponent openFileDialogComponent;

    /**
     * Initialize new instance of {@link PlaylistPanelComponent}
     */
    PlaylistPanelComponent(Dimension size, PlaylistDataWatcher dataWatcher) {
        this.skin = SkinFactory.getRMPSkin().getPlaylistSkin();
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
        this.mediaListComponent = new MediaListComponent(this.skin.getSmallPlayIcon());
        this.mediaListComponent.setListItemForegroundColor(this.skin.getListItemForegroundColor());
        this.mediaListComponent.setSelectedListItemBackgroundColor(this.skin.getSelectedListItemBackgroundColor());
        this.mediaListComponent.setFocusedListItemBackgroundColor(this.skin.getFocusedListItemBackgroundColor());
        this.mediaListComponent.setListItemBackgroundColor(this.skin.getListItemBackgroundColor());

        this.mediaListComponent.setDeleteMediaItemHandler(
                mediaFileIdList -> {
                    if (playlistEventHandler != null) {
                        playlistEventHandler.onMediaFilesDeleted(mediaFileIdList);
                    }
                    return  null;
                }
        );

        this.mediaListComponent.setDoubleClickMediaItemHandler(mediaFileId -> {
            if (playlistEventHandler != null) {
                playlistEventHandler.onMediaItemDoubleClick(mediaFileId);
            }
            return null;
        });

        this.playlistTitle = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "");
        this.playlistTitle.setTitleColor(Colors.LIGHT_GRAY);

        JPanel mediaListPanel = new RoundPanel();
        mediaListPanel.setBackground(Color.BLACK);
        mediaListPanel.setBorder(this.playlistTitle);
        mediaListPanel.setLayout(new BorderLayout());

        mediaListPanel.add(this.mediaListComponent.create(), BorderLayout.CENTER);

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
            public void mouseClicked(MouseEvent e) {
                playlistPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        JButton openFileButton = new IconButton(
                30,
                this.skin.getOpenFileIcon(),
                this.skin.getOpenFileIcon()
        );
        openFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (openFileDialogComponent != null && playlistEventHandler != null) {
                    playlistEventHandler.onFilesSelected(openFileDialogComponent.startForResult());
                }
            }
        });

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
                                        if (this.playlistEventHandler != null) {
                                            this.playlistEventHandler.onPlaylistSelected(pl.getId());
                                        }
                                    }
                            )
                    );
                    this.contextMenu.update();
                }
            });
        }

        if (this.dataWatcher.getSelectedPlaylistObserver() != null) {
            this.dataWatcher.getSelectedPlaylistObserver().subscribe(selectedPlaylist -> {
                String title = Character.toUpperCase(selectedPlaylist.getTitle().charAt(0)) +
                        selectedPlaylist.getTitle().substring(1);
                this.playlistTitle.setTitle(title);
                this.contextMenu.setCheckedPlaylist(selectedPlaylist.getId());
                this.playlistPanel.repaint();
            });
        }

        if (this.dataWatcher.getAddMediaFileObserver() != null) {
            this.dataWatcher.getAddMediaFileObserver().subscribe(mediaFile -> {
                this.mediaListComponent.addFile(mediaFile);
            });
        }

        if (this.dataWatcher.getReplaceMediaFilesObserver() != null) {
            this.dataWatcher.getReplaceMediaFilesObserver().subscribe(mediaFiles -> {
                this.mediaListComponent.addFiles(mediaFiles);
            });
        }

        if (this.dataWatcher.getSelectedMediaFileIdObserver() != null) {
            this.dataWatcher.getSelectedMediaFileIdObserver().subscribe(selectedMediaFileId -> {
                this.mediaListComponent.setSelectedItemId(selectedMediaFileId);
            });
        }
    }
}
