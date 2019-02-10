package com.rmp.dao.domain.state;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides the player state entity
 */
@Getter
@Setter
@DatabaseTable(tableName = "states")
public class StateEntity {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Provides the ID field name.
     */
    public static final String ID_FIELD = "id";

    /**
     * Provides the playlist id field name.
     */
    public static final String PLAYLIST_ID_FIELD = "playlist_id";

    /**
     * Provides the playlist file id field name.
     */
    public static final String PLAYLIST_FILE_ID_FIELD = "playlist_file_id";

    /**
     * Provides the playlist file id field name.
     */
    public static final String TIME_LABEL_ORDER_FIELD = "time_label_order";

    /**
     * Provides the modified at field name.
     */
    public static final String MODIFIED_AT_FIELD = "modified_at";

    /**
     * Provides the volume value field name.
     */
    public static final String VOLUME_VALUE_FIELD = "volume_value";

    /**
     * Provides the modified at field name.
     */
    public static final String IS_MUTE_FIELD = "is_mute";

    @DatabaseField(columnName = ID_FIELD, generatedId = true)
    private int id;

    @DatabaseField(columnName = PLAYLIST_ID_FIELD)
    private int playlistId;

    @DatabaseField(columnName = PLAYLIST_FILE_ID_FIELD)
    private Integer playlistFileId;

    @DatabaseField(columnName = MODIFIED_AT_FIELD)
    private String modifiedAtAsString = formatter.format(LocalDateTime.now());

    @DatabaseField(columnName = TIME_LABEL_ORDER_FIELD)
    private TimeLabelOrder timeLabelOrder;

    @DatabaseField(columnName = IS_MUTE_FIELD)
    private boolean isMute;

    @DatabaseField(columnName = VOLUME_VALUE_FIELD)
    private Integer volumeValue;
}
