package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.misc.Config;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AvailableScreens;

import static ch.mvcbase.MvcLogger.LOGGER;

public class ApplicationController extends ControllerBase<Application> {

    public static int TIMEOUT = Integer.parseInt(Config.get("screen.timeout"));
    private long idleTime = 0;
    private final long startTime = System.currentTimeMillis();
    private final Thread timeoutWorker;

    private final Runnable timeoutRunnable = () -> {
        boolean running = true;
        while (running) {
            if (System.currentTimeMillis() - startTime > 1000) {
                idleTime += 1;
            }

            if (idleTime > TIMEOUT && model.getCurrentScreen() != AvailableScreens.START) {
                model.timeoutScreen();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.logException("Timeout thread interrupted", e);
                running = false;
            }
        }
    };

    public ApplicationController(Application model) {
        super(model);
        this.timeoutWorker = new Thread(timeoutRunnable);
        this.timeoutWorker.start();
    }

    @Override
    public void shutdown() {
        super.shutdown();
        this.timeoutWorker.interrupt();
        LOGGER.logInfo("Shutting down timeout thread");
    }

    public void nextScreen() {
        model.incrementScreen();
        this.idleTime = 0;
    }

    public void prevScreen() {
        model.decrementScreen();
        this.idleTime = 0;
    }

    public void up() {
        model.getActiveScreen().up(model);
        this.idleTime = 0;
    }

    public void down() {
        model.getActiveScreen().down(model);
        this.idleTime = 0;
    }

    public void left() {
        model.getActiveScreen().left(model);
        this.idleTime = 0;
    }

    public void right() {
        model.getActiveScreen().right(model);
        this.idleTime = 0;
    }

    public void scan(String barcode) {
        model.getActiveScreen().scan(model, barcode);
        this.idleTime = 0;
    }
}
