package com.rmp.mediator.service.mediaFile;

import com.rmp.dao.domain.mediaFile.MediaFileDao;
import com.rmp.dao.domain.mediaFile.MediaFileModel;
import com.rmp.widget.readModels.UIMediaFileModel;

import java.util.List;

/**
 * Provides the service to work with media files
 */
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
     * Deletes the media files ids
     *
     * @param mediaFileIds - the identifiers of media files to delete
     */
    public void deleteMediaFiles(List<Integer> mediaFileIds) {
        this.mediaFileDao.deleteByFileIds(mediaFileIds);
    }
}
