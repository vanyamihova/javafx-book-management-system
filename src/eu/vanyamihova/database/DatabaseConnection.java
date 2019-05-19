package eu.vanyamihova.database;

import eu.vanyamihova.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create connection to the database. It uses configuration to load
 * correct data.
 *
 * @author Vanya Mihova <vanya.mihova89@gmail.com>
 * @since 2019-05-17
 */
public final class DatabaseConnection {

    private static Connection connection = null;
    private static Configuration configuration = new Configuration();

    public static Connection getConnection() {
        try {
            Class.forName(configuration.getDatabaseDriver());
            connection = DriverManager.getConnection(configuration.getDatabaseUrl(), configuration.getDatabaseUser(), configuration.getDatabasePassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
