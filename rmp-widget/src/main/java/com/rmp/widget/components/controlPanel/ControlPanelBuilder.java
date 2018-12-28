package com.rmp.widget.components.controlPanel;

import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.skins.ControlPanelSkin;

/**
 * Provides the builder for {@link ControlPanelComponent}
 */
public class ControlPanelBuilder {

    private ControlPanelSkin skin;
    private ControlPanelDataWatcher controlPanelDataWatcher;
    private ControlPanelEventHandler eventHandler;

    /**
     * Sets the skin for {@link ControlPanelComponent}
     *
     * @param skin - the skin that should be applied
     * @return the current {@link ControlPanelBuilder} instance
     */
    public ControlPanelBuilder setSkin(ControlPanelSkin skin) {
        this.skin = skin;
        return this;
    }

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
        if (this.skin == null) {
            throw new NullPointerException("Skin is required to build ControlPanelComponent");
        }

        ControlPanelComponent component = new ControlPanelComponent(this.skin);
        component.setControlPanelDataWatcher(this.controlPanelDataWatcher);
        component.setEventHandler(this.eventHandler);
        component.initialize();

        return component;
    }
}
