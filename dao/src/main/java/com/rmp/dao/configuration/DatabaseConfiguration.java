package com.rmp.dao.configuration;

import com.google.inject.Singleton;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.rmp.dao.utils.ConfigReader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * Provides the database configuration
 */
@Slf4j
@Singleton
public class DatabaseConfiguration {

    @Getter
    private ConnectionSource sqliteConnection;

    /**
     * Initialize new instance of {@link DatabaseConfiguration}
     */
    public DatabaseConfiguration() {
        this.doConnect();
    }

    private void doConnect() {
        try {
            log.info(ConfigReader.getProperty("db_name"));
            String url = "jdbc:sqlite:" + ConfigReader.getProperty("db_name");
            this.sqliteConnection = new JdbcConnectionSource(url);
            log.info("Connection to SQLite has been established.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

}
