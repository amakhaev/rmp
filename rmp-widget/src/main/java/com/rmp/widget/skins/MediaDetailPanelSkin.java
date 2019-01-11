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
    PairColor getArtPanelBackgroundGradientPalette();

    /**
     * Gets the default art URL
     */
    URL getDefaultArt();

    /**
     * Gets the background color of total panel
     */
    Color getTotalCountBackgroundColor();

    /**
     * Gets the title color of total panel
     */
    Color getTotalCountTitleColor();

    /**
     * Gets the value color of total panel
     */
    Color getTotalCountValueColor();

    /**
     * Gets the background color of selected track panel
     */
    Color getSelectedTrackBackgroundColor();

    /**
     * Gets the title color of selected track panel
     */
    Color getSelectedTrackTitleColor();

    /**
     * Gets the value color of selected track panel
     */
    Color getSelectedTrackValueColor();
}
