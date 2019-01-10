package com.rmp.widget.skins;

import java.awt.*;
import java.net.URL;

/**
 * Provides the skin of MediaDetailComponent
 */
public interface MediaDetailPanelSkin {

    /**
     * Gets the background color if title panel
     */
    Color getTitlePanelBackgroundColor();

    /**
     * Gets the foreground color of title panel
     */
    Color getTitlePanelForegroundColor();

    /**
     * Gets the background palette of gradient palette
     */
    GradientPalette getArtPanelBackgroundGradientPalette();

    /**
     * Gets the default art URL
     */
    URL getDefaultArt();
}
