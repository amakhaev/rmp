package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.utilities.ImageUtility;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides the context menu for playlist
 */
class PlaylistContextMenu {

    private final ImageIcon checkIcon;
    private final ImageIcon addIcon;

    private List<JMenuItem> anyButtons;
    private Map<Integer, JMenuItem> playlistButtons;
    private JPopupMenu popup;

    /**
     * Initialize new instance of {@link PlaylistContextMenu}
     */
    PlaylistContextMenu(URL checkIcon, URL addIcon) {
        this.checkIcon = new ImageIcon(ImageUtility.getScaledImage(new ImageIcon(checkIcon).getImage(), 18, 18));
        this.addIcon = new ImageIcon(ImageUtility.getScaledImage(new ImageIcon(addIcon).getImage(), 18, 18));
        this.anyButtons = new ArrayList<>();
        this.playlistButtons = new HashMap<>();
        this.popup = new JPopupMenu();
    }

    /**
     * Adds new item to popup menu
     *
     * @param title - the title of menu item
     * @param enableAddIcon - indicates when add icon should be added
     * @param action - the action of menu item
     */
    void addButton(String title, boolean enableAddIcon, Runnable action) {
        JMenuItem menuItem = this.createMenuItem(title, enableAddIcon, action);
        this.anyButtons.add(menuItem);
    }

    /**
     * Adds new item to popup menu
     *
     * @param playlistId - the id of playlist
     * @param title - the title of menu item
     * @param action - the action of menu item
     */
    void addPlaylistButton(int playlistId, String title, Runnable action) {
        JMenuItem checkBoxMenuItem = new JMenuItem(title);
        checkBoxMenuItem.addActionListener(actionEvent -> action.run());
        this.playlistButtons.put(playlistId, checkBoxMenuItem);
    }

    /**
     * Sets the check state of playlist button.
     *
     * @param playlistId - the playlist id to check
     * return true when playlist was successfully checked, false otherwise
     */
    boolean setCheckedPlaylist(int playlistId) {
        if (this.playlistButtons.isEmpty() || !this.playlistButtons.containsKey(playlistId)) {
            return false;
        }

        this.playlistButtons.forEach((key, value) -> value.setIcon(null));
        this.playlistButtons.get(playlistId).setIcon(this.checkIcon);

        return true;
    }

    /**
     * Creates the context menu
     *
     * @return the {@link JPopupMenu} instance.
     */
    JPopupMenu update() {
        this.popup.removeAll();
        this.anyButtons.forEach(menuItem -> this.popup.add(menuItem));

        if (!this.playlistButtons.isEmpty()) {
            this.popup.addSeparator();
            this.playlistButtons.forEach((key, value) -> this.popup.add(value));
        }
        this.popup.repaint();

        return this.popup;
    }

    private JMenuItem createMenuItem(String title, boolean enableAddIcon, Runnable action) {
        JMenuItem menuItem = new JMenuItem(title);

        if (enableAddIcon) {
            menuItem.setIcon(this.addIcon);
        }

        menuItem.addActionListener(actionEvent -> action.run());
        return menuItem;
    }
}