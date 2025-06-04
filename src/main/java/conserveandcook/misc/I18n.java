package conserveandcook.misc;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static ch.mvcbase.MvcLogger.LOGGER;

public class I18n {
    private static I18n INSTANCE;
    private Languages language;

    /** Mapping of Languages and Keys to their Translation to minimize DB hits. */
    private HashMap<Integer, HashMap<String, String>> cache;

    /** Cache can be disabled via the properties */
    private boolean isCacheEnabled;

    public static I18n getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new I18n();

            INSTANCE.isCacheEnabled = Config.isEnabled("i18n.cache");
            if(INSTANCE.isCacheEnabled) {
                INSTANCE.cache = new HashMap<>();
            }
        }
        return INSTANCE;
    }

    /**
     * Translates a given key into the target language. Language can be specified over a setter.
     * @return The translated String
     */
    public static String translate(String key) {
        I18n i18n = getInstance();

        if(i18n.isCacheEnabled) {
            String cacheResult = getFromCache(key);
            if (cacheResult != null) {
                return cacheResult;
            }
        }

        Database db = Database.getInstance();
        String query = "SELECT * from translations WHERE language_id = ? and key = ?";
        Languages lang = i18n.language;

        if (lang == null) {
            throw new NullPointerException("Language is null");
        }

        try (ResultSet resultSet = db.executeQuery(query, lang.index, key)) {

            if (resultSet == null || !resultSet.next()) {
                LOGGER.logError("Translation not found for key " + key);
                return null;
            }
            String result = resultSet.getString(2);

            if (i18n.isCacheEnabled) {
                putToCache(key, result);
            }

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
     * @return The translated key, or null if it was not present in memory.
     */
    private static String getFromCache(String key) {
        I18n i18n = getInstance();
        if (! cacheContains(key)) {
            return null;
        }
        return i18n.cache.get(i18n.language.index).get(key);
    }

    private static boolean cacheContains(String key) {
        I18n i18n = getInstance();
        boolean cacheContainsKey = i18n.cache.containsKey(i18n.language.index);
        if (! cacheContainsKey) return false;

        return i18n.cache.get(i18n.language.index).containsKey(key);
    }

    private static void putToCache(String key, String value) {
        I18n i18n = getInstance();
        if (! i18n.cache.containsKey(i18n.language.index)) {
            i18n.cache.put(i18n.language.index, new HashMap<>());
        }
        i18n.cache.get(i18n.language.index).put(key, value);
    }

    public static void clearCache() {
        I18n i18n = getInstance();
        i18n.cache.clear();
    }
}
