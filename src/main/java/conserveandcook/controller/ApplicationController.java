package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;
import conserveandcook.model.Recipe;
import conserveandcook.view.gui.AvailableScreens;
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
            case LANGUAGE:
                model.incrementLanguage();
            case REGION:
                model.nextRegion();
                break;
            case GAME:
                try {
                    model.setSelectedRecipe(Recipe.getRandomRecipe());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                break;
            case NAME:
                model.decrementLetter();
                break;
        }
    }

    // TODO: (SK) Remove temporary code, replace with Scanner-Code
    private int tmp = 0;
    public void down() {
        switch (model.getCurrentScreen()) {
            case LANGUAGE:
                model.decrementLanguage();
            case REGION:
                model.previousRegion();
                break;
            case GAME:
                if (model.getSelectedRecipe() != null) {
                    model.addSelectedIngredient(model.getSelectedRecipe().getIngredients()[tmp]);
                    tmp++;
                }
                break;
            case NAME:
                model.incrementLetter();
                break;
        }
    }

    public void scan(String barcode) {
        if (model.getCurrentScreen().equals(AvailableScreens.GAME)) {
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
