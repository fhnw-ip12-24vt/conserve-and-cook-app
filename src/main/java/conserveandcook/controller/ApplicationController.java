package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.model.Application;

public class ApplicationController extends ControllerBase<Application> {

    public ApplicationController(Application model) {
        super(model);
    }

    public void up() {
        model.setIdleTime(0);
        boolean shouldContinue = model.getActiveScreen().interaction(model);
        if (! shouldContinue) return;

        model.getActiveScreen().up(model);
    }

    public void down() {
        model.setIdleTime(0);
        boolean shouldContinue = model.getActiveScreen().interaction(model);
        if (! shouldContinue) return;

        model.getActiveScreen().down(model);
    }

    public void left() {
        model.setIdleTime(0);
        boolean shouldContinue = model.getActiveScreen().interaction(model);
        if (! shouldContinue) return;

        model.getActiveScreen().left(model);
    }
    public void right() {
        model.setIdleTime(0);
        boolean shouldContinue = model.getActiveScreen().interaction(model);
        if (! shouldContinue) return;

        model.getActiveScreen().right(model);
    }
    public void scan(String barcode) {
        model.setIdleTime(0);
        boolean shouldContinue = model.getActiveScreen().interaction(model);
        if (! shouldContinue) return;

        model.getActiveScreen().scan(model, barcode);
    }

    public void press(){
        model.setIdleTime(0);
        boolean shouldContinue = model.getActiveScreen().interaction(model);
        if (! shouldContinue) return;

        model.getActiveScreen().press(model);
    }

    public void shutdown(){
        super.shutdown();
        System.exit(1);
    }
}
