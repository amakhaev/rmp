package com.rmp.dao.domain.mediaFile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Provides the model to work with playlist file
 */
@Getter
@Setter
public class MediaFileModel {

    private int id;
    private String path;
    private LocalDateTime createdAt;
    private int playlistId;

}