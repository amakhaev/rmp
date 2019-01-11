package com.rmp.widget.dataWatcher;

import com.rmp.widget.readModels.UIMediaFileModel;

import java.awt.*;

/**
 * Provides the data watcher for media detail component
 */
public interface MediaDetailDataWatcher {

    /**
     * Gets the observer that watched on media file changing
     */
    ReplayDataObserver<UIMediaFileModel> getMediaFileObserver();

    /**
     * Gets the observer that watched on media file art changing
     */
    ReplayDataObserver<Image> getMediaFileArtObserver();

    /**
     * Gets the observer that watched on media file count changing
     */
    ReplayDataObserver<Integer> getTotalCountObserver();

    /**
     * Gets the observer that watched on selected media file intex changing
     */
    ReplayDataObserver<Integer> getSelectedMediaIndexObserver();

}
