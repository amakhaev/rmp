package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.MediaDetailPanelSkin;

import java.awt.*;

/**
 * Provides default implementation of MediaDetailPanelSkin
 */
public class DefaultMediaDetailPanelSkin implements MediaDetailPanelSkin {
    /**
     * Gets the background color if title panel
     */
    @Override
    public Color getTitlePanelBackgroundColor() {
        return Colors.BLACK;
    }

    /**
     * Gets the foreground color of title panel
     */
    @Override
    public Color getTitlePanelForegroundColor() {
        return Colors.PRIMARY;
    }
}
