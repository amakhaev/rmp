package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.PlaylistPanelSkin;

import java.awt.*;

/**
 * Provides the builder for {@link PlaylistPanelComponent}
 */
public class PlaylistPanelBuilder {

    private PlaylistPanelSkin skin;
    private Dimension playlistPanelSize;
    private PlaylistDataWatcher dataWatcher;

    /**
     * Sets the skin for {@link PlaylistPanelComponent}
     *
     * @param skin - the skin that should be applied
     * @return the current {@link PlaylistPanelBuilder} instance
     */
    public PlaylistPanelBuilder setSkin(PlaylistPanelSkin skin) {
        this.skin = skin;
        return this;
    }

    /**
     * Sets the size of component
     *
     * @param playlistPanelSize - the dimension that should be applied to panel as preference size
     * @return the current {@link PlaylistPanelBuilder} instance
     */
    public PlaylistPanelBuilder setPlaylistPanelSize(Dimension playlistPanelSize) {
        this.playlistPanelSize = playlistPanelSize;
        return this;
    }

    /**
     * Sets the watcher of playlist component
     *
     * @param dataWatcher - the watcher
     * @return the current {@link PlaylistPanelBuilder} instance
     */
    public PlaylistPanelBuilder setDataWatcher(PlaylistDataWatcher dataWatcher) {
        this.dataWatcher = dataWatcher;
        return this;
    }

    /**
     * Builds the {@link PlaylistPanelComponent} instance by given arguments
     *
     * @return the {@link PlaylistPanelComponent} instance
     */
    public PlaylistPanelComponent build() {
        if (this.skin == null) {
            throw new NullPointerException("Skin is required to build PlaylistPanelComponent");
        }

        if (this.playlistPanelSize == null) {
            throw new NullPointerException("Panel size is required to build PlaylistPanelComponent");
        }

        if (this.dataWatcher == null) {
            throw new NullPointerException("Data watcher is required to build PlaylistPanelComponent");
        }

        PlaylistPanelComponent component = new PlaylistPanelComponent(this.skin, this.playlistPanelSize);
        component.setDataWatcher(this.dataWatcher);

        return component;
    }
}
