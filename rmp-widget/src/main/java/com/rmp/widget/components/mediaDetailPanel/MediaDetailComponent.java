package com.rmp.widget.components.mediaDetailPanel;

import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.eventHandler.MediaDetailEventHandler;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.MediaDetailPanelSkin;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Provides the component that displayed information about media
 */
public class MediaDetailComponent {

    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);

    private final MediaDetailPanelSkin skin;

    private MediaTitlePanel mediaTitlePanel;
    private MediaArtPanel mediaArtPanel;

    @Setter
    private MediaDetailEventHandler eventHandler;

    @Setter
    private MediaDetailDataWatcher dataWatcher;

    @Getter
    private JPanel mediaDetailPanel;

    /**
     * Initialize new instance of {@link MediaDetailComponent}
     */
    MediaDetailComponent(MediaDetailPanelSkin skin) {
        this.skin = skin;
        this.mediaDetailPanel = new JPanel();
    }

    /**
     * Initializes the component
     */
    void initialize() {
        this.mediaDetailPanel.setLayout(new BorderLayout());
        this.mediaDetailPanel.setBorder(EMPTY_BORDER);
        this.mediaDetailPanel.setBackground(Colors.TRANSPARENT);

        this.mediaTitlePanel = this.createMediaTitlePanel();
        this.mediaArtPanel = this.createMediaArtPanel();

        this.mediaDetailPanel.add(this.mediaTitlePanel, BorderLayout.PAGE_START);
        this.mediaDetailPanel.add(this.mediaArtPanel, BorderLayout.CENTER);

        this.subscribeToWatcherChanges();
    }

    private MediaTitlePanel createMediaTitlePanel() {
        MediaTitlePanel mediaTitlePanel = new MediaTitlePanel();
        mediaTitlePanel.setBackground(this.skin.getTitlePanelBackgroundColor());
        mediaTitlePanel.setLabelForegroundColor(this.skin.getTitlePanelForegroundColor());
        mediaTitlePanel.setTitle(null);

        return mediaTitlePanel;
    }

    private MediaArtPanel createMediaArtPanel() {
        return new MediaArtPanel(this.skin.getDefaultArt());
    }

    private void subscribeToWatcherChanges() {
        if (this.dataWatcher == null) {
            return;
        }

        if (this.dataWatcher.getMediaFileObserver() != null) {
            this.dataWatcher.getMediaFileObserver().subscribe(mediaFile -> {
                this.mediaTitlePanel.setTitle(mediaFile == null ? null : mediaFile.getDisplayName());
            });
        }

        if (this.dataWatcher.getMediaFileArtObserver() != null) {
            this.dataWatcher.getMediaFileArtObserver().subscribe(imageArt -> {
                this.mediaArtPanel.setArt(imageArt);
            });
        }
    }


}
