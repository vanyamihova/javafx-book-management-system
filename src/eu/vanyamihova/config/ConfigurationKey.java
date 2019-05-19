package eu.vanyamihova.config;

/**
 * Represents all keys used in the configuration file.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-18
 */
enum ConfigurationKey {
    DATABASE_DRIVER("database.driver"),
    DATABASE_URL("database.url"),
    DATABASE_USER("database.user"),
    DATABASE_PASSWORD("database.password");

    private String value;

    ConfigurationKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
