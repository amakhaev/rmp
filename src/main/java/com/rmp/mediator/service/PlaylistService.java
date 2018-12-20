package com.rmp.mediator.service;

import com.rmp.dao.domain.playlist.PlaylistDao;
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
        this.mapper = UIPlaylistMapper.INSTANCE;
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
}