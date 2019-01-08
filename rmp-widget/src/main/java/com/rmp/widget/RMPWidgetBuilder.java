package com.rmp.widget;

import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.eventHandler.MediaDetailEventHandler;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import com.rmp.widget.skins.RMPSkin;
import com.rmp.widget.skins.Skin;
import com.rmp.widget.skins.defaultSkin.DefaultSkin;
import lombok.Getter;

/**
 * Provides the builder to create instance of {@link RMPWidget}
 */
@Getter
public class RMPWidgetBuilder {

    private Skin skin;
    private PlaylistDataWatcher playlistDataWatcher;
    private PlaylistEventHandler playlistEventHandler;
    private ControlPanelDataWatcher controlPanelDataWatcher;
    private ControlPanelEventHandler controlPanelEventHandler;
    private MediaDetailDataWatcher mediaDetailDataWatcher;
    private MediaDetailEventHandler mediaDetaileventHandler;

    /**
     * Initialize new instance of {@link RMPWidgetBuilder}
     */
    public RMPWidgetBuilder() {
        this.skin = Skin.DEFAULT;
    }

    /**
     * Sets the skin of widget
     *
     * @param skin - the skin to use
     * @return current {@link RMPWidgetBuilder} instance.
     */
    public RMPWidgetBuilder setSkin(Skin skin) {
        if (skin == null) {
            throw new IllegalArgumentException("Skin is required parameter");
        }
        this.skin = skin;
        return this;
    }

    /**
     * Sets the ui of playlist component
     *
     * @param dataWatcher - the ui
     * @return the current {@link RMPWidgetBuilder} instance
     */
    public RMPWidgetBuilder setPlaylistDataWatcher(PlaylistDataWatcher dataWatcher) {
        this.playlistDataWatcher = dataWatcher;
        return this;
    }

    /**
     * Sets the playlist eventHandler
     *
     * @param playlistEventHandler - the dialog eventHandler to set
     * @return the current {@link RMPWidgetBuilder} instance
     */
    public RMPWidgetBuilder setPlaylistEventHandler(PlaylistEventHandler playlistEventHandler) {
        this.playlistEventHandler = playlistEventHandler;
        return this;
    }

    /**
     * Sets the data watcher of buttons panel
     *
     * @param controlPanelDataWatcher - the data watcher for panel
     * @return the current {@link RMPWidgetBuilder} instance
     */
    public RMPWidgetBuilder setControlPanelDataWatcher(ControlPanelDataWatcher controlPanelDataWatcher) {
        this.controlPanelDataWatcher = controlPanelDataWatcher;
        return this;
    }

    /**
     * Sets the data event handler of buttons panel
     *
     * @param eventHandler - the event handler for panel
     * @return the current {@link RMPWidgetBuilder} instance
     */
    public RMPWidgetBuilder setControlPanelEventHandler(ControlPanelEventHandler eventHandler) {
        this.controlPanelEventHandler = eventHandler;
        return this;
    }

    /**
     * Sets the data watcher for component
     *
     * @param mediaDetailDataWatcher - the data watcher instance
     * @return the current {@link RMPWidgetBuilder} instance
     */
    public RMPWidgetBuilder setMediaDetailDataWatcher(MediaDetailDataWatcher mediaDetailDataWatcher) {
        this.mediaDetailDataWatcher = mediaDetailDataWatcher;
        return this;
    }

    /**
     * Sets the event handler for component
     *
     * @param eventHandler - the event handler component
     * @return the current {@link RMPWidgetBuilder} instance
     */
    public RMPWidgetBuilder setMediaDetailEventHandler(MediaDetailEventHandler eventHandler) {
        this.mediaDetaileventHandler = eventHandler;
        return this;
    }

    /**
     * Builds the widget by parameters from builder
     *
     * @return the {@link RMPWidget} instance
     */
    public RMPWidget build() {
        RMPWidget widget = new RMPWidget(this.getSkin());
        widget.initialize(
                this.playlistDataWatcher,
                this.playlistEventHandler,
                this.controlPanelDataWatcher,
                this.controlPanelEventHandler,
                this.mediaDetailDataWatcher,
                this.mediaDetaileventHandler
        );

        return widget;
    }

    private RMPSkin getSkin() {
        switch (this.skin) {
            case DEFAULT:
                return new DefaultSkin();
            default:
                return new DefaultSkin();
        }
    }
}
