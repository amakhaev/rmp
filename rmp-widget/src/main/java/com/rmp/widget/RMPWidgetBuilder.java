package com.rmp.widget;

import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.eventHandler.MediaDetailEventHandler;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import lombok.Getter;

/**
 * Provides the builder to create instance of {@link RMPWidget}
 */
@Getter
public class RMPWidgetBuilder {

    private PlaylistDataWatcher playlistDataWatcher;
    private PlaylistEventHandler playlistEventHandler;
    private ControlPanelDataWatcher controlPanelDataWatcher;
    private ControlPanelEventHandler controlPanelEventHandler;
    private MediaDetailDataWatcher mediaDetailDataWatcher;
    private MediaDetailEventHandler mediaDetaileventHandler;

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
        RMPWidget widget = new RMPWidget();
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
}
