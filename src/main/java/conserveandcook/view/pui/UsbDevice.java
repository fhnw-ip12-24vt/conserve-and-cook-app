package conserveandcook.view.pui;

import conserveandcook.misc.Environments;
import conserveandcook.view.pui.components.Button;
import conserveandcook.view.pui.components.Joystick;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static ch.mvcbase.MvcLogger.LOGGER;

public class UsbDevice implements Runnable {
    private Thread serialReaderThread;
    private final String device;
    private final Joystick joystick;
    private final Button button;
    private volatile boolean running = true;

    public UsbDevice(String device, Joystick joystick, Button button) {
        this.device = device;
        this.joystick = joystick;
        this.button = button;

        startReading();
    }

    private void startReading() {
        serialReaderThread = new Thread(this, "SerialJoystickReader");
        serialReaderThread.setDaemon(true);
        serialReaderThread.start();
    }

    public void shutdown() {
        serialReaderThread.interrupt();
        running = false;
        LOGGER.logInfo("Shutting down USB device: " + device);
    }

    @Override
    public void run() {
        if (Environments.get() == Environments.TEST) {
            LOGGER.logInfo("USB device input listener disabled in TEST environment");
            return;
        }

        int connectionAttempts = 0;
        int maxAttempts = 5;
        while (running) {
            File file = new File(device);

            if (!file.exists()) {
                connectionAttempts++;
                LOGGER.logInfo("USB device not found at: " + device + ". Retrying in 2s. (Attempt " + connectionAttempts + ")");
                sleep(2000);
                continue;
            }

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8];
                LOGGER.logInfo("Reading USB events from " + device);

                while (running) {
                    int bytesRead = fis.read(buffer);
                    if (Thread.interrupted()) throw new InterruptedException();

                    if (bytesRead == 8) {
                        parseEvent(buffer);
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.logInfo("USB device thread interrupted");
                Thread.currentThread().interrupt(); // Preserve interrupt status
            } catch (IOException e) {
                LOGGER.logException("IO error with USB device: " + e.getMessage(), e);
                sleep(2000); // Wait before retrying after I/O errors
            } catch (Exception e) {
                LOGGER.logException("Unexpected error in USB device thread: " + e.getMessage(), e);
                sleep(2000);
            }
        }

        LOGGER.logInfo("USB device listener thread stopped");
    }

    private void parseEvent(byte[] buffer) {
        boolean isNorth = false, isEast = false, isSouth = false, isWest = false;

        // The direction in which the joystick was moved
        int value = (short) ((buffer[4] & 0xFF) | ((buffer[5] & 0xFF) << 8));

        byte type = buffer[6]; // type 1 = Button Press, type 2 = Axis movement
        byte axis = buffer[7]; // 1 = X-Axis, 2 = Y-Axis

        // Joystick moved to center
        if (value == 0) {
            joystick.setNeutral();
            return;
        }

        // Button Pressed
        if (type == 1) {
            button.setPressed(true);
            return;
        }

        // Joystick moved on an axis
        switch (axis) {
            case 1: // X-axis
                isWest = (value > -30000);  // Move left
                isEast = (value < 30000); // Move right
                break;
            case 0: // Y-axis
                isNorth = (value < -30000);   // Move up
                isSouth = (value > 30000); // Move down
                break;
            default: // No other axis
                break;
        }
        joystick.setInput(isNorth, isEast, isSouth, isWest);
    }

    /**
     * Interrupt-Safe sleeping function
     * @param millis Wait time in milliseconds
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}
