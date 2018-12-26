package com.rmp.widget.utilities;

import lombok.experimental.UtilityClass;

import java.net.URL;

/**
 * Provides the helper to work with icons
 */
@UtilityClass
public class ResourceHelper {

    public static final String PLAY_ICON = "Aqua-Play-icon.png";
    public static final String PLAY_PRESSED_ICON = "Aqua-Play-icon-pressed.png";

    public static final String STOP_ICON = "Aqua-Stop-icon.png";
    public static final String STOP_PRESSED_ICON = "Aqua-Stop-icon-pressed.png";

    public static final String CONFIG_ICON = "Aqua-Config-icon.png";
    public static final String CONFIG_PRESSED_ICON = "Aqua-Config-icon-pressed.png";

    public static final String NEXT_ICON = "Aqua-Next-icon.png";
    public static final String NEXT_PRESSED_ICON = "Aqua-Next-icon-pressed.png";

    public static final String PAUSE_ICON = "Aqua-Pause-icon.png";
    public static final String PAUSE_PRESSED_ICON = "Aqua-Pause-icon-pressed.png";

    public static final String PREVIOUS_ICON = "Aqua-Previous-icon.png";
    public static final String PREVIOUS_PRESSED_ICON = "Aqua-Previous-icon-pressed.png";

    public static final String PLAYLIST_COVER_ICON = "playlist-cover.png";

    public static final String ADD_ICON = "add-icon.png";
    public static final String ADD_LIST_ICON = "add-list-icon.png";
    public static final String CHECK_ICON = "check-icon.png";
    public static final String PLAY_BUTTON_ICON = "play-button-icon.png";

    /**
     * Gets the full path to resource.
     *
     * @param skinName - the name of skin
     * @param iconName - the name of icon
     * @return the path
     */
    public URL getPathToSkinIcon(String skinName, String iconName) {
        return ResourceHelper.class.getClassLoader().getResource(skinName + "/" + iconName);
    }
}
