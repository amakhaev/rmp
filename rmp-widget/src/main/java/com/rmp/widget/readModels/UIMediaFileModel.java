package com.rmp.widget.readModels;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Paths;

/**
 * Provides the UI media file model
 */
@Getter
@Setter
public class UIMediaFileModel {

    private int id;
    private String path;
    private int playlistId;

    /**
     * Gets the display name of media file
     */
    public String getDisplayName() {
        return Paths.get(this.path).getFileName().toString();
    }
}
