package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.*;

import java.awt.*;

/**
 * Provides the default skin of widget
 */
public class DefaultSkin implements RMPSkin {

    private static final Dimension SIZE = new Dimension(600, 450);

    private ControlPanelSkin controlPanelSkin;
    private PlaylistPanelSkin playlistPanelSkin;
    private MediaDetailPanelSkin mediaDetailPanelSkin;

    /**
     * Gets the size of widget
     *
     * @return the {@link Dimension} of widget
     */
    @Override
    public Dimension getSize() {
        return SIZE;
    }

    /**
     * Gets the background start color of widget
     */
    @Override
    public Color getBackgroundStartColor() {
        return Colors.CHARCOAL;
    }

    /**
     * Gets the background end color of widget
     */
    @Override
    public Color getBackgroundEndColor() {
        return Colors.IRON;
    }

    /**
     * Gets the button panel skin
     */
    @Override
    public ControlPanelSkin getControlPanelSkin() {
        if (this.controlPanelSkin == null) {
            this.controlPanelSkin = new DefaultControlPanelSkin(this.getSize());
        }
        return this.controlPanelSkin;
    }

    /**
     * Gets the playlist panel skin
     */
    @Override
    public com.rmp.widget.skins.PlaylistPanelSkin getPlaylistSkin() {
        if (this.playlistPanelSkin == null) {
            this.playlistPanelSkin = new DefaultPlaylistPanelSkin();
        }

        return this.playlistPanelSkin;
    }

    /**
     * Gets the skin of media detail component
     */
    @Override
    public MediaDetailPanelSkin getMediaDetailSkin() {
        if (this.mediaDetailPanelSkin == null) {
            this.mediaDetailPanelSkin = new DefaultMediaDetailPanelSkin();
        }

        return this.mediaDetailPanelSkin;
    }
}
