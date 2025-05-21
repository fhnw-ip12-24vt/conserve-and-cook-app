package conserveandcook.misc;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static ch.mvcbase.MvcLogger.LOGGER;

public class I18n {
    private static I18n INSTANCE;
    private Languages language;
    private HashMap<String, HashMap<Integer, String>> cache;

    private I18n() {}

    public static I18n getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new I18n();
            INSTANCE.cache = new HashMap<>();
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
        String cacheResult = getFromCache(key);
        if (cacheResult != null) {
            return cacheResult;
        }

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
            String result = resultSet.getString(2);
            putToCache(key, result);

            return result;
        } catch (SQLException e) {
            LOGGER.logInfo("Translation for key " + key + " failed");
            return null;
        }
    }

    public static void setLanguage(Languages language) {
        I18n i18n = getInstance();
        i18n.language = language;
    }

    /**
     * Try to retrieve the translation String from memory.
     * @param key
     * @return The translated key, or null if it was not present in memory.
     */
    private static String getFromCache(String key) {
        I18n i18n = getInstance();
        if (! cacheContains(key)) return null;
        return i18n.cache.get(key).get(i18n.language.index);
    }

    private static boolean cacheContains(String key) {
        I18n i18n = getInstance();
        boolean cacheContainsKey = i18n.cache.containsKey(key);
        if (! cacheContainsKey) return false;

        return i18n.cache.get(key).containsKey(i18n.language.index);
    }

    private static void putToCache(String key, String value) {
        I18n i18n = getInstance();
        if (! i18n.cache.containsKey(key)) {
            i18n.cache.put(key, new HashMap<>());
        }
        i18n.cache.get(key).put(i18n.language.index, value);
    }
}
