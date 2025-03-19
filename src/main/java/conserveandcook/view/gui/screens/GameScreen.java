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

import java.nio.file.Path;

public class GameScreen extends AbstractScreen {
    private int gameDuration = 120; // 2 minutes in seconds
    private long lastUpdateTime;
    private static final Logger log = LoggerFactory.getLogger(GameScreen.class);

    private int ingredientIndex = 0;
    private int prevCategory = 0;

    private int debugIndex = 0;
    private int debugCategory = 0;

    boolean runningOnPi = Environments.get() == Environments.PRODUCTION;

    public GameScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        drawBackground("img/game/frame_0.png");
        if (model.getSelectedRecipe() == null){
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
        gameTimer();
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

    private void gameTimer() {
        long currentTime = System.nanoTime();
        long goneTime = currentTime - lastUpdateTime;

        if (gameDuration <= 0) {
            gameOver();
            return;
        }

        long second = 1000000000L; // one second in nanoseconds
        if (goneTime >= second) {
            gameDuration--;
            lastUpdateTime = currentTime;
        }


        drawTime();
    }

    private void drawTime() {
        int minutes = gameDuration / 60;
        int seconds = gameDuration % 60;
        String time = String.format("%02d:%02d", minutes, seconds);

        gui.drawString(time, 50, 50);
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

        gui.drawImage(path, (1090 + xOffset) * SCALE, (200 + yOffset) * SCALE, SCALE);

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

        gui.drawImage(path, (710) * SCALE, (200 + yOffset) * SCALE, SCALE);
    }

    private static String getPathOfImage(Ingredient ingredient, Recipe selectedRecipe) {
        String name = ingredient.getName() + ".png";
        String recipeId = String.valueOf(selectedRecipe.getId());

        String path = Path.of("img/recipe", recipeId, name).toString();
        path = path.replace('\\', '/');
        return path;
    }

    private void drawRecipe(Recipe recipe) {
        int x = 120, y = 235;
        String id = String.valueOf(recipe.getId());
        String path = Path.of("img/recipe", id, "recipe.png").toString();
        path = path.replace('\\', '/');

        gui.drawImage(path, x * SCALE, y * SCALE, SCALE);
    }
}

    private void gameOver() {
        gui.drawString("Game Over", 50, 50);
    }
}