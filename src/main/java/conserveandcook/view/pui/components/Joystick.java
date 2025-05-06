package conserveandcook.view.pui.components;

import com.pi4j.catalog.components.base.Component;
import conserveandcook.misc.Environments;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Joystick extends Component {

    private Runnable onNorth, onEast, onSouth, onWest;
    private Runnable whileNorth, whileEast, whileSouth, whileWest;
    private boolean isNorth, isEast, isSouth, isWest = false;

    private Duration whilePressedDelay;
    private ExecutorService executor;

    public void setDirection(boolean isInDirection, Runnable worker, Runnable task) {
        if (isInDirection) {
            if (executor == null) return;
            executor.submit(worker);
            if (task != null) {
                task.run();
            }
        }
    }

    public void setNeutral() {
        isNorth = false;
        isEast = false;
        isWest = false;
        isSouth = false;
    }

    private final Runnable whileNorthWorker = () -> {
        while (isNorth && whileNorth != null) {
            delay(whilePressedDelay);
            whileNorth.run();
        }
    };

    private final Runnable whileEastWorker = () -> {
        while (isEast && whileEast != null) {
            delay(whilePressedDelay);
            whileEast.run();
        }
    };

    private final Runnable whileSouthWorker = () -> {
        while (isSouth && whileSouth != null) {
            delay(whilePressedDelay);
            whileSouth.run();
        }
    };

    private final Runnable whileWestWorker = () -> {
        while (isWest && whileWest != null) {
            delay(whilePressedDelay);
            whileWest.run();
        }
    };

    public void whileNorth(Runnable task, Duration delay) {
        whileNorth = task;
        whilePressedDelay = delay;
        if (executor != null) {
            executor.shutdownNow();
        }
        if (task != null) {
            executor = Executors.newSingleThreadExecutor();
        }
    }

    public void whileSouth(Runnable task, Duration delay) {
        whileSouth = task;
        whilePressedDelay = delay;
        if (executor != null) {
            executor.shutdownNow();
        }
        if (task != null) {
            executor = Executors.newSingleThreadExecutor();
        }
    }

    public void whileEast(Runnable task, Duration delay) {
        whileEast = task;
        whilePressedDelay = delay;
        if (executor != null) {
            executor.shutdownNow();
        }
        if (task != null) {
            executor = Executors.newSingleThreadExecutor();
        }
    }

    public void whileWest(Runnable task, Duration delay) {
        whileWest = task;
        whilePressedDelay = delay;
        if (executor != null) {
            executor.shutdownNow();
        }
        if (task != null) {
            executor = Executors.newSingleThreadExecutor();
        }
    }

    public void onNorth(Runnable task) {
        this.onNorth = task;
    }

    public void onEast(Runnable task) {
        this.onEast = task;
    }

    public void onSouth(Runnable task) {
        this.onSouth = task;
    }

    public void onWest(Runnable task) {
        this.onWest = task;
    }

    // For testing
    public void mockInput() {
        if (Environments.get() == Environments.TEST) {
            executor = Executors.newSingleThreadExecutor();
            setDirection(true, whileNorthWorker, onNorth);
            setDirection(true, whileEastWorker, onEast);
            setDirection(true, whileSouthWorker, onSouth);
            setDirection(true, whileWestWorker, onWest);
        }
    }

    public void setInput(boolean isNorth, boolean isEast, boolean isSouth, boolean isWest) {
        setDirection(isWest, whileNorthWorker, onNorth);
        setDirection(isEast, whileEastWorker, onEast);
        setDirection(isSouth, whileSouthWorker, onSouth);
        setDirection(isWest, whileWestWorker, onWest);
    }
}