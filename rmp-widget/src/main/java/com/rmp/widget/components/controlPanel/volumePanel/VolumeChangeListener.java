package com.rmp.widget.components.controlPanel.volumePanel;

/**
 * Provides the change volume listener
 */
@FunctionalInterface
public interface VolumeChangeListener {

    /**
     * Handles the changing volume
     */
    void onChanged(int value);

}
