package com.rmp.widget.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Provides the utility class for localization
 */
@Slf4j
@UtilityClass
public class LocalizationUtils {

    private ResourceBundle resourceBundle;

    /**
     * Gets the locale value by given key.
     *
     * @param key - the key to search.
     * @return the locale value.
     */
    public String getString(String key) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("locale", new Locale("ru", "RU"), new UTF8Control());
        }

        try {
            return resourceBundle.getString(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return key;
        }
    }

    private class UTF8Control extends ResourceBundle.Control {

        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IOException
        {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }
}

