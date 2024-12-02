package conserveandcook;

import conserveandcook.misc.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {

    @BeforeEach
    public final void setup() {
        Config.set("joystick_enabled", "false");
        Config.set("barcode_scanner_enabled", "false");
        Config.set("environment", "test");
        Config.set("database_path", "src/main/resources/database/database.db");
    }


    @AfterEach
    public void shutdown() {
    }
}
