package com.rmp.widget.components.buttonsPanel;

import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.skins.ButtonPanelSkin;

/**
 * Provides the builder for {@link ButtonPanelComponent}
 */
public class ButtonPanelBuilder {

    private ButtonPanelSkin skin;
    private ControlPanelDataWatcher controlPanelDataWatcher;
    private ControlPanelEventHandler eventHandler;

    /**
     * Sets the skin for {@link ButtonPanelComponent}
     *
     * @param skin - the skin that should be applied
     * @return the current {@link ButtonPanelBuilder} instance
     */
    public ButtonPanelBuilder setSkin(ButtonPanelSkin skin) {
        this.skin = skin;
        return this;
    }

    /**
     * Sets the data watcher of buttons panel
     *
     * @param controlPanelDataWatcher - the data watcher for panel
     * @return the current {@link ButtonPanelBuilder} instance
     */
    public ButtonPanelBuilder setControlPanelDataWatcher(ControlPanelDataWatcher controlPanelDataWatcher) {
        this.controlPanelDataWatcher = controlPanelDataWatcher;
        return this;
    }

    /**
     * Sets the data event handler of buttons panel
     *
     * @param eventHandler - the event handler for panel
     * @return the current {@link ButtonPanelBuilder} instance
     */
    public ButtonPanelBuilder setEventHandler(ControlPanelEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        return this;
    }

    /**
     * Builds the {@link ButtonPanelComponent} instance by given arguments
     *
     * @return the {@link ButtonPanelComponent} instance
     */
    public ButtonPanelComponent build() {
        if (this.skin == null) {
            throw new NullPointerException("Skin is required to build ButtonPanelComponent");
        }

        ButtonPanelComponent component = new ButtonPanelComponent(this.skin);
        component.setControlPanelDataWatcher(this.controlPanelDataWatcher);
        component.setEventHandler(this.eventHandler);
        component.initialize();

        return component;
    }
}
