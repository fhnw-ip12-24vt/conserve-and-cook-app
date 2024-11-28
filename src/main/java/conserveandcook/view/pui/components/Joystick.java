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

    private Runnable onNorth;
    private Runnable onEast;
    private Runnable onSouth;
    private Runnable onWest;

    private Runnable whileNorth;
    private Runnable whileEast;
    private Runnable whileSouth;
    private Runnable whileWest;

    private Duration whilePressedDelay;

    private boolean isNorth = false;
    private boolean isEast = false;
    private boolean isSouth = false;
    private boolean isWest = false;

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
            byte[] buffer = new byte[8]; // Joystick event structure size
            System.out.println("Reading joystick events from " + this.device);

            while (true) {
                int bytesRead = fis.read(buffer);
                if (bytesRead == 8) { // Each event is 8 bytes
                    parseEvent(buffer);
                }
            }
        } catch (Exception e) {
            logException(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private void parseEvent(byte[] buffer) {
        int value = (short) ((buffer[4] & 0xFF) | ((buffer[5] & 0xFF) << 8));
        byte type = buffer[6];
        byte number = buffer[7];

        // Event type 2 = Axis movement, type 1 = Button press
        if (value == 0) {
            setNeutral();
        } else if (type == 2) { // Axis movement
            switch (number) {
                case 1: // X-axis
                    isWest = (value > -30000);  // Move left
                    isEast = (value < 30000); // Move right
                    break;
                case 0: // Y-axis
                    isNorth = (value < -30000);   // Move up
                    isSouth = (value > 30000); // Move down
                    break;
                default:
                    // Ignore other axes
                    break;
            }
        } else if (type == 1) { // Button press
            // You can extend this to handle buttons if needed
            System.out.printf("Button %d %s%n", number, (value == 1) ? "pressed" : "released");
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

    public boolean isNorth() {
        return isNorth;
    }

    public boolean isEast() {
        return isEast;
    }

    public boolean isSouth() {
        return isSouth;
    }

    public boolean isWest() {
        return isWest;
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