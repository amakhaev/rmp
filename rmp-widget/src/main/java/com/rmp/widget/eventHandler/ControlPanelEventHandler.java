package com.rmp.widget.eventHandler;

/**
 * Provides the event handler to button's manipulate
 */
public interface ControlPanelEventHandler {

    /**
     * Handles the stop event
     */
    void onStop();

    /**
     * Handles the prev event
     */
    void onPrev();

    /**
     * Handles the play event
     */
    void onPlay();

    /**
     * Handles the next event
     */
    void onNext();

    /**
     * Handles the open config event
     */
    void onConfigOpen();

    /**
     * Handles the changing value of timeline
     *
     * @param value - new value of timeline
     */
    void onTimelineValueChanged(int value);
}
