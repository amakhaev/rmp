package com.rmp.widget.skins.defaultSkin;

import com.rmp.widget.skins.PlaylistPanelSkin;
import com.rmp.widget.utilities.ResourceHelper;

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
}
