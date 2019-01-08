package com.rmp.widget.components.controlPanel;

/**
 * The listener of changing of time label value
 */
@FunctionalInterface
public interface TimeLabelOrderListener {

    /**
     * Handles the changing order of time label
     *
     * @param order - the value
     */
    void onChanged(TimeLabelOrder order);

}
