package conserveandcook.model;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String query = "select * from ingredient where id = ? limit 1";

        // Check for three digits
        Pattern pattern = Pattern.compile("\\d{3}");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            id = matcher.group();
        } else {
            throw new SQLException("Invalid barcode: No id found");
        }
        ResultSet results = db.executeQuery(query, id);

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

    public static Ingredient[] getIngredientsByRecipe(int recipeId) throws SQLException {
        Database db = Database.getInstance();
        int ingredientCount = 9;
        String query = "select * from ingredient where recipe_id = ? order by category_id limit 9;";
        ResultSet results = db.executeQuery(query, recipeId);
        Ingredient[] ingredients = new Ingredient[ingredientCount];

        int index = 0;
        if (results == null) {
            throw new SQLException("Exactly three ingredients were expected, received zero");
        }
        while (results.next() && index < ingredientCount) {
            ingredients[index] = new Ingredient
                    (
                            results.getInt(1),
                            results.getString(2),
                            results.getInt(3),
                            results.getInt(4)
                    );
            index++;
        }
        return ingredients;
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

    public int getId() {
        return id;
    }
}
