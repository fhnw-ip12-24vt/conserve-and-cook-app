package conserveandcook.view.pui;

import ch.mvcbase.PuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.misc.Config;
import conserveandcook.model.Application;
import conserveandcook.view.pui.components.BarcodeScanner;
import conserveandcook.view.pui.components.Button;
import conserveandcook.view.pui.components.Joystick;

public class Hardware extends PuiBase<Application, ApplicationController> {
    private Joystick joystick;
    private BarcodeScanner scanner;
    private Button button;
    private UsbDevice usb;

    public Hardware(ApplicationController controller, int frameRate) {
        super(controller, frameRate);
    }

    @Override
    public void setupEventHandler(ApplicationController controller) {
        if (joystick != null) {
            joystick.onNorth(controller::up);
            joystick.onEast(controller::right);
            joystick.onWest(controller::left);
            joystick.onSouth(controller::down);
        }

        if (scanner != null) {
            scanner.onScan(controller::scan);
        }

        if (button != null) {
            button.onPress(controller::press);
        }
    }

    @Override
    public void initializeComponents(Application model) {
        if (Config.isEnabled("joystick")) {
            joystick = new Joystick();
            button = new Button();

            usb = new UsbDevice(Config.get("joystick.path"), joystick, button);
        }

        if (Config.isEnabled("barcode_scanner")) {
            scanner = new BarcodeScanner(Config.get("barcode_scanner.path"));
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
        if (scanner != null) {
            scanner.shutdown();
        }
        if (usb != null) {
            usb.shutdown();
        }
    }
}
