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
    public void refreshDatabase() {
        try {
            String databasePath = "src/main/resources/database/test.sql";
            FileReader fileReader = new FileReader(databasePath);
            BufferedReader reader = new BufferedReader(fileReader);

            StringBuilder sql = new StringBuilder();
            Statement statement = Database.getInstance().connection.createStatement();
            statement.execute("PRAGMA busy_timeout = 5000;");
            String line;

            while ((line = reader.readLine()) != null) {
                // Skip comments and empty lines
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--") || line.startsWith("#")) {
                    continue;
                }

                sql.append(line);

                // Check if the line ends with a semicolon (end of statement)
                if (line.endsWith(";")) {
                    statement.addBatch(sql.toString());
                    sql.setLength(0);
                }
            }
            statement.executeBatch();
            statement.closeOnCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
