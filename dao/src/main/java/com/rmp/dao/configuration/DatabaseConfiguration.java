package com.rmp.dao.configuration;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.rmp.dao.utils.ConfigReader;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * Provides the database configuration
 */
@Slf4j
public class DatabaseConfiguration {

    private static ConnectionSource sqliteConnection;

    private DatabaseConfiguration() {}

    public static ConnectionSource getConnection() {
        if (sqliteConnection == null) {
            doConnect();
        }

        return sqliteConnection;
    }

    private static void doConnect() {
        try {
            log.info(ConfigReader.getProperty("db_name"));
            String url = "jdbc:sqlite:" + ConfigReader.getProperty("db_name");
            sqliteConnection = new JdbcConnectionSource(url);
            log.info("Connection to SQLite has been established.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

}
