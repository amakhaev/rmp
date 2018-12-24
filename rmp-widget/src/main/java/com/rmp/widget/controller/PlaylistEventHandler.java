package com.rmp.widget.controller;

/**
 * Provides the handler of event from playlist component.
 */
public interface PlaylistEventHandler {

    /**
     * Handles the create playlist event
     *
     * @param name - the name of new playlist
     */
    void onPlaylistCreate(String name);

}