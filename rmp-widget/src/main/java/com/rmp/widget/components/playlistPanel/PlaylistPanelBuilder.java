package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.components.playlistDialog.NewPlaylistDialogComponent;
import com.rmp.widget.components.playlistDialog.OpenFileDialogComponent;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.PlaylistPanelSkin;

import java.awt.*;

/**
 * Provides the builder for {@link PlaylistPanelComponent}
 */
public class PlaylistPanelBuilder {

    private Dimension playlistPanelSize;
    private PlaylistDataWatcher dataWatcher;
    private PlaylistEventHandler playlistEventHandler;
    private NewPlaylistDialogComponent playlistDialogComponent;
    private OpenFileDialogComponent openFileDialogComponent;

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
     * Sets the watchers of playlist component
     *
     * @param dataWatcher - the watchers
     * @return the current {@link PlaylistPanelBuilder} instance
     */
    public PlaylistPanelBuilder setDataWatcher(PlaylistDataWatcher dataWatcher) {
        this.dataWatcher = dataWatcher;
        return this;
    }

    /**
     * Sets the playlist dialog component
     *
     * @param playlistDialogComponent - the dialog component to set
     * @return the current {@link PlaylistPanelBuilder} instance
     */
    public PlaylistPanelBuilder setPlaylistDialogComponent(NewPlaylistDialogComponent playlistDialogComponent) {
        this.playlistDialogComponent = playlistDialogComponent;
        return this;
    }

    /**
     * Sets the playlist eventHandler
     *
     * @param playlistEventHandler - the dialog eventHandler to set
     * @return the current {@link PlaylistPanelBuilder} instance
     */
    public PlaylistPanelBuilder setPlaylistEventHandler(PlaylistEventHandler playlistEventHandler) {
        this.playlistEventHandler = playlistEventHandler;
        return this;
    }

    /**
     * Sets the open file dialog component
     *
     * @param openFileDialogComponent - the dialog component to set
     * @return the current {@link PlaylistPanelBuilder} instance
     */
    public PlaylistPanelBuilder setOpenFileDialogComponent(OpenFileDialogComponent openFileDialogComponent) {
        this.openFileDialogComponent = openFileDialogComponent;
        return this;
    }

    /**
     * Builds the {@link PlaylistPanelComponent} instance by given arguments
     *
     * @return the {@link PlaylistPanelComponent} instance
     */
    public PlaylistPanelComponent build() {

        if (this.playlistPanelSize == null) {
            throw new NullPointerException("Panel size is required to build PlaylistPanelComponent");
        }

        if (this.dataWatcher == null) {
            throw new NullPointerException("Data watchers is required to build PlaylistPanelComponent");
        }

        PlaylistPanelComponent component = new PlaylistPanelComponent(this.playlistPanelSize, this.dataWatcher);
        component.setPlaylistDialogComponent(this.playlistDialogComponent);
        component.setPlaylistEventHandler(this.playlistEventHandler);
        component.setOpenFileDialogComponent(this.openFileDialogComponent);

        return component;
    }
}
