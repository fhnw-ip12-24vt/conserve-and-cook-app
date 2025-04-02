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

    /**
     * Calculates the score between 0 and 100.
     * 100 means that the selected ingredient is the ingredient with the least co2.
     * 0 means that the selected ingredient is the ingredient with the most co2.
     * If the selected ingredient's co2 is inbetween,
     * the score is calculated as a percentage of how close to the max value it is.
     *
     * @param selectedIngredients The selected ingredient from the model
     * @return All three ingredient scores added
     */
    public int calculateScore(Ingredient[] selectedIngredients) {
        int score = 0;

        for (int i = 0; i < 3; i++) {
            Ingredient ingredient = selectedIngredients[i];
            if (ingredient == null) {
                continue;
            }
            int ingScore = ingredient.getCo2();
            int min = 0, max = 0;
            try {
                max = getMaxScore();
                min = getMinScore();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return 0;
            }

            int x = max - min;
            int y = ingScore - min;

            double division = (double) y / x;
            double dblScore = 1 - division;
            score += (int) (dblScore * 100);
        }

        return (score / 3) * 125;
    }

    public int getMaxScore() throws SQLException {
        Database db = Database.getInstance();
        String query = "SELECT id, max(co2_score) FROM ingredient WHERE recipe_id = ?";
        ResultSet results = db.executeQuery(query, id);

        // Check if we've got a row
        if (results == null || !results.next()) {
            throw new SQLException("Exactly one value was expected, received zero");
        }

        return results.getInt(2);
    }

    public int getMinScore() throws SQLException {
        Database db = Database.getInstance();
        String query = "SELECT id, min(co2_score) FROM ingredient WHERE recipe_id = ?";
        ResultSet results = db.executeQuery(query, id);

        // Check if we've got a row
        if (results == null || !results.next()) {
            throw new SQLException("Exactly one value was expected, received zero");
        }

        return results.getInt(2);
    }
}
