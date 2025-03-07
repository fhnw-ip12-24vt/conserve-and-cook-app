package conserveandcook.model;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest extends AbstractTest {

    @Test
    public void recipeTest() {
        // Arrange
        Recipe r1;
        try {
            // Act
            r1 = Recipe.getRandomRecipe();
            // Assert
            assertNotNull(r1);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void recipeIngredientsTest() {
        // Arrange
        Recipe r1;
        try {
            // Act
            r1 = Recipe.getRandomRecipe();
            // Assert
            Ingredient[] ingredients = r1.getIngredients();

            for (Ingredient i : ingredients) {
                assertNotNull(i);
                assertNotNull(i.getName());
            }
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void maxScoreTest() throws SQLException {
        // Arrange
        Recipe r1;
        int score;

        // Act
        r1 = Recipe.getRandomRecipe();
        score = r1.getMaxScore();

        // Assert
        assertEquals(40, score);
    }

    @Test
    public void minScoreTest() throws SQLException {
        // Arrange
        Recipe r1;
        int score;

        // Act
        r1 = Recipe.getRandomRecipe();
        score = r1.getMinScore();

        // Assert
        assertEquals(3, score);
    }

    @Test
    public void scoreTest() throws SQLException {
        // Arrange
        Recipe r1;
        Application m = new Application();

        // Act
        r1 = Recipe.getRandomRecipe();
        Ingredient i1 = Ingredient.getIngredientById("007");
        Ingredient i2 = Ingredient.getIngredientById("001");
        Ingredient i3 = Ingredient.getIngredientById("006");
        m.addSelectedIngredient(i1);
        m.addSelectedIngredient(i2);
        m.addSelectedIngredient(i3);

        // Assert
        int score = r1.calculateScore(m.getSelectedIngredients());
        assertEquals((100/3)*1000, score);
    }

    @Test
    public void scoreTestMin() throws SQLException {
        // Arrange
        Recipe r1;
        Application m = new Application();

        // Act
        r1 = Recipe.getRandomRecipe();
        Ingredient i1 = Ingredient.getIngredientById("004");
        Ingredient i2 = Ingredient.getIngredientById("009");
        Ingredient i3 = Ingredient.getIngredientById("006");
        m.addSelectedIngredient(i1);
        m.addSelectedIngredient(i2);
        m.addSelectedIngredient(i3);

        // Assert
        int score = r1.calculateScore(m.getSelectedIngredients());
        assertEquals((100 / 3)*1000, score);
    }

    @Test
    public void scoreTestMax() throws SQLException {
        // Arrange
        Recipe r1;
        Application m = new Application();

        // Act
        r1 = Recipe.getRandomRecipe();
        Ingredient i1 = Ingredient.getIngredientById("005");
        Ingredient i2 = Ingredient.getIngredientById("003");
        Ingredient i3 = Ingredient.getIngredientById("002");
        m.addSelectedIngredient(i1);
        m.addSelectedIngredient(i2);
        m.addSelectedIngredient(i3);

        // Assert
        int score = r1.calculateScore(m.getSelectedIngredients());
        assertEquals(0, score);
    }
}
