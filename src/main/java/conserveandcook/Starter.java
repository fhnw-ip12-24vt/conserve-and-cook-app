package conserveandcook;

import conserveandcook.controller.BootController;
import conserveandcook.model.Boot;
import conserveandcook.view.gui.BootScreen;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Starter {
    public static final int FRAME_RATE = 10;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "True");

        LOGGER.logInfo("App started");

        // Initialize database connection
        Database.getInstance();

        // Open the boot window
        Boot boot = new Boot();
        BootController bootController = new BootController(boot);
        BootScreen bootScreen = new BootScreen(bootController);
        bootScreen.open();
        bootController.boot();
        bootScreen.runUntilClosed(1000 / FRAME_RATE);
    }
}
