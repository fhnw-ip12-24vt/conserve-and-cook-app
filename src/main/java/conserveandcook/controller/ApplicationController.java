package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;
import conserveandcook.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationController extends ControllerBase<Application> {
    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    /**
     * The Controller needs a Model.
     *
     * @param model Model managed by this Controller
     */

    public ApplicationController(Application model) {
        super(model);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    public void nextScreen() {
        model.incrementScreen();
    }

    public void prevScreen() {
        model.decrementScreen();
    }

    public void up() {
        switch (model.getCurrentScreen()) {
            case "language":
                model.incrementLanguage();
            case "region":
                model.nextRegion();
                break;
            case "game":
                try {
                    model.setSelectedRecipe(Recipe.getRandomRecipe());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                break;
        }
    }

    // TODO: (SK) Remove temporary code, replace with Scanner-Code
    private int tmp = 0;
    public void down() {
        switch (model.getCurrentScreen()) {
            case "language":
                model.decrementLanguage();
            case "region":
                model.previousRegion();
                break;
            case "game":
                if (model.getSelectedRecipe() != null) {
                    model.addSelectedIngredient(model.getSelectedRecipe().getIngredients()[tmp]);
                    tmp++;
                }
                break;
        }
    }

    public void scan(String barcode) {
        if (model.getCurrentScreen().equals("game")) {
            try {
                Ingredient scannedIngredient = Ingredient.getIngredientById(barcode);
                model.addSelectedIngredient(scannedIngredient);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            // TODO: (SK) Implement game logic
        }
    }
}
