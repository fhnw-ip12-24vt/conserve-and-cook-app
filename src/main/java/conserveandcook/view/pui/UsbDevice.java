package conserveandcook.view.pui;

import conserveandcook.misc.Environments;
import conserveandcook.view.pui.components.Button;
import conserveandcook.view.pui.components.Joystick;

import java.io.FileInputStream;

import static ch.mvcbase.MvcLogger.LOGGER;

public class UsbDevice {
    private Thread serialReaderThread;
    private final String device;
    private final Joystick joystick;
    private final Button button;

    public UsbDevice(String device, Joystick joystick, Button button) {
        this.device = device;
        this.joystick = joystick;
        this.button = button;

        startReading();
    }

    private void startReading() {
        serialReaderThread = new Thread(this::listenToInput, "SerialJoystickReader");
        serialReaderThread.setDaemon(true);
        serialReaderThread.start();
    }

    public void shutdown() {
        serialReaderThread.interrupt();
        LOGGER.logInfo("Shutting down USB device: " + device);
    }

    private void listenToInput() {
        if (Environments.get() == Environments.TEST) {
            return;
        }
        try (FileInputStream fis = new FileInputStream(this.device)) {
            byte[] buffer = new byte[8]; // Joystick events are 8 bytes long
            LOGGER.logInfo("Reading USB events from " + this.device);

            while (true) {
                int bytesRead = fis.read(buffer);
                if (bytesRead == 8) {
                    parseEvent(buffer);
                }
            }
        } catch (Exception e) {
            LOGGER.logException(e.getMessage(), e);
            shutdown();
        }
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
        joystick.setInput(isNorth,isEast,isSouth,isWest);
    }
}
