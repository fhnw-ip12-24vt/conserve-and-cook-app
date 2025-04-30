package conserveandcook.checks;

import conserveandcook.Database;

public class DatabaseCheck extends Check{

    @Override
    public boolean run() {
        // TODO: (NN) Hier muss die Datenbank getestet werden. Sind alle Tabellen vorhanden? etc.
        Database db = Database.getInstance();
        return db.connection != null;
    }
}
