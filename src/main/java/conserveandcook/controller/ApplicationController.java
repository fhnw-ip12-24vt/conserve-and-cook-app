package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.Database;
import conserveandcook.model.Application;

import java.util.HashMap;
import java.util.Map;

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
}
