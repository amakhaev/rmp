package com.rmp.mediator.ui;

import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.ReplayDataObserver;

/**
 * Provides the implementation of {@link ControlPanelDataWatcher}
 */
public class ControlPanelWatcher implements ControlPanelDataWatcher {

    private final ReplayDataObserver<Boolean> isPlayingObserver;

    /**
     * Initialize new instance of {@link ControlPanelDataWatcher}
     */
    public ControlPanelWatcher() {
        this.isPlayingObserver = new ReplayDataObserver<>();
    }

    /**
     * Provides the observer that indicates when player is playing
     */
    @Override
    public ReplayDataObserver<Boolean> getIsPlayingObserver() {
        return this.isPlayingObserver;
    }
}
