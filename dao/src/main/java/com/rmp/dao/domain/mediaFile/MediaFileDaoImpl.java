package com.rmp.dao.domain.mediaFile;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.rmp.dao.configuration.DatabaseConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * Provides implementation of {@link MediaFileDao}
 */
@Slf4j
public class MediaFileDaoImpl implements MediaFileDao {

    private Dao<MediaFileEntity, Integer> mediaFileDao;
    private MediaFileMapper mapper;

    /**
     * Initialize new instance of {@link MediaFileDaoImpl}
     */
    @Inject
    public MediaFileDaoImpl(ConnectionSource connectionSource) {
        this.mapper = MediaFileMapper.INSTANCE;
        try {
            this.mediaFileDao = DaoManager.createDao(connectionSource, MediaFileEntity.class);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Creates the media files files
     *
     * @param mediaFile - the playlist files to be created
     * @return created {@link List<MediaFileModel>} instance.
     */
    @Override
    public MediaFileModel createMediaFile(MediaFileModel mediaFile) {
        try {
            return this.mapper.entityToModel(
                    this.mediaFileDao.createIfNotExists(this.mapper.modelToEntity(mediaFile))
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Gets the list of media files by given playlist id
     *
     * @param playlistId - he playlist id
     * @return the {@link List<MediaFileModel>} instance
     */
    @Override
    public List<MediaFileModel> findAllByPlaylistId(int playlistId) {
        try {
            return this.mapper.entitiesToModels(
                    this.mediaFileDao
                            .queryBuilder()
                            .orderBy(MediaFileEntity.CREATED_AT_FIELD, true)
                            .where()
                            .eq(MediaFileEntity.PLAYLIST_ID_FIELD, playlistId)
                            .query()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Finds the media file by given id
     *
     * @param id - the id of media file
     * @return the {@link MediaFileModel} instance
     */
    @Override
    public MediaFileModel findMediaFileById(int id) {
        try {
            return this.mapper.entityToModel(
                    this.mediaFileDao.queryBuilder().where().eq(MediaFileEntity.ID_FIELD, id).queryForFirst()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Deletes the media files by given ids.
     *
     * @param fileIds - the list of identifiers to delete
     */
    @Override
    public void deleteByFileIds(List<Integer> fileIds) {
        try {
            this.mediaFileDao.deleteIds(fileIds);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Gets the media file by given playlist id and path
     *
     * @param playlistId - the playlist id
     * @param path       - the path to file
     * @return the {@link MediaFileModel} instance
     */
    @Override
    public MediaFileModel findByPlaylistIdAndPath(int playlistId, String path) {
        try {
            return this.mapper.entityToModel(
                    this.mediaFileDao
                            .queryBuilder()
                            .where()
                            .eq(MediaFileEntity.PLAYLIST_ID_FIELD, playlistId)
                            .and()
                            .eq(MediaFileEntity.PATH_FIELD, path)
                            .queryForFirst()
            );
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
