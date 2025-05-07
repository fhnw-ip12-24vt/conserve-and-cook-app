package conserveandcook.view.pui.components;

import com.pi4j.catalog.components.base.Component;
import conserveandcook.misc.Environments;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Joystick extends Component {

    private Runnable onNorth, onEast, onSouth, onWest;
    private boolean isNorth, isEast, isSouth, isWest = false;

    private Duration whilePressedDelay;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void setDirection(boolean isInDirection, Runnable task) {
        if (isInDirection) {
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

    public void shutdown() {
        executor.shutdownNow();
    }

    // For testing
    public void mockInput() {
        if (Environments.get() == Environments.TEST) {
            setDirection(true, onNorth);
            setDirection(true, onEast);
            setDirection(true, onSouth);
            setDirection(true, onWest);
        }
    }

    public void setInput(boolean isNorth, boolean isEast, boolean isSouth, boolean isWest) {
        setDirection(isNorth, onNorth);
        setDirection(isEast, onEast);
        setDirection(isSouth, onSouth);
        setDirection(isWest, onWest);
    }
}