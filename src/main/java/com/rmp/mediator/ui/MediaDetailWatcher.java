package com.rmp.mediator.ui;

import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.ReplayDataObserver;
import com.rmp.widget.readModels.UIMediaFileModel;

import java.awt.*;

/**
 * Provides the implementation of watching on MediaDetails
 */
public class MediaDetailWatcher implements MediaDetailDataWatcher {

    private final ReplayDataObserver<UIMediaFileModel> mediaFileObserver;
    private final ReplayDataObserver<Image> mediaFileArtObserver;
    private final ReplayDataObserver<Integer> mediaTotalCountObserver;
    private final ReplayDataObserver<Integer> mediaSelectedMediaIndexObserver;

    /**
     * Initialize new media file observer
     */
    public MediaDetailWatcher() {
        this.mediaFileObserver = new ReplayDataObserver<>();
        this.mediaFileArtObserver = new ReplayDataObserver<>();
        this.mediaTotalCountObserver = new ReplayDataObserver<>();
        this.mediaSelectedMediaIndexObserver = new ReplayDataObserver<>();
    }

    /**
     * Gets the observer that watched on media file changing
     */
    @Override
    public ReplayDataObserver<UIMediaFileModel> getMediaFileObserver() {
        return this.mediaFileObserver;
    }

    /**
     * Gets the observer that watched on media file art changing
     */
    @Override
    public ReplayDataObserver<Image> getMediaFileArtObserver() {
        return this.mediaFileArtObserver;
    }

    /**
     * Gets the observer that watched on media file count changing
     */
    @Override
    public ReplayDataObserver<Integer> getTotalCountObserver() {
        return this.mediaTotalCountObserver;
    }

    /**
     * Gets the observer that watched on selected media file intex changing
     */
    @Override
    public ReplayDataObserver<Integer> getSelectedMediaIndexObserver() {
        return this.mediaSelectedMediaIndexObserver;
    }
}
