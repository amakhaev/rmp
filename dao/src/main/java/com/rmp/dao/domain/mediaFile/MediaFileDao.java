package com.rmp.dao.domain.mediaFile;

import java.util.List;

/**
 * Provides the DAO to make CRUD operations with media files
 */
public interface MediaFileDao {

    MediaFileDao INSTANCE = new MediaFileDaoImpl();

    /**
     * Creates the media files files
     *
     * @param mediaFile - the playlist files to be created
     * @return created {@link List<MediaFileModel>} instance.
     */
    MediaFileModel createMediaFile(MediaFileModel mediaFile);

    /**
     * Gets the list of media files by given playlist id
     *
     * @param playlistId - he playlist id
     * @return the {@link List<MediaFileModel>} instance
     */
    List<MediaFileModel> findAllByPlaylistId(int playlistId);

    /**
     * Deletes the media files by given ids.
     *
     * @param fileIds - the list of identifiers to delete
     */
    void deleteByFileIds(List<Integer> fileIds);
}
