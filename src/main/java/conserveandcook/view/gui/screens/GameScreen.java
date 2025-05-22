package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Config;
import conserveandcook.misc.Environments;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;
import conserveandcook.model.Recipe;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Button;
import conserveandcook.view.gui.components.FinishButton;

import java.util.Arrays;

import static ch.mvcbase.MvcLogger.LOGGER;

public class GameScreen extends AbstractScreen {
    private int gameDuration;
    private long lastUpdateTime;
    private boolean gameOver;

    private int ingredientIndex = 0;
    private int prevCategory = 0;

    private int debugIndex = 0;
    private int debugCategory = 0;
    private static final double ingredientScale = 0.25;

    private Recipe recipe;
    private Ingredient[] ingredients;
    private int[] ingredientIds;
    private Ingredient[] selectedIngredients;
    private boolean buttonIsVisible = false;

    boolean runningOnPi = Environments.get() == Environments.PRODUCTION;

    FinishButton finishButton;

    public GameScreen(Gui g) {
        super(g);
        finishButton = new FinishButton(
                Button.States.IDLE,
                (int) (1200 * SCALE),
                (int) (880 * SCALE)
        );
    }

    public void draw(Application model) {
        drawBackground("img/game/frame_0.png");
        finishButton.setLanguage(model.getSelectedLanguage());
        Ingredient[] selectedIngredients = model.getSelectedIngredients();

        // Only calculate if necessary
        if(! buttonIsVisible) {
            int selectedCount = 0;
            for (Ingredient ing : selectedIngredients) {
                if (ing != null) {
                    selectedCount++;
                }
            }
            if (selectedCount == 3) {
                buttonIsVisible = true;
                finishButton.setState(Button.States.ACTIVE);
            }
        } else {
            finishButton.draw(gui);
        }

        Recipe selectedRecipe = model.getSelectedRecipe();
        if (selectedRecipe == null) return;

        drawRecipe(selectedRecipe);

        for (Ingredient ingredient : ingredients) {
            if (ingredient == null) continue;
            drawIngredient(ingredient);
        }

        for (Ingredient ingredient : selectedIngredients) {
            if (ingredient == null) continue;
            drawSelectedIngredient(ingredient);
        }
        gameTimer(model);
    }

    @Override
    public void scan(Application model, String barcode) {
        try {
            Ingredient scannedIngredient = Ingredient.getIngredientById(barcode, model.getSelectedRecipe().getId());
            if (isValidIngredient(scannedIngredient)) {
                model.addSelectedIngredient(scannedIngredient);
            }
        } catch (Exception e) {
            LOGGER.logInfo(e.getMessage());
        }
    }

    private boolean isValidIngredient(Ingredient scannedIngredient) {
        return Arrays.stream(ingredientIds)
                .anyMatch(n -> n == scannedIngredient.getId());
    }

    @Override
    public void down(Application model) {
        // TODO: (SK) Remove temporary code, repl ace with Scanner-Code
        if (!runningOnPi && model.getSelectedRecipe() != null) {
            model.addSelectedIngredient(model.getSelectedRecipe()
                    .getIngredients()[(3 * debugCategory) + debugIndex]);
            debugIndex = Application.incrementWrapped(debugIndex, 2);
        }
    }

    @Override
    public void left(Application model) {
        if (!runningOnPi) {
            debugCategory = Application.incrementWrapped(debugCategory, 2);
        }
    }

    @Override
    public void right(Application model) {
        if (!runningOnPi) {
            debugCategory = Application.decrementWrapped(debugCategory, 2);
        } else {
            model.incrementScreen();
        }
    }

    @Override
    public void init(Application model) {
        // Reset Timer
        String gameDurationString = Config.get("game.duration");
        if (gameDurationString != null) {
            this.gameDuration = Integer.parseInt(gameDurationString);
        } else {
            LOGGER.logError("Invalid property: game.duration must be greater than 0");
            this.gameDuration = 120;
        }

        buttonIsVisible = false;
    }

    /**
     * Called to preload the ingredients, to block the drawing queue less
     * @param model
     */
    public void preload(Application model) {
        // Select new random recipe, whenever the screen is initialized
        try {
            recipe = Recipe.getRandomRecipe(model.getSelectedRegion().getId());
            model.setSelectedRecipe(recipe);
            ingredients = recipe.getIngredients();
            ingredientIds = new int[9];
            for (int i = 0; i < ingredients.length; i++) {
                ingredientIds[i] = ingredients[i].getId();
            }
        } catch (Exception e) {
            LOGGER.logError(e.getMessage(), e);
        }

        model.resetIngredients();
    }

    private void gameTimer(Application model) {
        long currentTime = System.nanoTime();
        long goneTime = currentTime - lastUpdateTime;

        if (gameDuration <= 0) {
            gameOver(model);
            return;
        }

        long second = 1_000_000_000L; // one second in nanoseconds
        if (goneTime >= second) {
            gameDuration--;
            lastUpdateTime = currentTime;
        }

        int minutes = gameDuration / 60;
        int seconds = gameDuration % 60;
        String time = String.format("%02d:%02d", minutes, seconds);

        gui.setFontSize((int) (80 * SCALE));
        gui.drawString(time, 305 * SCALE, 995 * SCALE);
    }

    private void gameOver(Application model) {
        this.gameOver = true;
        model.incrementScreen();
    }

    @Override
    public AvailableScreens next() {
        if (gameOver) {
            return AvailableScreens.GAMEOVER;
        }
        return AvailableScreens.RESULT;
    }

    private void drawIngredient(Ingredient ingredient) {
        String path = getPathOfImage(ingredient);

        int category = ingredient.getCategory();
        int yOffset = category * 240;
        int xOffset = ingredientIndex * 230;

        gui.drawImage(path, (1090 + xOffset) * SCALE, (200 + yOffset) * SCALE, SCALE * ingredientScale);

        // Calculate which position horizontally (x-offset) the ingredient is drawn (0, 1 or 2)
        if (category == prevCategory) {
            ingredientIndex = ingredientIndex + 1 > 3 ? 0 : ingredientIndex + 1;
        } else {
            ingredientIndex = 0;
            prevCategory = category;
        }
    }

    private void drawSelectedIngredient(Ingredient ingredient) {
        String path = getPathOfImage(ingredient);

        int category = ingredient.getCategory();
        int yOffset = category * 240;

        gui.drawImage(path, (710) * SCALE, (200 + yOffset) * SCALE, SCALE * ingredientScale);
    }

    private static String getPathOfImage(Ingredient ingredient) {
        String name = ingredient.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return "img/ingredient/" + name + ".png";
    }

    private void drawRecipe(Recipe recipe) {
        int x = 120, y = 235;
        String path = "img/recipe/" + recipe.getName() + ".png";
        gui.drawImage(path, x * SCALE, y * SCALE, SCALE);
    }
}
