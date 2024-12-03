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

    public void right() {
        switch (model.getSelectedRegion()) {
            case "regions":
                model.nextRegion();
        }
    }

    public void left() {
        switch (model.getSelectedRegion()) {
            case "regions":
                model.previousRegion();
        }
    }

    public void scan(String barcode) {
        if (model.getCurrentScreen().equals("game")) {
            // TODO: (SK) Implement game logic
        }
    }
}
