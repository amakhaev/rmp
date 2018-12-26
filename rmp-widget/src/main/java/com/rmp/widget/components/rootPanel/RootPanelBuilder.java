package com.rmp.widget.components.rootPanel;

import com.rmp.widget.components.buttonsPanel.ButtonPanelBuilder;
import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.skins.RMPSkin;

/**
 * Provides the builder for {@link RootPanelComponent}
 */
public class RootPanelBuilder {

    private RMPSkin skin;
    private PlaylistPanelBuilder playlistPanelBuilder;
    private ButtonPanelBuilder buttonPanelBuilder;

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
     * Sets the builder playlist component
     *
     * @param playlistPanelBuilder - the playlist panel builder
     * @return the current {@link RootPanelBuilder} instance
     */
    public RootPanelBuilder setPlaylistPanelBuilder(PlaylistPanelBuilder playlistPanelBuilder) {
        this.playlistPanelBuilder = playlistPanelBuilder;
        return this;
    }

    /**
     * Sets the button panel builder
     *
     * @param buttonPanelBuilder - the builder to set
     * @return current {@link RootPanelBuilder} instance
     */
    public RootPanelBuilder setButtonPanelBuilder(ButtonPanelBuilder buttonPanelBuilder) {
        this.buttonPanelBuilder = buttonPanelBuilder;
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

        if (this.playlistPanelBuilder == null) {
            throw new NullPointerException("Playlist builder is required to create RootPanelComponent");
        }

        if (this.buttonPanelBuilder == null) {
            throw new NullPointerException("Button panel builder is required to create RootPanelComponent");
        }

        RootPanelComponent rootPanelComponent = new RootPanelComponent(this.skin);
        rootPanelComponent.initialize(this.playlistPanelBuilder, this.buttonPanelBuilder);
        return rootPanelComponent;
    }
}
