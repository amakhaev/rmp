package com.rmp.widget.components.mediaDetailPanel;

import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.eventHandler.MediaDetailEventHandler;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.MediaDetailPanelSkin;
import com.rmp.widget.skins.SkinFactory;
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
    private MediaSummaryPanel mediaSummaryPanel;

    @Setter
    private MediaDetailEventHandler eventHandler;

    @Setter
    private MediaDetailDataWatcher dataWatcher;

    @Getter
    private JPanel mediaDetailPanel;

    /**
     * Initialize new instance of {@link MediaDetailComponent}
     */
    MediaDetailComponent() {
        this.skin = SkinFactory.getRMPSkin().getMediaDetailSkin();
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
        this.mediaSummaryPanel = this.createMediaSummaryPanel();

        this.mediaDetailPanel.add(this.mediaTitlePanel, BorderLayout.PAGE_START);
        this.mediaDetailPanel.add(this.mediaArtPanel, BorderLayout.CENTER);
        this.mediaDetailPanel.add(this.mediaSummaryPanel, BorderLayout.PAGE_END);

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
        MediaArtPanel mediaArtPanel = new MediaArtPanel(this.skin.getDefaultArt());
        mediaArtPanel.setGradient(this.skin.getArtPanelBackgroundGradientPalette());
        return mediaArtPanel;
    }

    private MediaSummaryPanel createMediaSummaryPanel() {
        MediaSummaryPanel mediaSummary = new MediaSummaryPanel();
        mediaSummary.setTotalCountColors(
                this.skin.getTotalCountBackgroundColor(),
                this.skin.getTotalCountTitleColor(),
                this.skin.getTotalCountValueColor()
        );

        mediaSummary.setSelectedTrackColors(
                this.skin.getSelectedTrackBackgroundColor(),
                this.skin.getSelectedTrackTitleColor(),
                this.skin.getSelectedTrackValueColor()
        );

        return mediaSummary;
    }

    private void subscribeToWatcherChanges() {
        if (this.dataWatcher == null) {
            return;
        }

        if (this.dataWatcher.getMediaFileObserver() != null) {
            this.dataWatcher.getMediaFileObserver().subscribe(
                    mediaFile -> this.mediaTitlePanel.setTitle(mediaFile == null ? null : mediaFile.getDisplayName())
            );
        }

        if (this.dataWatcher.getMediaFileArtObserver() != null) {
            this.dataWatcher.getMediaFileArtObserver().subscribe(imageArt -> this.mediaArtPanel.setArt(imageArt));
        }

        if (this.dataWatcher.getTotalCountObserver() != null) {
            this.dataWatcher.getTotalCountObserver().subscribe(
                    totalCount -> this.mediaSummaryPanel.setTotalCountValue(totalCount)
            );
        }

        if (this.dataWatcher.getSelectedMediaIndexObserver() != null) {
            this.dataWatcher.getSelectedMediaIndexObserver().subscribe(
                    selectedTrackIndex -> this.mediaSummaryPanel.setSelectedTrackValue(selectedTrackIndex)
            );
        }
    }


}
