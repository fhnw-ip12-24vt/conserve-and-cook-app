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

        int[] scores = new int[3];
        int[][] scoresByCat = new int[3][3];
        int[][] minMax = getMinMaxScores();
        int[] minScoresByCat = minMax[0];
        int[] maxScoresByCat = minMax[1];

        for (int i = 0; i < 3; i++) {
            Ingredient ingredient = selectedIngredients[i];
            int ingScore = ingredient.getCo2();
            int max = maxScoresByCat[i];
            int min = minScoresByCat[i];

            int x = max - min;
            int y = ingScore - min;

            double division = (double) y / x;
            double dblScore = 1 - division;
            score += ((int) dblScore) * 100;
        }

        return score;
    }

    // TODO: (SK) Refactor this!
    private int[][] getMinMaxScores() {
        int[][] scoresByCat = new int[3][3];
        int[] minScoresByCat = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] maxScoresByCat = new int[3];

        for (int category = 0; category < 3; category++) {
            for (int j = 0; j < 3; j++) {
                int ingredientScore = this.ingredients[(3 * category) + j].getCo2();
                scoresByCat[category][j] = ingredientScore;
                if (ingredientScore > maxScoresByCat[category]) {
                    maxScoresByCat[category] = ingredientScore;
                }

                if (ingredientScore < minScoresByCat[category]) {
                    minScoresByCat[category] = ingredientScore;
                }
            }
        }

        return new int[][]{minScoresByCat, maxScoresByCat};

    }

}

