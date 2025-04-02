package conserveandcook.misc;

import conserveandcook.Database;
import conserveandcook.model.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class I18n {
    private static I18n INSTANCE;

    public static I18n getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new I18n();
        }
        return INSTANCE;
    }

    private String translate(String key) throws SQLException {
        Database db = Database.getInstance();
        String query = "SELECT text from translation WHERE language_id = ? and key = ?";
        ResultSet resultSet = db.executeQuery(query, Application.getLanguage(), key);

        if (resultSet == null)
            throw new SQLException("Exactly one value was expected, received zero");

        return resultSet.getString(1);
    }
}
