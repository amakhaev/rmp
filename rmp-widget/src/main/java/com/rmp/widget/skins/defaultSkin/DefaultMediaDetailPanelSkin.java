package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.PairColor;
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
    public PairColor getArtPanelBackgroundGradientPalette() {
        return new PairColor(Colors.CHARCOAL, Colors.BLACK);
    }

    /**
     * Gets the default art URL
     */
    @Override
    public URL getDefaultArt() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.MEDIA_DETAIL_BACKGROUND_ICON);
    }

    /**
     * Gets the background color of total panel
     */
    @Override
    public Color getTotalCountBackgroundColor() {
        return Colors.BLACK;
    }

    /**
     * Gets the title color of total panel
     */
    @Override
    public Color getTotalCountTitleColor() {
        return Colors.PRIMARY;
    }

    /**
     * Gets the value color of total panel
     */
    @Override
    public Color getTotalCountValueColor() {
        return Colors.LIGHT_GREEN;
    }

    /**
     * Gets the background color of selected track panel
     */
    @Override
    public Color getSelectedTrackBackgroundColor() {
        return Colors.BLACK;
    }

    /**
     * Gets the title color of selected track panel
     */
    @Override
    public Color getSelectedTrackTitleColor() {
        return Colors.PRIMARY;
    }

    /**
     * Gets the value color of selected track panel
     */
    @Override
    public Color getSelectedTrackValueColor() {
        return Colors.LIGHT_GREEN;
    }
}
