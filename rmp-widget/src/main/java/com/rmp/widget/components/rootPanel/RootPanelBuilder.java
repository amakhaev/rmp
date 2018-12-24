package com.rmp.widget.components.rootPanel;

import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.skins.RMPSkin;

/**
 * Provides the builder for {@link RootPanelComponent}
 */
public class RootPanelBuilder {

    private RMPSkin skin;
    private PlaylistPanelBuilder playlistPanelBuilder;

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

        return new RootPanelComponent(this.skin, this.playlistPanelBuilder);
    }

}
