package conserveandcook.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String PATH = ".properties";
    private static Config INSTANCE;
    private Properties properties;

    private Config() {
        properties = new Properties();
        String env = System.getProperty("env", "app");

        try {
            File file = new File(env + PATH);
            if (file.exists() && !file.isDirectory()) {
                properties.load(new FileInputStream(env + PATH));
            } else {
                properties.load(new FileInputStream("app" + PATH));
                log.info("No configuration for current environment found");
                log.info("Loading properties from app" + PATH);
            }
        } catch (Exception e) {
            log.error("Couldn't load properties from {}", env + PATH, e);
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
        if (enabled.equals("false")) {
            return false;
        }
        return true;
    }

    // For testing purposes
    public static void loadTestProperties() {
        Config config = getInstance();
        try {
            File file = new File("test" + PATH);
            if (file.exists() && !file.isDirectory()) {
                config.properties.load(new FileInputStream("test" + PATH));
            } else {
                config.properties.load(new FileInputStream("app" + PATH));
                log.info("No test configuration found");
                log.info("Loading properties from app" + PATH);
            }
        } catch (Exception e) {
            log.error("Couldn't load properties from {}", "test" + PATH, e);
        }
    }
}
