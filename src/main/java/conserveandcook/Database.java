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

    public void executeUpdate(String sql, Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setParameters(preparedStatement, params);
            preparedStatement.executeUpdate();
            LOGGER.logInfo("SQL update executed: " + sql);
        } catch (SQLException e) {
            LOGGER.logException("Error executing update: " + e.getMessage(), e);
        }
    }

    public ResultSet executeQuery(String sql, Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setParameters(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData().getColumnCount() > 1) {
                return resultSet;
            }
        } catch (SQLException e) {
            LOGGER.logException("Error executing query: " + e.getMessage(), e);
        }
        return null;
    }

    private void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
