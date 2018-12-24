package com.rmp.mediator.service;

import com.rmp.dao.domain.playlist.PlaylistDao;
import com.rmp.dao.domain.playlist.PlaylistModel;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.util.List;

/**
 * Provides the service that worked with playlists
 */
public class PlaylistService {

    private UIPlaylistMapper mapper;
    private PlaylistDao playlistDao;

    /**
     * Initialize new instance of {@link PlaylistService}
     */
    public PlaylistService() {
        this.mapper = new UIPlaylistMapper();
        this.playlistDao = PlaylistDao.INSTANCE;
    }

    /**
     * Gets all playlists from database.
     *
     * @return the {@link List<com.rmp.widget.readModels.UIPlaylistModel>} instance
     */
    public List<UIPlaylistModel> getAllPlaylists() {
        return this.mapper.dataModelsToUIModels(this.playlistDao.findAll());
    }

    /**
     * Create the playlist
     *
     * @param title - the title of new playlist
     */
    public void createPlaylist(String title) {
        if (this.playlistDao.findByTitle(title) != null) {
            return;
        }

        PlaylistModel playlistModel = new PlaylistModel();
        playlistModel.setTitle(title);

        this.playlistDao.create(playlistModel);
    }
}