package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;
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
        }
    }

    public void down() {
        switch (model.getCurrentScreen()) {
            case "language":
                model.decrementLanguage();
        }
    }

    public void scan(String barcode) {
        if (model.getCurrentScreen().equals("game")) {
            try{
                Ingredient scannedIngredient = Ingredient.getIngredientById(barcode);
                model.addSelectedIngredient(scannedIngredient);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            // TODO: (SK) Implement game logic
        }
    }
}
