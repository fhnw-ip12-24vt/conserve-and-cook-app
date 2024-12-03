package conserveandcook.model;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ingredient {
    private final int id;
    private final String name;
    private final int co2;
    private final int category;

    private Ingredient(int id, String name, int co2, int category) {
        this.id = id;
        this.name = name;
        this.co2 = co2;
        this.category = category;
    }

    public static Ingredient getIngredientById(String id) throws SQLException {
        Database db = Database.getInstance();
        String query = "select * from ingredient where id = " + id + " limit 1";
        ResultSet results = db.executeQuery(query);

        // Check if we've got a row
        if (!results.next()) {
            throw new SQLException("Exactly one ingredient was expected, received zero");
        }
        return new Ingredient(
                results.getInt(1),
                results.getString(2),
                results.getInt(3),
                results.getInt(4)
        );
    }

    public String getName() {
        return name;
    }

    public int getCo2() {
        return co2;
    }

    public int getCategory() {
        return category;
    }
}
