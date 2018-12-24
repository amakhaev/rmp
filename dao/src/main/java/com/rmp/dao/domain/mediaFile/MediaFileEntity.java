package com.rmp.dao.domain.mediaFile;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides the entity to work with media file
 */
@Getter
@Setter
@DatabaseTable(tableName = "playlist_files")
public class MediaFileEntity {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Provides the ID field name.
     */
    public static final String ID_FIELD = "id";

    /**
     * Provides the path field name.
     */
    public static final String PATH_FIELD = "path";

    /**
     * Provides the created at field name.
     */
    public static final String CREATED_AT_FIELD = "created_at";

    /**
     * Provides the playlist id field name.
     */
    public static final String PLAYLIST_ID_FIELD = "playlist_id";

    @DatabaseField(columnName = ID_FIELD, generatedId = true)
    private int id;

    @DatabaseField(columnName = PATH_FIELD)
    private String path;

    @DatabaseField(columnName = CREATED_AT_FIELD)
    private String createdAtAsString = formatter.format(LocalDateTime.now());

    @DatabaseField(columnName = PLAYLIST_ID_FIELD)
    private int playlistId;

}