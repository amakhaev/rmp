package com.rmp.widget.dataWatcher;

/**
 * Provides the data watcher to button's manipulate
 */
public interface ControlPanelDataWatcher {

    /**
     * Provides the observer that indicates when player is playing
     */
    ReplayDataObserver<Boolean> getIsPlayingObserver();

}
