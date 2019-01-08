package com.rmp.mediator.ui;

import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.ReplayDataObserver;
import com.rmp.widget.readModels.UIMediaFileModel;

/**
 * Provides the implementation of watching on MediaDetails
 */
public class MediaDetailWatcher implements MediaDetailDataWatcher {

    private final ReplayDataObserver<UIMediaFileModel> mediaFileObserver;

    /**
     * Initialize new media file observer
     */
    public MediaDetailWatcher() {
        this.mediaFileObserver = new ReplayDataObserver<>();
    }

    /**
     * Gets the observer that watched on media file changing
     */
    @Override
    public ReplayDataObserver<UIMediaFileModel> getMediaFileObserver() {
        return this.mediaFileObserver;
    }
}
