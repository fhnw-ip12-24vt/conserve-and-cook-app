package conserveandcook;

import conserveandcook.misc.Config;

import java.sql.*;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Database {
    private static final String databasePath = Config.get("database_path");
    private static Database INSTANCE;
    private Connection connection;

    private Database() {
        this.connection = connect();
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            LOGGER.logInfo("Connection to SQLite database established.");
        } catch (SQLException e) {
            LOGGER.logException("Failed to connect to the database: " + e.getMessage(), e);
        }
        return connection;
    }

    public void executeUpdate(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            LOGGER.logInfo("SQL update executed: " + sql);
        } catch (SQLException e) {
            LOGGER.logException("Error executing update: " + e.getMessage(), e);
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.getMetaData().getColumnCount() > 1) {
                return resultSet;
            }
        } catch (SQLException e) {
            LOGGER.logException("Error executing query: " + e.getMessage(), e);
        }
        return null;
    }

    private String drop(String table) {
        return "DROP TABLE IF EXISTS " + table;
    }

    public void initializeDatabase() {
        executeUpdate(drop("translations"));
        executeUpdate(drop("recipe"));
        executeUpdate(drop("regions"));
        executeUpdate(drop("languages"));
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
