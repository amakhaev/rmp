package com.rmp.dao.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Provides reader to get config parameters
 */
@UtilityClass
@Slf4j
public class ConfigReader {

    private String configFileName = "config.properties";
    private Properties prop = new Properties();

    static {
        try (InputStream input = new FileInputStream(configFileName)) {
            prop.load(input);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * Gets the property from file.
     *
     * @param key - the key to search.
     * @return the string result.
     */
    public String getProperty(String key) {
        try {
            return prop.getProperty(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
