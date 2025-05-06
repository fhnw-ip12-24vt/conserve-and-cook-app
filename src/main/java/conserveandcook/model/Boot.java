package conserveandcook.model;

import conserveandcook.controller.ApplicationController;
import conserveandcook.view.gui.Window;
import conserveandcook.view.pui.Hardware;

import java.util.ArrayList;

import static conserveandcook.Starter.FRAME_RATE;

public class Boot {
    private ArrayList<String> logs = new ArrayList<>();
    private int capacity = 10;
    private boolean success = false;

    private Thread appThread;

    public void log(String log) {
        if (log == null) throw new IllegalArgumentException("Log must not be null");

        if (logs.size() >= capacity) {
            logs.removeFirst();
        }

        logs.add(log);
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }//NN added -> Add isSuccess() method to Boot model

    /**
     * Opens a new game window in its separate thread
     */
    public void openApplication() {
        if (appThread != null) {
            throw new RuntimeException("Application thread already open");
        }
        appThread = new Thread(() -> {
            try {
                ApplicationController controller = new ApplicationController(new Application());

                //Initialize hardware connection
                Hardware pui = new Hardware(controller, FRAME_RATE);

                //Open the window
                Window window = new Window(controller, this);
                window.open();
                log("Application opened");

                window.runUntilClosed(1000 / FRAME_RATE);

                //After closing the window we run a shutdown sequence
                controller.shutdown();
                pui.shutdown();
                log("Application stopped");
                Thread.currentThread().interrupt();

            } finally {
                appThread = null;
            }
        });

        appThread.start();
    }
}
