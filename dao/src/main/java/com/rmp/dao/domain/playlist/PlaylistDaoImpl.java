package com.rmp.dao.domain.playlist;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.rmp.dao.configuration.DatabaseConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * Provides the implementation of {@link PlaylistDao}
 */
@Slf4j
public class PlaylistDaoImpl implements PlaylistDao {

    private PlaylistMapper mapper;
    private Dao<PlaylistEntity, Integer> playlistDao;

    /**
     * Initialize new instance of {@link PlaylistDaoImpl}
     */
    @Inject
    public PlaylistDaoImpl(ConnectionSource connectionSource) {
        this.mapper = PlaylistMapper.INSTANCE;
        try {
            this.playlistDao = DaoManager.createDao(connectionSource, PlaylistEntity.class);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Finds the playlist by given title.
     *
     * @param title - the title of playlist
     * @return the {@link PlaylistEntity} object.
     */
    @Override
    public PlaylistModel findByTitle(String title) {
        try {
            return this.mapper.entityToModel(
                    this.playlistDao.queryBuilder().where().eq(PlaylistEntity.TITLE_FIELD, title).queryForFirst()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Finds the playlist by given id.
     *
     * @param id - the id of playlist
     * @return the {@link PlaylistModel} object.
     */
    @Override
    public PlaylistModel findById(int id) {
        try {
            return this.mapper.entityToModel(
                    this.playlistDao.queryBuilder().where().eq(PlaylistEntity.ID_FIELD, id).queryForFirst()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Creates the playlist.
     *
     * @param playlist - the playlist to create.
     * @return the created {@link PlaylistModel}
     */
    @Override
    public PlaylistModel create(PlaylistModel playlist) {
        try {
            this.playlistDao.create(this.mapper.modelToEntity(playlist));
            return this.findByTitle(playlist.getTitle());
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Finds all playlist entities.
     *
     * @return the {@link List} playlist etities.
     */
    @Override
    public List<PlaylistModel> findAll() {
        try {
            return this.mapper.entitiesToModels(
                    this.playlistDao.queryBuilder().query()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
