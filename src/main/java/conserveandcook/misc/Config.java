package conserveandcook.misc;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Config {
    private static final String PATH = ".properties";
    private static Config INSTANCE;
    private final Properties properties;

    private Config() {
        properties = new Properties();
        String env = System.getProperty("env", "app");

        try {
            File file = new File(env + PATH);
            if (file.exists() && !file.isDirectory()) {
                properties.load(new FileInputStream(env + PATH));
            } else {
                properties.load(new FileInputStream("app" + PATH));
                LOGGER.logInfo("No configuration for current environment found");
                LOGGER.logInfo("Loading properties from app" + PATH);
            }
        } catch (Exception e) {
            LOGGER.logException("Couldn't load properties from " + env + PATH, e);
        }
    }

    private static Config getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    public static String get(String key) {
        return getInstance().properties.getProperty(key);
    }

    public static void set(String key, String value) {
        getInstance().properties.setProperty(key, value);
    }

    public static boolean isEnabled(String key) {
        String enabled = get(key + ".enabled");
        return ! Objects.equals(enabled, "false");
    }

    /**
     * For testing purposes, ran before each test case
     */
    public static void loadTestProperties() {
        Config config = getInstance();
        try {
            File file = new File("test" + PATH);
            if (file.exists() && !file.isDirectory()) {
                config.properties.load(new FileInputStream("test" + PATH));
            } else {
                config.properties.load(new FileInputStream("app" + PATH));
                LOGGER.logInfo("No test configuration found");
                LOGGER.logInfo("Loading properties from app" + PATH);
            }
        } catch (Exception e) {
            LOGGER.logException("Couldn't load properties from {}" + "test" + PATH, e);
        }
    }
}
