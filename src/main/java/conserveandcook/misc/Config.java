package conserveandcook.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String PATH = "app.properties";
    private static Config INSTANCE;
    private Properties properties;

    private Config() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH));
        } catch (Exception e) {
            log.error("Couldn't load properties from {}", PATH, e);
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
}
