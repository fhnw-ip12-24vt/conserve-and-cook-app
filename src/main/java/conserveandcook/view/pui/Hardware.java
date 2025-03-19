package conserveandcook.view.pui;

import ch.mvcbase.PuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.misc.Config;
import conserveandcook.model.Application;
import conserveandcook.view.pui.components.BarcodeScanner;
import conserveandcook.view.pui.components.Joystick;

public class Hardware extends PuiBase<Application, ApplicationController> {
    private Joystick joystick;
    private BarcodeScanner scanner;

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
    }

    @Override
    public void initializeComponents(Application model) {
        if (Config.isEnabled("joystick")) {
            joystick = new Joystick(Config.get("joystick.path"));
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
        if (joystick != null) {
            joystick.shutdown();
        }
    }
}
