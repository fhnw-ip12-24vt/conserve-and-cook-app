package conserveandcook;

import conserveandcook.misc.Props;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {

    @BeforeEach
    public final void setup() {
        Props.set("joystick_enabled", "false");
        Props.set("barcode_scanner_enabled", "false");
        Props.set("environment", "test");
        Props.set("database_path", "src/main/resources/database/database.db");
    }


    @AfterEach
    public void shutdown() {
    }
}
