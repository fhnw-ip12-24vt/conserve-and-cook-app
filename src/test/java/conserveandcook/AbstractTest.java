package conserveandcook;

import conserveandcook.misc.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Statement;

public abstract class AbstractTest {

    @BeforeEach
    public final void setup() {
        Config.loadTestProperties();
    }

    @AfterEach
    public void shutdown() {
    }

    // TODO: (SK) Fix SQLITE_LOCKED error
}
