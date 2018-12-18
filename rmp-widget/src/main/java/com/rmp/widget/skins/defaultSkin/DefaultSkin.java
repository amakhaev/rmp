package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.ButtonPanelSkin;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.RMPSkin;

import java.awt.*;

/**
 * Provides the default skin of widget
 */
public class DefaultSkin implements RMPSkin {

    private static final Dimension SIZE = new Dimension(600, 400);

    private ButtonPanelSkin buttonPanelSkin;
    private com.rmp.widget.skins.PlaylistPanelSkin playlistPanelSkin;

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
    public ButtonPanelSkin getButtonPanelSkin() {
        if (this.buttonPanelSkin == null) {
            this.buttonPanelSkin = new DefaultButtonPanelSkin();
        }
        return this.buttonPanelSkin;
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
}
