package com.rmp.widget.components.buttonsPanel;

import com.rmp.widget.skins.ButtonPanelSkin;

/**
 * Provides the builder for {@link ButtonPanelComponent}
 */
public class ButtonPanelBuilder {

    private ButtonPanelSkin skin;

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
     * Builds the {@link ButtonPanelComponent} instance by given arguments
     *
     * @return the {@link ButtonPanelComponent} instance
     */
    public ButtonPanelComponent build() {
        if (this.skin == null) {
            throw new NullPointerException("Skin is required to build ButtonPanelComponent");
        }

        return new ButtonPanelComponent(this.skin);
    }

}
