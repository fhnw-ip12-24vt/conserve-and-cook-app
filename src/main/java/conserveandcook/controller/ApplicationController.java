package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.model.Application;

public class ApplicationController extends ControllerBase<Application> {

    public ApplicationController(Application model) {
        super(model);
    }

    public void nextScreen() {
        model.incrementScreen();
        model.setIdleTime(0);
    }

    public void up() {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().up(model);
        model.setIdleTime(0);
    }

    public void down() {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().down(model);
        model.setIdleTime(0);
    }

    public void left() {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().left(model);
        model.setIdleTime(0);
    }
    public void right() {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().right(model);
        model.setIdleTime(0);
    }
    public void scan(String barcode) {
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().scan(model, barcode);
        model.setIdleTime(0);
    }

    public void press(){
        model.getActiveScreen().interaction(model);
        model.getActiveScreen().press(model);
    }
}
