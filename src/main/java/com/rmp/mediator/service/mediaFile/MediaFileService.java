package com.rmp.mediator.service.mediaFile;

import com.google.inject.Singleton;
import com.rmp.dao.domain.mediaFile.MediaFileDao;
import com.rmp.dao.domain.mediaFile.MediaFileModel;
import com.rmp.widget.readModels.UIMediaFileModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides the service to work with media files
 */
@Singleton
public class MediaFileService {

    private final MediaFileDao mediaFileDao;
    private final UIMediaFileMapper mapper;

    /**
     * Initialize new instance of {@link MediaFileService}
     */
    public MediaFileService() {
        this.mediaFileDao = MediaFileDao.INSTANCE;
        this.mapper = new UIMediaFileMapper();
    }

    /**
     * Creates new media file in database
     *
     * @param mediaFile - the media files to create
     * @return created media file
     */
    public UIMediaFileModel createMediaFile(UIMediaFileModel mediaFile) {
        MediaFileModel createdMediaFile = this.mediaFileDao.createMediaFile(
                this.mapper.UiModelToDataModel(mediaFile)
        );

        return this.mapper.dataModelToUIModel(createdMediaFile);
    }

    /**
     * Gets all media files by given playlist id
     *
     * @param playlistId - the playlist id to search
     * @return the media files
     */
    public List<UIMediaFileModel> getByPlaylistId(int playlistId) {
        return this.mapper.dataModelsToUIModels(
                this.mediaFileDao.findAllByPlaylistId(playlistId)
        );
    }

    /**
     * Gets media files path from playlist
     *
     * @param playlistId - the playlist if
     * @return the {@link List<String>} instance
     */
    public List<String> getPathsFromPlaylist(int playlistId) {
        List<UIMediaFileModel> models = this.getByPlaylistId(playlistId);
        return models.stream().map(UIMediaFileModel::getPath).collect(Collectors.toList());
    }

    /**
     * Finds index of media file in playlist by given id
     *
     * @param playlistId - the playlist id
     * @param mediaFileId - the media file id
     * @return index of found media file or -1 if not found
     */
    public int findMediaFileIndexInPlaylist(int playlistId, int mediaFileId) {
        List<UIMediaFileModel> models = this.getByPlaylistId(playlistId);
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getId() == mediaFileId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets media file by given id
     *
     * @param id - the id to search
     * @return the media file
     */
    public UIMediaFileModel getById(int id) {
        return this.mapper.dataModelToUIModel(this.mediaFileDao.findMediaFileById(id));
    }

    /**
     * Deletes the media files ids
     *
     * @param mediaFileIds - the identifiers of media files to delete
     */
    public void deleteMediaFiles(List<Integer> mediaFileIds) {
        this.mediaFileDao.deleteByFileIds(mediaFileIds);
    }

    /**
     * Gets the media file by given playlist id and path
     *
     * @param playlistId - the playlist id
     * @param path - the path to file
     * @return the {@link UIMediaFileModel} instance
     */
    public UIMediaFileModel getByPlaylistIdAndPath(int playlistId, String path) {
        return this.mapper.dataModelToUIModel(this.mediaFileDao.findByPlaylistIdAndPath(playlistId, path));
    }
}
