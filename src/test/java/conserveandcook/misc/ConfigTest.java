package conserveandcook.misc;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConfigTest extends AbstractTest {
    @Test
    public void testGetEnv() {
        String env = Config.get("environment");
        assertEquals("test", env);
    }

    @Test
    public void testSetEnv() {
        Config.set("environment", "abc1234");
        String env = Config.get("environment");
        assertEquals("abc1234", env);
    }

    @Test
    public void testGetNonexistent() {
        String nonexistent = Config.get("nonexistent");
        assertNull(nonexistent);
    }
}
