package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.GradientPalette;
import com.rmp.widget.skins.MediaDetailPanelSkin;
import com.rmp.widget.utilities.ResourceHelper;

import java.awt.*;
import java.net.URL;

/**
 * Provides default implementation of MediaDetailPanelSkin
 */
public class DefaultMediaDetailPanelSkin implements MediaDetailPanelSkin {

    private static final String SKIN_NAME = "skin_default";

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

    /**
     * Gets the background palette of gradient palette
     */
    @Override
    public GradientPalette getArtPanelBackgroundGradientPalette() {
        return new GradientPalette(Colors.BLACK, Colors.CHARCOAL);
    }

    /**
     * Gets the default art URL
     */
    @Override
    public URL getDefaultArt() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.MEDIA_DETAIL_BACKGROUND_ICON);
    }
}
