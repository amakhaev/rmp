package com.rmp.widget.skins;

import java.awt.*;
import java.net.URL;

/**
 * Provides the skin of ControlPanelComponent
 */
public interface ControlPanelSkin {

    /**
     * Gets the size of parent widget
     */
    Dimension getWidgetSize();

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

    /**
     * Gets the URL of VOLUME_PRESSED icon
     */
    URL getVolumePressedIconUrl();

    /**
     * Gets the URL of VOLUME icon
     */
    URL getVolumeIconUrl();

    // === Skin of timeline =========================

    /**
     * Gets the timeline background color
     */
    Color getTimelineBackgroundColor();

    /**
     * Gets the timeline border color
     */
    Color getTimelineBorderColor();

    /**
     * Gets the timeline cursor shadow color
     */
    Color getTimelineCursorShadowColor();

    /**
     * Gets the timeline shadow color
     */
    Color getTimelineShadowColor();

    /**
     * Gets the shadow color of label
     */
    Color getTimeLineLabelShadowColor();

    /**
     * Gets the foreground color of label
     */
    Color getTimeLineLabelForegroundColor();

    /**
     * Gets the gradient of cursor on volume panel
     */
    PairColor getVolumePanelCursorGradien();

    /**
     * Gets the color of slider on volume panel
     */
    Color getVolumePanelSliderShadowColor();
}
