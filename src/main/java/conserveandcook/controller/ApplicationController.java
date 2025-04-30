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
        model.getActiveScreen().up(model);
    }

    public void down() {
        model.getActiveScreen().down(model);
    }

    public void left() {
        model.getActiveScreen().left(model);
    }

    public void right() {
        model.getActiveScreen().right(model);
    }

    public void scan(String barcode) {
        model.getActiveScreen().scan(model, barcode);
    }

    public void press(){
        model.getActiveScreen().getComponents().press();
    }
}
