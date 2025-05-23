package conserveandcook.model;

import conserveandcook.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Recipe {
    private final int id;
    private final String name;
    private final Ingredient[] ingredients;

    private Recipe(int id, String name, Ingredient[] ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public static Recipe getRandomRecipe(int regionId) throws SQLException {
        Database db = Database.getInstance();
        // TODO: (SK) Replace this code once all the recipes are drawn
        String query = "select * from recipe where region_id = ? order by random() limit 1";
        ResultSet results = db.executeQuery(query, regionId);

        // Check if we've got a row
        if (results == null || !results.next()) {
            throw new SQLException("Exactly one recipe was expected, received zero");
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
        int min, max, score = 0;
        try {
            max = getMaxScore();
            min = getMinScore();
        } catch (SQLException e) {
            LOGGER.logException(e.getMessage(), e);
            return 0;
        }

        for (int i = 0; i < 3; i++) {
            Ingredient ingredient = selectedIngredients[i];
            if (ingredient == null) {
                continue;
            }
            int ingScore = ingredient.getCo2();

            int x = max - min;
            int y = ingScore - min;

            double division = (double) y / x;
            double dblScore = 1 - division;
            score += (int) (dblScore * 100);
        }

        // Multiplication with 125 is only cosmetic, the numbers look cooler
        return (score / 3) * 125;
    }

    public int getMaxScore() throws SQLException {
        Database db = Database.getInstance();
        String query = "SELECT i.id, max(i.co2_score) FROM ingredient i JOIN ingredient_to_recipe r on i.id = r.ingredient_id WHERE r.recipe_id = ?";
        ResultSet results = db.executeQuery(query, id);

        // Check if we've got a row
        if (results == null || !results.next()) {
            throw new SQLException("Exactly one value was expected, received zero");
        }

        return results.getInt(2);
    }

    public int getMinScore() throws SQLException {
        Database db = Database.getInstance();
        String query = "SELECT i.id, min(i.co2_score) FROM ingredient i JOIN ingredient_to_recipe r on i.id = r.ingredient_id WHERE r.recipe_id = ?";
        ResultSet results = db.executeQuery(query, id);

        // Check if we've got a row
        if (results == null || !results.next()) {
            throw new SQLException("Exactly one value was expected, received zero");
        }

        return results.getInt(2);
    }

    /**
     * @return The comment of the Ingredient with the largest c02 value
     */
    public String getComment(Ingredient[] ingredient) {
        Ingredient max = null;
        int maxCo2 = -1;

        for (Ingredient value : ingredient) {
            if (value == null) continue;
            if (value.getCo2() > maxCo2) {
                max = value;
                maxCo2 = value.getCo2();
            }
        }

        return max != null ? max.getComment() : null;
    }
}
