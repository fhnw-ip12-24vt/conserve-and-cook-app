package conserveandcook.model;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest extends AbstractTest {
    String testId1 = "644824915043";
    String testId2 = "4250236825175";

    @Test
    public void ingredientTest() {
        // Arrange
        Ingredient i1;
        try {
            // Act
            i1 = Ingredient.getIngredientById(testId1);
            // Assert
            assertEquals("Rindfleisch", i1.getName());
            assertEquals(10, i1.getCo2());
            assertEquals(0, i1.getCategory());
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
            i2 = Ingredient.getIngredientById(testId2);
            // Assert
            assertEquals("Poulet", i2.getName());
            assertEquals(20, i2.getCo2());
            assertEquals(1, i2.getCategory());
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
            i3 = Ingredient.getIngredientById(testId3);
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
            i1 = Ingredient.getIngredientById(testId1);
            i2 = Ingredient.getIngredientById(testId2);
            model.addSelectedIngredient(i1);
            Ingredient[] selected = model.getSelectedIngredients();
            assertEquals("Rindfleisch", i1.getName());
            assertNull(selected[1]);
            assertNull(selected[2]);
            model.addSelectedIngredient(i2);
            assertEquals("Rindfleisch", i1.getName());
            assertEquals("Poulet", i2.getName());
            assertNull(selected[2]);
        } catch (Exception e) {
            fail(e);
        }
    }
}
