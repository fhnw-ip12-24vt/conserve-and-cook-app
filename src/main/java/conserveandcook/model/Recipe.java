package conserveandcook.model;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Recipe {
    private final int id;
    private final String name;
    private final Ingredient[] ingredients;

    private Recipe(int id, String name, Ingredient[] ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public static Recipe getRandomRecipe() throws SQLException {
        Database db = Database.getInstance();
        String query = "select * from recipe order by random() limit 1";
        ResultSet results = db.executeQuery(query);

        // Check if we've got a row
        if (results == null || !results.next()) {
            throw new SQLException("Exactly one ingredient was expected, received zero");
        }

        int id = results.getInt(1);
        Ingredient[] ingredients = Ingredient.getIngredientsByRecipe(id);

        return new Recipe(
                id,
                results.getString(3),
                ingredients
        );
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

