package conserveandcook;

import static ch.mvcbase.MvcLogger.LOGGER;

public class Starter {
    private static final int FRAME_RATE = 50;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {

        // SomeController controller = new SomeController();

        // SomePUI pui =  new SomePUI(controller, FRAME_RATE);

        LOGGER.logInfo("App started");

        // This will ensure Pi4J is properly finished. All I/O instances are
        // released by the system and shutdown in the appropriate
        // manner. It will also ensure that any background
        // threads/processes are cleanly shutdown and any used memory
        // is returned to the system.
        /* Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            controller.shutdown();
            pui.shutdown();
        }));

         */

    }
}
