package conserveandcook.checks;

import conserveandcook.misc.Config;
import conserveandcook.misc.Environments;

import java.io.FileInputStream;
import java.io.IOException;

public class DeviceCheck extends Check {

    @Override
    public boolean run() {
        if (Environments.get() != Environments.PRODUCTION) return true;

        String scannerPath = Config.get("barcode_scanner.path");
        String joystickPath = Config.get("joystick.path");
        return findDevice(scannerPath) && findDevice(joystickPath);
    }

    private boolean findDevice(String path) {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            return true;
        } catch (IOException e) {
            throw new CheckException("Device not found: " + path + e);
        }
    }
}
