package ch.mvcbase;

import java.util.Timer;
import java.util.TimerTask;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;

/**
 * Base class for all PUIs.
 */
public abstract class PuiBase<M, C extends ControllerBase<M>> implements Projector<M, C> {
    protected final Context pi4J;

    private final Timer timer;

    public PuiBase(C controller, int frameRate) {
        pi4J = Pi4J.newAutoContext();

        init(controller);

        int updateDelay = 1000 / frameRate;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               updateComponents(controller.model);
            }
            }, updateDelay, updateDelay);
    }

    public void shutdown() {
        pi4J.shutdown();
        timer.cancel();
    }
}


