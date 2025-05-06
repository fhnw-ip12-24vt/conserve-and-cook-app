package conserveandcook.model;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest extends AbstractTest {
    String testId1 = "078";
    String testId2 = "016";
    private static int RECIPE_ID = 1;

    @Test
    public void ingredientTest() {
        // Arrange
        Ingredient i1;
        try {
            // Act
            i1 = Ingredient.getIngredientById(testId1, RECIPE_ID);
            // Assert
            assertEquals("ananas", i1.getName());
            assertEquals(10, i1.getCo2());
            assertEquals(1, i1.getCategory());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void ingredientTest2() {
        // Arrange
        Ingredient i2;
        try {
            // Act
            i2 = Ingredient.getIngredientById(testId2, RECIPE_ID);
            // Assert
            assertEquals("avocado", i2.getName());
            assertEquals(20, i2.getCo2());
            assertEquals(2, i2.getCategory());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void nonExistingIngredientTest() {
        // Arrange
        Ingredient i3;
        String testId3 = "123456765432";
        try {
            // Act
            i3 = Ingredient.getIngredientById(testId3, RECIPE_ID);
        } catch (Exception e) {
            // Assert
            assertTrue(true);
        }
    }

    @Test
    public void invalidBarcodeTest() {
        // arrange
        Ingredient i4;
        String testId4 = "abc";

        try {
            // Act
            i4 = Ingredient.getIngredientById(testId4, RECIPE_ID);
        } catch (Exception e) {
            // Assert
            assertTrue(true);
        }
    }

    @Test
    public void modelTest() {
        Application model = new Application();
        Ingredient i1, i2;
        try {
            // Act
            i1 = Ingredient.getIngredientById(testId1, RECIPE_ID);
            i2 = Ingredient.getIngredientById(testId2, RECIPE_ID);
            model.addSelectedIngredient(i1);
            Ingredient[] selected = model.getSelectedIngredients();
            assertEquals("ananas", selected[1].getName());
            assertNull(selected[0]);
            assertNull(selected[2]);
            model.addSelectedIngredient(i2);
            assertEquals("ananas", i1.getName());
            assertEquals("avocado", i2.getName());
            assertNull(selected[0]);
        } catch (Exception e) {
            fail(e);
        }
    }
}
