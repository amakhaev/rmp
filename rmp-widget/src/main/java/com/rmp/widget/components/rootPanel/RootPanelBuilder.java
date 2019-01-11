package com.rmp.widget.components.rootPanel;

import com.rmp.widget.components.controlPanel.ControlPanelBuilder;
import com.rmp.widget.components.mediaDetailPanel.MediaDetailBuilder;
import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.skins.RMPSkin;

/**
 * Provides the builder for {@link RootPanelComponent}
 */
public class RootPanelBuilder {

    private PlaylistPanelBuilder playlistPanelBuilder;
    private ControlPanelBuilder controlPanelBuilder;
    private MediaDetailBuilder mediaDetailBuilder;

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
     * @param controlPanelBuilder - the builder to set
     * @return current {@link RootPanelBuilder} instance
     */
    public RootPanelBuilder setControlPanelBuilder(ControlPanelBuilder controlPanelBuilder) {
        this.controlPanelBuilder = controlPanelBuilder;
        return this;
    }

    /**
     * Sets the media detail panel builder
     *
     * @param mediaDetailBuilder - the builder to set
     * @return current {@link RootPanelBuilder} instance
     */
    public RootPanelBuilder setMediaDetailBuilder(MediaDetailBuilder mediaDetailBuilder) {
        this.mediaDetailBuilder = mediaDetailBuilder;
        return this;
    }

    /**
     * Builds the {@link RootPanelComponent} from prepared parameters
     *
     * @return the {@link RootPanelComponent} instance
     */
    public RootPanelComponent build() {
        if (this.playlistPanelBuilder == null) {
            throw new NullPointerException("Playlist builder is required to create RootPanelComponent");
        }

        if (this.controlPanelBuilder == null) {
            throw new NullPointerException("Button panel builder is required to create RootPanelComponent");
        }

        if (this.mediaDetailBuilder == null) {
            throw new NullPointerException("Media detail panel builder is required to create RootPanelComponent");
        }

        RootPanelComponent rootPanelComponent = new RootPanelComponent();
        rootPanelComponent.initialize(this.playlistPanelBuilder, this.controlPanelBuilder, this.mediaDetailBuilder);
        return rootPanelComponent;
    }
}
