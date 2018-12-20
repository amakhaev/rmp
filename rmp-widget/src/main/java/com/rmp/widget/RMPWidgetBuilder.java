package com.rmp.widget;

import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
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
     * Sets the watcher of playlist component
     *
     * @param dataWatcher - the watcher
     * @return the current {@link RMPWidgetBuilder} instance
     */
    public RMPWidgetBuilder setDataWatcher(PlaylistDataWatcher dataWatcher) {
        this.playlistDataWatcher = dataWatcher;
        return this;
    }

    /**
     * Builds the widget by parameters from builder
     *
     * @return the {@link RMPWidget} instance
     */
    public RMPWidget build() {
        return new RMPWidget(this.getSkin(), this.playlistDataWatcher);
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
