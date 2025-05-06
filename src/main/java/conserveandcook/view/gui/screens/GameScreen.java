package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.controller.ApplicationController;
import conserveandcook.misc.Config;
import conserveandcook.misc.Environments;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;
import conserveandcook.model.Recipe;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.mvcbase.MvcLogger.LOGGER;

public class GameScreen extends AbstractScreen {
    /** Game duration in seconds */
    private int gameDuration; // 2 minutes in seconds
    private long lastUpdateTime;
    private boolean gameOver;
    private final ApplicationController controller;

    private static final Logger log = LoggerFactory.getLogger(GameScreen.class);

    private int ingredientIndex = 0;
    private int prevCategory = 0;

    private int debugIndex = 0;
    private int debugCategory = 0;
    private static final double ingredientScale = 0.25;

    boolean runningOnPi = Environments.get() == Environments.PRODUCTION;

    Button finishButton;
    public GameScreen(Gui g, ApplicationController controller) {
        super(g);
        finishButton = new Button(Button.Type.FINISH);
        finishButton.setState(Button.States.ACTIVE);
        finishButton.setY(440);
        finishButton.setX(600);
        components.add(finishButton);
        this.controller = controller;
    }

    public void draw(Application model) {
        drawBackground("img/game/frame_0.png");
        finishButton.setLanguage(model.getSelectedLanguage());
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
    public void init(Application model) {
        // Reset Timer
        String gameDurationString = Config.get("game.duration");
        if (gameDurationString != null) {
            this.gameDuration = Integer.parseInt(gameDurationString);
        } else {
            LOGGER.logError("Invalid property: game.duration must be greater than 0");
            this.gameDuration = 120;
        }

        // Select new random recipe, whenever the screen is initialized
        try {
            model.setSelectedRecipe(Recipe.getRandomRecipe());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        model.resetIngredients();
    }

    private void gameTimer() {
        long currentTime = System.nanoTime();
        long goneTime = currentTime - lastUpdateTime;

        if (gameDuration <= 0) {
            gameOver();
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
        gui.drawString(time, 140 * SCALE, 140 * SCALE);
    }

    private void gameOver() {
        this.gameOver = true;
        controller.nextScreen();
    }

    @Override
    public AvailableScreens next() {
        if (gameOver) {
            return AvailableScreens.GAMEOVER;
        }
        return AvailableScreens.RESULT;
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
        String name = ingredient.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        String path = "img/ingredient/" + name + ".png";
        return path;
    }

    private void drawRecipe(Recipe recipe) {
        int x = 120, y = 235;
        String path = "img/recipe/" + recipe.getName() + ".png";

        gui.drawImage(path, x * SCALE, y * SCALE, SCALE);
    }
}
