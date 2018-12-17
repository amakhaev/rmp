package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.ButtonPanelSkin;
import com.rmp.widget.utilities.ResourceHelper;

import java.net.URL;

/**
 * Provides the default skin of button panel
 */
public class DefaultButtonPanelSkin implements ButtonPanelSkin {

    private static final String SKIN_NAME = "skin_default";

    /**
     * Gets the URL of STOP icon
     */
    @Override
    public URL getStopIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.STOP_ICON);
    }

    /**
     * Gets the URL of STOP_PRESSED icon
     */
    @Override
    public URL getStopPressedIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.STOP_PRESSED_ICON);
    }

    /**
     * Gets the URL of PREVIOUS icon
     */
    @Override
    public URL getPreviousIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PREVIOUS_ICON);
    }

    /**
     * Gets the URL of PREVIOUS_PRESSED icon
     */
    @Override
    public URL getPreviousPressedIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PREVIOUS_PRESSED_ICON);
    }

    /**
     * Gets the URL of NEXT icon
     */
    @Override
    public URL getNextIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.NEXT_ICON);
    }

    /**
     * Gets the URL of NEXT_PRESSED icon
     */
    @Override
    public URL getNextPressedIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.NEXT_PRESSED_ICON);
    }

    /**
     * Gets the URL of CONFIG icon
     */
    @Override
    public URL getConfigIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.CONFIG_ICON);
    }

    /**
     * Gets the URL of CONFIG_PRESSED icon
     */
    @Override
    public URL getConfigPressedIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.CONFIG_PRESSED_ICON);
    }

    /**
     * Gets the URL of PLAY icon
     */
    @Override
    public URL getPlayIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PLAY_ICON);
    }

    /**
     * Gets the URL of PLAY_PRESSED icon
     */
    @Override
    public URL getPlayPressedIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PLAY_PRESSED_ICON);
    }

    /**
     * Gets the URL of PAUSE icon
     */
    @Override
    public URL getPauseIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PAUSE_ICON);
    }

    /**
     * Gets the URL of PAUSE_PRESSED icon
     */
    @Override
    public URL getPausePressedIconUrl() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PAUSE_PRESSED_ICON);
    }
}
