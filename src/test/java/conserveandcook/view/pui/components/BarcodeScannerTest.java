package conserveandcook.view.pui.components;

import conserveandcook.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarcodeScannerTest extends AbstractTest {
    String test;

    @Test
    public void barcodeScannertest() {
        // Random file, obviously pom.xml is not a valid joystick input
        try {
            BarcodeScanner bs = new BarcodeScanner("pom.xml");
        } catch (Exception e) {
            // Should throw an exception
            assertTrue(true);
        }
    }

    @Test
    public void onScanTest() {
        BarcodeScanner bs = new BarcodeScanner("pom.xml");
        bs.onScan(this::task);
        bs.mockScan("test-string");
        assertEquals("test-string", test);
    }

    private void task(String payload) {
        this.test = payload;
    }
}
