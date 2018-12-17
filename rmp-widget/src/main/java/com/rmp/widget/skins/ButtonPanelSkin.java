package com.rmp.widget.skins;

import java.net.URL;

/**
 * Provides the skin of ButtonPanelComponent
 */
public interface ButtonPanelSkin {

    /**
     * Gets the URL of STOP icon
     */
    URL getStopIconUrl();

    /**
     * Gets the URL of STOP_PRESSED icon
     */
    URL getStopPressedIconUrl();

    /**
     * Gets the URL of PREVIOUS icon
     */
    URL getPreviousIconUrl();

    /**
     * Gets the URL of PREVIOUS_PRESSED icon
     */
    URL getPreviousPressedIconUrl();

    /**
     * Gets the URL of NEXT icon
     */
    URL getNextIconUrl();

    /**
     * Gets the URL of NEXT_PRESSED icon
     */
    URL getNextPressedIconUrl();

    /**
     * Gets the URL of CONFIG icon
     */
    URL getConfigIconUrl();

    /**
     * Gets the URL of CONFIG_PRESSED icon
     */
    URL getConfigPressedIconUrl();

    /**
     * Gets the URL of PLAY icon
     */
    URL getPlayIconUrl();

    /**
     * Gets the URL of PLAY_PRESSED icon
     */
    URL getPlayPressedIconUrl();

    /**
     * Gets the URL of PAUSE icon
     */
    URL getPauseIconUrl();

    /**
     * Gets the URL of PAUSE_PRESSED icon
     */
    URL getPausePressedIconUrl();
}
