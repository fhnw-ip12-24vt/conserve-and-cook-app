package conserveandcook.misc;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ch.mvcbase.MvcLogger.LOGGER;

public class I18n {
    private static I18n INSTANCE;
    private Languages language;

    private I18n() {}

    public static I18n getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new I18n();
        }
        return INSTANCE;
    }

    /**
     * Translates a given key into the target language. Language can be specified over a setter.
     * @param key
     * @return The translated String
     */
    public static String translate(String key) {
        I18n i18n = getInstance();
        Database db = Database.getInstance();
        String query = "SELECT * from translations WHERE language_id = ? and key = ?";
        Languages lang = i18n.language;

        if (lang == null) {
            throw new NullPointerException("Language is null");
        }

        try (ResultSet resultSet = db.executeQuery(query, lang.index, key)) {

            if (resultSet == null) {
                return null;
            }

            return resultSet.getString(2);
        } catch (SQLException e) {
            LOGGER.logInfo("Translation for key " + key + " failed");
            return null;
        }
    }

    public static void setLanguage(Languages language) {
        I18n i18n = getInstance();
        i18n.language = language;
    }
}
