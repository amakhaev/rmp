package com.rmp.mediator.listeners;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rmp.mediator.mediaPlayer.PlayerMediator;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * Provides the implementation of event handler from UI
 */
@Slf4j
@Singleton
public class PlaylistEventListener implements PlaylistEventHandler {

    private final PlayerMediator playerMediator;

    /**
     * Initialize new instance of {@link PlaylistEventListener}
     */
    @Inject
    public PlaylistEventListener(PlayerMediator playerMediator) {
        this.playerMediator = playerMediator;
    }

    /**
     * Handles the create playlist event
     *
     * @param title - the name of new playlist
     */
    @Override
    public void onPlaylistCreate(String title) {
        this.playerMediator.createPlaylist(title);
    }

    /**
     * Handles the selecting of playlist from list
     *
     * @param id - the identifier of selected playlist
     */
    @Override
    public void onPlaylistSelected(int id) {
        this.playerMediator.setActivePlaylist(id);
    }

    /**
     * Handles the selecting of files
     *
     * @param files - the files to be handled
     */
    @Override
    public void onFilesSelected(List<File> files) {
        this.playerMediator.addMediaFilesToActivePlaylist(files);
    }

    /**
     * Handles the deleting of media files
     *
     * @param mediaFileIds - the media files ids
     */
    @Override
    public void onMediaFilesDeleted(List<Integer> mediaFileIds) {
        this.playerMediator.deleteMediaFiles(mediaFileIds);
    }

    /**
     * Handles double click by media file item in playlist
     *
     * @param mediaFileId - the media file id
     */
    @Override
    public void onMediaItemDoubleClick(int mediaFileId) {
        this.playerMediator.play(mediaFileId);
    }
}
