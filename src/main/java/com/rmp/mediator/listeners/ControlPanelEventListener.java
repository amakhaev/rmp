package com.rmp.mediator.listeners;

import com.rmp.mediator.mediaPlayer.PlayerMediator;
import com.rmp.widget.components.controlPanel.TimeLabelOrder;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;

/**
 * Provides base implementation of {@link ControlPanelEventHandler}
 */
public class ControlPanelEventListener implements ControlPanelEventHandler {

    private final PlayerMediator playerMediator;

    /**
     * Initialize new instance of {@link ControlPanelEventHandler}
     */
    public ControlPanelEventListener(PlayerMediator playerMediator) {
        this.playerMediator = playerMediator;
    }

    /**
     * Handles the stop event
     */
    @Override
    public void onStop() {
        this.playerMediator.stopPlaying();
    }

    /**
     * Handles the prev event
     */
    @Override
    public void onPrev() {
        this.playerMediator.playPrev();
    }

    /**
     * Handles the play event
     */
    @Override
    public void onPlay() {
        this.playerMediator.play();
    }

    /**
     * Handles the next event
     */
    @Override
    public void onNext() {
        this.playerMediator.playNext();
    }

    /**
     * Handles the open config event
     */
    @Override
    public void onConfigOpen() {
        System.out.println("Not implemented yet");
    }

    /**
     * Handles the changing value of timeline
     *
     * @param value - new value of timeline
     */
    @Override
    public void onTimelineValueChanged(int value) {
        this.playerMediator.setTimelineValue(value);
    }

    /**
     * Handles the changing of time label order
     *
     * @param order - the value
     */
    @Override
    public void onTimeLabelOrderChanged(TimeLabelOrder order) {
        this.playerMediator.setTimeLabelOrder(order);
    }
}