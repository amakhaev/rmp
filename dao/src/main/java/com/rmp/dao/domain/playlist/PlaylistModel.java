package com.rmp.dao.domain.playlist;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The model with playlist data
 */
@Getter
@Setter
public class PlaylistModel {

    private int id;
    private String title;
    private LocalDateTime createdAt;

}

