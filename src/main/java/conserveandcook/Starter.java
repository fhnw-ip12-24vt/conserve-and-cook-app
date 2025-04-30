package conserveandcook;

import conserveandcook.controller.ApplicationController;
import conserveandcook.controller.BootController;
import conserveandcook.model.Application;
import conserveandcook.model.Boot;
import conserveandcook.view.gui.BootScreen;
import conserveandcook.view.gui.Window;
import conserveandcook.view.pui.Hardware;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Starter {
    public static final int FRAME_RATE = 50;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "True");

        LOGGER.logInfo("App started");

        // Initialize database connection
        Database.getInstance();

        ApplicationController controller = new ApplicationController(new Application());

        // Initialize hardware connection
        Hardware pui = new Hardware(controller, FRAME_RATE);

        // Open the boot window
        BootController bootController = new BootController(new Boot());
        BootScreen bootScreen = new BootScreen(bootController);
        bootScreen.open();
        bootScreen.runUntilClosed(1000 / Starter.FRAME_RATE);

        // Open the window
        Window gui = new Window(controller);
        gui.open();
        gui.runUntilClosed(1000 / Starter.FRAME_RATE);

        // After closing the window we run a shutdown sequence
        controller.shutdown();
        pui.shutdown();
    }
}
