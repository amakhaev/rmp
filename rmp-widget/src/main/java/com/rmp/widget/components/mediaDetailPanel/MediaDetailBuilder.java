package com.rmp.widget.components.mediaDetailPanel;

import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.eventHandler.MediaDetailEventHandler;
import com.rmp.widget.skins.MediaDetailPanelSkin;

/**
 * Provides the builder for {@link MediaDetailComponent}
 */
public class MediaDetailBuilder {

    private MediaDetailPanelSkin skin;
    private MediaDetailDataWatcher mediaDetailDataWatcher;
    private MediaDetailEventHandler eventHandler;


    /**
     * Sets the skin for {@link MediaDetailPanelSkin}
     *
     * @param skin - the skin that should be applied
     * @return the current {@link MediaDetailBuilder} instance
     */
    public MediaDetailBuilder setSkin(MediaDetailPanelSkin skin) {
        this.skin = skin;
        return this;
    }

    /**
     * Sets the data watcher for component
     *
     * @param mediaDetailDataWatcher - the data watcher instance
     * @return the current {@link MediaDetailBuilder} instance
     */
    public MediaDetailBuilder setMediaDetailDataWatcher(MediaDetailDataWatcher mediaDetailDataWatcher) {
        this.mediaDetailDataWatcher = mediaDetailDataWatcher;
        return this;
    }

    /**
     * Sets the event handler for component
     *
     * @param eventHandler - the event handler component
     * @return the current {@link MediaDetailBuilder} instance
     */
    public MediaDetailBuilder setEventHandler(MediaDetailEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        return this;
    }

    /**
     * Builds the {@link MediaDetailComponent} instance by given arguments
     *
     * @return the {@link MediaDetailComponent} instance
     */
    public MediaDetailComponent build() {
        if (this.skin == null) {
            throw new NullPointerException("Skin is required to build MediaDetailComponent");
        }

        MediaDetailComponent component = new MediaDetailComponent(this.skin);
        component.setDataWatcher(this.mediaDetailDataWatcher);
        component.setEventHandler(this.eventHandler);

        component.initialize();
        return component;
    }
}
