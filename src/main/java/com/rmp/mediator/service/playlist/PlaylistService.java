package com.rmp.mediator.service.playlist;

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

    /**
     * Gets the UI playlist model by given id
     *
     * @param id - the id of playlist
     * @return the {@link UIPlaylistModel} instance
     */
    public UIPlaylistModel getById(int id) {
        return this.mapper.dataModelToUIModel(this.playlistDao.findById(id));
    }

    /**
     * Gets the UI playlist model by given title
     *
     * @param title - the title of playlist
     * @return the {@link UIPlaylistModel} instance
     */
    public UIPlaylistModel getByTitle(String title) {
        return this.mapper.dataModelToUIModel(this.playlistDao.findByTitle(title));
    }
}