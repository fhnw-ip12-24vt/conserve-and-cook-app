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
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().up(model);
    }

    public void down() {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().down(model);
    }

    public void left() {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().left(model);
    }
    public void right() {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().right(model);
    }
    public void scan(String barcode) {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().scan(model, barcode);
    }

    public void press(){
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().press(model);
    }
}
