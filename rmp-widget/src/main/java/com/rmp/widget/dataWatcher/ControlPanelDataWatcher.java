package com.rmp.widget.dataWatcher;

import com.rmp.widget.components.controlPanel.TimeLabelOrder;

/**
 * Provides the data watcher to button's manipulate
 */
public interface ControlPanelDataWatcher {

    /**
     * Gets the observer that indicates when player is playing
     */
    ReplayDataObserver<Boolean> getIsPlayingObserver();

    /**
     * Gets the observer that indicates when slider value was changed
     */
    ReplayDataObserver<Long> getTimelineValueChangedObserver();

    /**
     * Gets the observer that indicates when slider length was changed
     */
    ReplayDataObserver<Long> getTimelineLengthChangedObserver();

    /**
     * Gets the observer that indicates when time label order was changed
     */
    ReplayDataObserver<TimeLabelOrder> getTimeLabelOrderChangedObserver();
}
