package com.rmp.widget.components.controlPanel;

import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.skins.ControlPanelSkin;

/**
 * Provides the builder for {@link ControlPanelComponent}
 */
public class ControlPanelBuilder {

    private ControlPanelDataWatcher controlPanelDataWatcher;
    private ControlPanelEventHandler eventHandler;

    /**
     * Sets the data watcher of buttons panel
     *
     * @param controlPanelDataWatcher - the data watcher for panel
     * @return the current {@link ControlPanelBuilder} instance
     */
    public ControlPanelBuilder setControlPanelDataWatcher(ControlPanelDataWatcher controlPanelDataWatcher) {
        this.controlPanelDataWatcher = controlPanelDataWatcher;
        return this;
    }

    /**
     * Sets the data event handler of buttons panel
     *
     * @param eventHandler - the event handler for panel
     * @return the current {@link ControlPanelBuilder} instance
     */
    public ControlPanelBuilder setEventHandler(ControlPanelEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        return this;
    }

    /**
     * Builds the {@link ControlPanelComponent} instance by given arguments
     *
     * @return the {@link ControlPanelComponent} instance
     */
    public ControlPanelComponent build() {
        ControlPanelComponent component = new ControlPanelComponent();
        component.setControlPanelDataWatcher(this.controlPanelDataWatcher);
        component.setEventHandler(this.eventHandler);
        component.initialize();

        return component;
    }
}
