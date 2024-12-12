package conserveandcook.model;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
