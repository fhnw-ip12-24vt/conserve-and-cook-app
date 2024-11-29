package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.model.Application;

public class ApplicationController extends ControllerBase<Application> {
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
        switch (model.getCurrentScreen()) {
            case "game":
                // TODO: (SK) Implement game logic
        }
    }
}
