package com.rmp.widget.skins;

import java.net.URL;

/**
 * Provides the skin of PlaylistPanelComponent
 */
public interface PlaylistPanelSkin {

    /**
     * Gets the play lists icon
     *
     * @return the {@link URL} to file
     */
    URL getPlaylistIcon();

    /**
     * Gets the open file icon
     *
     * @return the {@link URL} to file
     */
    URL getOpenFileIcon();

    /**
     * Gets the checked icon
     *
     * @return the {@link URL} to file
     */
    URL getCheckedFileIcon();

    /**
     * Gets the add icon
     *
     * @return the {@link URL} to file
     */
    URL getAddIcon();

    /**
     * Gets the small play icon
     *
     * @return the {@link URL} to file
     */
    URL getSmallPlayIcon();
}
