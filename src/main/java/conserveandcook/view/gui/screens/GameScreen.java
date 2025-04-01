package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Environments;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;
import conserveandcook.model.Recipe;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameScreen extends AbstractScreen {
    private static final Logger log = LoggerFactory.getLogger(GameScreen.class);

    private int ingredientIndex = 0;
    private int prevCategory = 0;

    private int debugIndex = 0;
    private int debugCategory = 0;
    private static final double ingredientScale = 0.25;

    boolean runningOnPi = Environments.get() == Environments.PRODUCTION;

    public GameScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        drawBackground("img/game/frame_0.png");
        if (model.getSelectedRecipe() == null) {
            try {
                model.setSelectedRecipe(Recipe.getRandomRecipe());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        Ingredient[] selectedIngredients = model.getSelectedIngredients();
        Recipe selectedRecipe = model.getSelectedRecipe();
        if (selectedRecipe != null) {
            drawRecipe(selectedRecipe);
            Ingredient[] ingredients = model.getSelectedRecipe().getIngredients();

            for (Ingredient ingredient : ingredients) {
                drawIngredient(ingredient, selectedRecipe);
            }

            for (Ingredient ingredient : selectedIngredients) {
                if (ingredient != null) {
                    drawSelectedIngredient(ingredient, selectedRecipe);
                }
            }
        }
    }

    @Override
    public void scan(Application model, String barcode) {
        try {
            Ingredient scannedIngredient = Ingredient.getIngredientById(barcode);
            model.addSelectedIngredient(scannedIngredient);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        // TODO: (SK) Implement game logic
    }

    @Override
    public void down(Application model) {
        // TODO: (SK) Remove temporary code, repl ace with Scanner-Code
        if (!runningOnPi && model.getSelectedRecipe() != null) {
            model.addSelectedIngredient(model.getSelectedRecipe()
                    .getIngredients()[(3 * debugCategory) + debugIndex]);
            debugIndex = model.incrementWrapped(debugIndex, 2);
        }
    }

    @Override
    public void left(Application model) {
        if (!runningOnPi) {
            debugCategory = model.incrementWrapped(debugCategory, 2);
        }
    }

    @Override
    public void right(Application model) {
        if (!runningOnPi) {
            debugCategory = model.decrementWrapped(debugCategory, 2);
        }
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.NAME;
    }

    private void drawIngredient(Ingredient ingredient, Recipe selectedRecipe) {
        String path = getPathOfImage(ingredient, selectedRecipe);

        int category = ingredient.getCategory();
        int yOffset = category * 240;
        int xOffset = ingredientIndex * 230;

        gui.drawImage(path, (1090 + xOffset) * SCALE, (200 + yOffset) * SCALE, SCALE * ingredientScale);

        if (category == prevCategory) {
            ingredientIndex = ingredientIndex + 1 > 3 ? 0 : ingredientIndex + 1;
        } else {
            ingredientIndex = 0;
            prevCategory = category;
        }
    }

    private void drawSelectedIngredient(Ingredient ingredient, Recipe selectedRecipe) {
        String path = getPathOfImage(ingredient, selectedRecipe);

        int category = ingredient.getCategory();
        int yOffset = category * 240;

        gui.drawImage(path, (710) * SCALE, (200 + yOffset) * SCALE, SCALE * ingredientScale);
    }

    private static String getPathOfImage(Ingredient ingredient, Recipe selectedRecipe) {
        String path = "img/ingredient/" + ingredient.getName() + ".png";
        return path;
    }

    private void drawRecipe(Recipe recipe) {
        int x = 120, y = 235;
        String path = "img/recipe/" + recipe.getName() + ".png";

        gui.drawImage(path, x * SCALE, y * SCALE, SCALE);
    }
}
