package com.rmp.dao.domain.playlist;

import java.util.List;

/**
 * Provides the DAO to work with play lists
 */
public interface PlaylistDao {

    /**
     * Finds the playlist by given title.
     *
     * @param title - the title of playlist
     * @return the {@link PlaylistEntity} object.
     */
    PlaylistModel findByTitle(String title);

    /**
     * Finds the playlist by given id.
     *
     * @param id - the id of playlist
     * @return the {@link PlaylistModel} object.
     */
    PlaylistModel findById(int id);

    /**
     * Creates the playlist.
     *
     * @param playlist - the playlist to create.
     * @return the created {@link PlaylistModel}
     */
    PlaylistModel create(PlaylistModel playlist);

    /**
     * Finds all playlist entities.
     *
     * @return the {@link List} playlist etities.
     */
    List<PlaylistModel> findAll();
}
