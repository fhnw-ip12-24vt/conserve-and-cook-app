package conserveandcook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Database {

    private static Database INSTANCE;
    private static final String databasePath = "src/main/resources/database/database.db";

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            LOGGER.logInfo("Connection to SQLite database established.");
        } catch (SQLException e) {
            LOGGER.logException("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public void executeUpdate(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            LOGGER.logInfo("SQL update executed: " + sql);
        } catch (SQLException e) {
            LOGGER.logException("Error executing update: " + e.getMessage());
        }
    }

    public void executeQuery(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                LOGGER.logInfo("ID: " + resultSet.getInt("id"));
                if (resultSet.getMetaData().getColumnCount() > 1) {
                    LOGGER.logInfo("Value: " + resultSet.getString(2));
                }
            }

        } catch (SQLException e) {
            LOGGER.logException("Error executing query: " + e.getMessage());
        }
    }

    public void initializeDatabase() {
        executeUpdate("DROP TABLE IF EXISTS translations");
        executeUpdate("DROP TABLE IF EXISTS recipe");
        executeUpdate("DROP TABLE IF EXISTS regions");
        executeUpdate("DROP TABLE IF EXISTS languages");
        executeUpdate("CREATE TABLE languages (id INT PRIMARY KEY, name VARCHAR(20))");
        executeUpdate("CREATE TABLE translations (id INT PRIMARY KEY, text VARCHAR(255), language_id INT, FOREIGN KEY(language_id) REFERENCES languages(id))");
        executeUpdate("CREATE TABLE regions (id INT PRIMARY KEY, title VARCHAR(20))");
        executeUpdate("CREATE TABLE recipe (id INT PRIMARY KEY, region_id INT, FOREIGN KEY(region_id) REFERENCES regions(id))");
        executeUpdate("INSERT INTO languages VALUES (1, 'English')");
        executeUpdate("INSERT INTO languages VALUES (2, 'German')");
        executeUpdate("INSERT INTO regions VALUES (1, 'Europe')");
        executeUpdate("INSERT INTO regions VALUES (2, 'Asia')");
        executeUpdate("INSERT INTO translations VALUES (1, 'Hello', 1)");
        executeUpdate("INSERT INTO translations VALUES (2, 'Hallo', 2)");
        executeUpdate("INSERT INTO recipe VALUES (1, 1)");
    }
}
