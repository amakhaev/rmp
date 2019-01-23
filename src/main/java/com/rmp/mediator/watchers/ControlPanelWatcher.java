package com.rmp.mediator.watchers;

import com.rmp.widget.components.controlPanel.TimeLabelOrder;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.ReplayDataObserver;

/**
 * Provides the implementation of {@link ControlPanelDataWatcher}
 */
public final class ControlPanelWatcher implements ControlPanelDataWatcher {

    private final ReplayDataObserver<Boolean> isPlayingObserver;
    private final ReplayDataObserver<Long> sliderValueChangedObserver;
    private final ReplayDataObserver<Long> sliderLengthChangedObserver;
    private final ReplayDataObserver<TimeLabelOrder> timeLabelOrderChangedObserver;

    /**
     * Initialize new instance of {@link ControlPanelDataWatcher}
     */
    public ControlPanelWatcher() {
        this.sliderValueChangedObserver = new ReplayDataObserver<>();
        this.sliderLengthChangedObserver = new ReplayDataObserver<>();
        this.isPlayingObserver = new ReplayDataObserver<>();
        this.timeLabelOrderChangedObserver = new ReplayDataObserver<>();
    }

    /**
     * Provides the observer that indicates when player is playing
     */
    @Override
    public ReplayDataObserver<Boolean> getIsPlayingObserver() {
        return this.isPlayingObserver;
    }

    /**
     * Gets the observer that indicates when slider value was changed
     */
    @Override
    public ReplayDataObserver<Long> getTimelineValueChangedObserver() {
        return this.sliderValueChangedObserver;
    }

    /**
     * Gets the observer that indicates when slider length was changed
     */
    @Override
    public ReplayDataObserver<Long> getTimelineLengthChangedObserver() {
        return this.sliderLengthChangedObserver;
    }

    /**
     * Gets the observer that indicates when time label order was changed
     */
    @Override
    public ReplayDataObserver<TimeLabelOrder> getTimeLabelOrderChangedObserver() {
        return this.timeLabelOrderChangedObserver;
    }
}
