package com.rmp.dao.domain.playlist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides the playlist entity
 */
@Getter
@Setter
@DatabaseTable(tableName = "playlists")
public class PlaylistEntity {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Provides the ID field name.
     */
    public static final String ID_FIELD = "id";

    /**
     * Provides the title field name.
     */
    public static final String TITLE_FIELD = "title";

    /**
     * Provides the created at field name.
     */
    public static final String CREATED_AT_FIELD = "created_at";

    @DatabaseField(columnName = ID_FIELD, generatedId = true)
    private int id;

    @DatabaseField(columnName = TITLE_FIELD)
    private String title;

    @DatabaseField(columnName = CREATED_AT_FIELD)
    private String createdAtAsString = formatter.format(LocalDateTime.now());
}