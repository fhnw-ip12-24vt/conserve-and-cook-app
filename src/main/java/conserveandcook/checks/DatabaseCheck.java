package conserveandcook.checks;

import conserveandcook.Database;

import java.sql.ResultSet;

public class DatabaseCheck extends Check {
    Database db;

    @Override
    public boolean run() {
        // TODO: (NN) Hier muss die Datenbank getestet werden. Sind alle Tabellen vorhanden? etc.
        db = Database.getInstance();
        if (db == null || db.connection == null) throw new CheckException("Database connection failed");

        String[] tables = {
                "highscore",
                "ingredient",
                "ingredient_to_recipe",
                "languages",
                "recipe",
                "regions",
                "translations",
                "abrakadabra"
        };
        for (String table : tables) {
            boolean exists = checkTable(table);
            if (!exists) throw new CheckException("Table not found: " + table);
        }
        return true;
    }

    private boolean checkTable(String table) {
        String query = "SELECT * FROM ?";
        ResultSet result = null;
        try {
            result = db.executeQuery(query, table);
            System.out.println(result);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
