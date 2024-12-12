package conserveandcook;

import conserveandcook.misc.Config;

import java.sql.*;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Database {
    private static final String databasePath = Config.get("database.path");
    private static Database INSTANCE;
    public Connection connection;

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
}
