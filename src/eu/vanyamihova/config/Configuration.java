package eu.vanyamihova.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Reads main configuration file and provides methods for all properties,
 * which are needed in the application.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
public final class Configuration {

    private static final String CONFIG_FILE = "config.cfg";
    private Properties configFile;

    public Configuration() {
        configFile = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(CONFIG_FILE);
            configFile.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDatabaseDriver() {
        return getProperty(ConfigurationKey.DATABASE_DRIVER);
    }

    public String getDatabaseUrl() {
        return getProperty(ConfigurationKey.DATABASE_URL);
    }

    public String getDatabaseUser() {
        return getProperty(ConfigurationKey.DATABASE_USER);
    }

    public String getDatabasePassword() {
        return getProperty(ConfigurationKey.DATABASE_PASSWORD);
    }

    private String getProperty(ConfigurationKey key) {
        return configFile.getProperty(key.getValue());
    }
}
