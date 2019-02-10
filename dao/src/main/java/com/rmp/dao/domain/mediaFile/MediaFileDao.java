package com.rmp.dao.domain.mediaFile;

import java.util.List;

/**
 * Provides the DAO to make CRUD operations with media files
 */
public interface MediaFileDao {

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
     * Finds the media file by given id
     *
     * @param id - the id of media file
     * @return the {@link MediaFileModel} instance
     */
    MediaFileModel findMediaFileById(int id);

    /**
     * Deletes the media files by given ids.
     *
     * @param fileIds - the list of identifiers to delete
     */
    void deleteByFileIds(List<Integer> fileIds);

    /**
     * Gets the media file by given playlist id and path
     *
     * @param playlistId - the playlist id
     * @param path - the path to file
     * @return the {@link MediaFileModel} instance
     */
    MediaFileModel findByPlaylistIdAndPath(int playlistId, String path);
}
