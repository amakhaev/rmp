package com.rmp.widget.components.rootPanel;

import com.rmp.widget.skins.RMPSkin;

/**
 * Provides the builder for {@link RootPanelComponent}
 */
public class RootPanelBuilder {

    private RMPSkin skin;

    /**
     * Sets the skin of {@link RootPanelComponent}.
     *
     * @param skin - the skin that should be applied to panel
     * @return current {@link RootPanelBuilder} instance
     */
    public RootPanelBuilder setSkin(RMPSkin skin) {
        this.skin = skin;
        return this;
    }

    /**
     * Builds the {@link RootPanelComponent} from prepared parameters
     *
     * @return the {@link RootPanelComponent} instance
     */
    public RootPanelComponent build() {
        if (this.skin == null) {
            throw new NullPointerException("Skin is required to build RootPanelComponent");
        }

        return new RootPanelComponent(skin);
    }

}
