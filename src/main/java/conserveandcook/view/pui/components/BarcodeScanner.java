package conserveandcook.view.pui.components;

import com.pi4j.catalog.components.base.Component;
import conserveandcook.misc.Environments;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.function.Consumer;

import static ch.mvcbase.MvcLogger.LOGGER;

public class BarcodeScanner extends Component {
    private final String devicePath;
    private Thread barcodeScannerThread;
    private Consumer<String> onScan;

    public BarcodeScanner(String path) {
        devicePath = path;
        startReading();
    }

    private void startReading() {
        barcodeScannerThread = new Thread(this::listenToInput);
        barcodeScannerThread.setDaemon(true);
        barcodeScannerThread.start();
    }

    public void shutdown() {
        barcodeScannerThread.interrupt();
        LOGGER.logInfo("Shutting down Barcode Scanner");
    }

    private void listenToInput() {
        if (Environments.get() == Environments.TEST) {
            return;
        }
        try (FileInputStream inputStream = new FileInputStream(devicePath)) {
            LOGGER.logInfo("Listening for barcode scans on " + devicePath);
            StringBuilder barcode = new StringBuilder();

            // Mapping of key codes to characters
            String[] keyMap = {
                    "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                    "-", "=", "", "", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
                    "[", "]", "", "a", "s", "d", "f", "g", "h", "j", "k", "l", ";",
                    "'", "`", "", "\\", "z", "x", "c", "v", "b", "n", "m", ",", ".", "/"
            };
            while (true) {
                InputEvent event = parseEvent(inputStream);
                // Check if the event is a key press (type 1, value 1)
                if (event.type == 1 && event.value == 1) { // Key pressed
                    if (event.code > 0 && event.code < keyMap.length) {
                        String key = keyMap[event.code];
                        barcode.append(key);
                    }
                } else if (event.value == 0) { // Key released
                    if (event.code == 28) { // Enter key
                        // Run the onScan worker, if it has been set
                        if (onScan != null) {
                            onScan.accept(barcode.toString());
                        }
                        barcode.setLength(0); // Reset barcode buffer
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.logError("Error reading from {}: {}", devicePath, e.getMessage(), e);
        }
    }

    public static class InputEvent {
        public long timeSeconds;
        public long timeMicroseconds;
        public short type;
        public short code;
        public int value;
    }

    private static InputEvent parseEvent(FileInputStream inputStream) throws IOException {
        byte[] buffer = new byte[24]; // InputEvent size is 24 bytes
        int bytesRead = inputStream.read(buffer);
        if (bytesRead < buffer.length) {
            throw new IOException("Incomplete input event read");
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        InputEvent event = new InputEvent();
        event.timeSeconds = byteBuffer.getLong(0);
        event.timeMicroseconds = byteBuffer.getLong(8);
        event.type = byteBuffer.getShort(16);
        event.code = byteBuffer.getShort(18);
        event.value = byteBuffer.getInt(20);
        return event;
    }

    public void onScan(Consumer<String> task) {
        this.onScan = task;
    }

    public void mockScan(String payload) {
        if (onScan != null) {
            onScan.accept(payload);
        }
    }
}
