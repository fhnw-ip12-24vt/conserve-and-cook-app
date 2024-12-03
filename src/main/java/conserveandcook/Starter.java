package conserveandcook;

import conserveandcook.controller.ApplicationController;
import conserveandcook.model.Application;
import conserveandcook.view.gui.Screen;
import conserveandcook.view.pui.Hardware;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Starter {
    public static final int FRAME_RATE = 50;


    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "True");

        LOGGER.logInfo("App started");
        Database.getInstance();

        ApplicationController controller = new ApplicationController(new Application());
        Screen screen = new Screen(controller);

        Hardware pui = new Hardware(controller, FRAME_RATE);

        screen.open();
        screen.setFullScreen(true);
        screen.runUntilClosed(1000 / Starter.FRAME_RATE);

        controller.shutdown();
        pui.shutdown();
    }
}
