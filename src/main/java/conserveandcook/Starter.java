package conserveandcook;

import conserveandcook.controller.ApplicationController;
import conserveandcook.model.Application;
import conserveandcook.view.gui.Screen;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Starter {
    public static final int FRAME_RATE = 50;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "True");
        LOGGER.logInfo("App started");

        ApplicationController controller = new ApplicationController(new Application());
        Screen screen = new Screen(controller);

        screen.open();
        screen.setFullScreen(true);
        screen.runUntilClosed(1000 / Starter.FRAME_RATE);

        controller.shutdown();
    }
}
