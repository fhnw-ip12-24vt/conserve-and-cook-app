package conserveandcook.view.pui.components;

import com.pi4j.catalog.components.base.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Joystick extends Component {
    private static final Logger log = LoggerFactory.getLogger(Joystick.class);
    private String device;
    private Thread serialReaderThread;

    private Runnable onNorth, onEast, onSouth, onWest;

    private Runnable whileNorth, whileEast, whileSouth, whileWest;

    private Duration whilePressedDelay;

    private boolean isNorth, isEast, isSouth, isWest = false;

    private ExecutorService executor;

    public Joystick(String device) {
        this.device = device;
        startReading();
    }

    private void startReading() {
        serialReaderThread = new Thread(() -> listenToInput(), "SerialJoystickReader");
        serialReaderThread.setDaemon(true);
        serialReaderThread.start();
    }

    private void listenToInput() {
        try (FileInputStream fis = new FileInputStream(this.device)) {
            byte[] buffer = new byte[8]; // Joystick events are 8 bytes big
            log.info("Reading joystick events from {}", this.device);

            while (true) {
                int bytesRead = fis.read(buffer);
                if (bytesRead == 8) {
                    parseEvent(buffer);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void parseEvent(byte[] buffer) {
        // The direction in which the joystick was moved
        int value = (short) ((buffer[4] & 0xFF) | ((buffer[5] & 0xFF) << 8));

        // type 1 = Button Press, type 2 = Axis movement
        byte type = buffer[6];

        // 1 = X-Axis, 2 = Y-Axis
        byte axis = buffer[7];

        if (value == 0) {
            setNeutral();
        } else if (type == 2) { // Axis movement
            switch (axis) {
                case 1: // X-axis
                    isWest = (value > -30000);  // Move left
                    isEast = (value < 30000); // Move right
                    break;
                case 0: // Y-axis
                    isNorth = (value < -30000);   // Move up
                    isSouth = (value > 30000); // Move down
                    break;
                default:
                    // No other axis
                    break;
            }
        } else if (type == 1) {
            // Button press
        }

        executor = Executors.newSingleThreadExecutor();
        if (isNorth) {
            executor.submit(whileNorthWorker);
            if (onNorth != null) {
                onNorth.run();
            }
        }

        if (isEast) {
            executor.submit(whileEastWorker);
            if (onEast != null) {
                onEast.run();
            }
        }

        if (isSouth) {
            executor.submit(whileSouthWorker);
            if (onSouth != null) {
                onSouth.run();
            }
        }

        if (isWest) {
            executor.submit(whileWestWorker);
            if (onWest != null) {
                onWest.run();
            }
        }

    }

    private void setNeutral() {
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
}