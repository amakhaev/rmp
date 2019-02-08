package com.rmp.dao.domain.state;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Provides the model to work with player state model
 */
@Getter
@Setter
public class StateModel {

    private int id;
    private int playlistId;
    private Integer playlistFileId;
    private LocalDateTime modifiedAt;
    private TimeLabelOrder timeLabelOrder;
    private boolean isMute;
}
