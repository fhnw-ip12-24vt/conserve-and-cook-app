package conserveandcook.checks;

import conserveandcook.Database;

public class DatabaseCheck extends Check{

    @Override
    public boolean run() {
        Database db = Database.getInstance();
        return db.connection != null;
    }
}
