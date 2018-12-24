package com.rmp.mediator.service.playlist;

import com.rmp.dao.domain.playlist.PlaylistModel;
import com.rmp.mediator.service.UIModelMapper;
import com.rmp.widget.readModels.UIPlaylistModel;

/**
 * Provides the mapper from data model to ui model
 */
class UIPlaylistMapper extends UIModelMapper<PlaylistModel, UIPlaylistModel> {

    /**
     * Initialize new instance of {@link UIPlaylistMapper}
     */
    UIPlaylistMapper() {
        super(PlaylistModel.class, UIPlaylistModel.class);
    }
}