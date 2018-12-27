package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.PlaylistPanelSkin;
import com.rmp.widget.utilities.ResourceHelper;

import java.awt.*;
import java.net.URL;

/**
 * Provides default implementation of play list panel
 */
public class DefaultPlaylistPanelSkin implements PlaylistPanelSkin {

    private static final String SKIN_NAME = "skin_default";

    /**
     * Gets the play lists icon
     *
     * @return the {@link URL} to file
     */
    @Override
    public URL getPlaylistIcon() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PLAYLIST_COVER_ICON);
    }

    /**
     * Gets the open file icon
     *
     * @return the {@link URL} to file
     */
    @Override
    public URL getOpenFileIcon() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.ADD_LIST_ICON);
    }

    /**
     * Gets the checked icon
     *
     * @return the {@link URL} to file
     */
    @Override
    public URL getCheckedFileIcon() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.CHECK_ICON);
    }

    /**
     * Gets the add icon
     *
     * @return the {@link URL} to file
     */
    @Override
    public URL getAddIcon() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.ADD_ICON);
    }

    /**
     * Gets the small play icon
     *
     * @return the {@link URL} to file
     */
    @Override
    public URL getSmallPlayIcon() {
        return ResourceHelper.getPathToSkinIcon(SKIN_NAME, ResourceHelper.PLAY_BUTTON_ICON);
    }

    /**
     * Gets the color of text for label in list of media items
     */
    @Override
    public Color getListItemForegroundColor() {
        return Colors.PRIMARY_GRAY;
    }

    /**
     * Gets the color of label in list of media items that currently selected
     */
    @Override
    public Color getSelectedListItemBackgroundColor() {
        return Colors.IRON;
    }

    /**
     * Gets the color of label in list of media items that currently has focus
     */
    @Override
    public Color getFocusedListItemBackgroundColor() {
        return Colors.VERY_DARK_GREEN;
    }

    /**
     * Gets the color of background for label in list of media items
     */
    @Override
    public Color getListItemBackgroundColor() {
        return Colors.BLACK;
    }
}
