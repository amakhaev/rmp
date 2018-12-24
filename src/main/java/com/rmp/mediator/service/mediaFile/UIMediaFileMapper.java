package com.rmp.mediator.service.mediaFile;

import com.rmp.dao.domain.mediaFile.MediaFileModel;
import com.rmp.mediator.service.UIModelMapper;
import com.rmp.widget.readModels.UIMediaFileModel;

/**
 * Provides the mapper to work with UI models
 */
public class UIMediaFileMapper extends UIModelMapper<MediaFileModel, UIMediaFileModel> {
    /**
     * Initialize new instance of {@link UIModelMapper}
     */
    public UIMediaFileMapper() {
        super(MediaFileModel.class, UIMediaFileModel.class);
    }
}
