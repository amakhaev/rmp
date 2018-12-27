package com.rmp.mediator.factory;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.vlcPlayer.mediaData.MediaPlaylist;
import com.rmp.vlcPlayer.mediaData.SuccessivelyPlaylist;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Provides the factory to create media playlist
 */
@UtilityClass
public class MediaPlaylistFactory {

    /**
     * Creates the media playlist by given state
     *
     * @param stateModel - the state to get initial data
     * @param mediaFileService - the {@link MediaFileService} instance
     * @return created {@link MediaPlaylist} instance
     */
    public MediaPlaylist create(StateModel stateModel, MediaFileService mediaFileService) {
        List<String> paths = mediaFileService.getPathsFromPlaylist(stateModel.getPlaylistId());

        Integer startMediaFileId = stateModel.getPlaylistFileId();
        int startIndex = 0;
        if (startMediaFileId != null) {
            startIndex = mediaFileService.findMediaFileIndexInPlaylist(stateModel.getPlaylistId(), startMediaFileId);
        }

        return new SuccessivelyPlaylist(paths, startIndex == -1 ? 0 : startIndex);
    }

}
