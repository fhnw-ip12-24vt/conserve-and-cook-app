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
        joystick.onNorth(controller::up);
        joystick.onEast(controller::nextScreen);
        joystick.onWest(controller::prevScreen);
        joystick.onSouth(controller::down);
        scanner.onScan(controller::scan);
    }

    @Override
    public void initializeComponents(Application model) {
        joystick = new Joystick(Config.get("joystick_path"));
        scanner = new BarcodeScanner(Config.get("barcode_scanner_path"));
    }

    @Override
    public void shutdown(){
        super.shutdown();
        joystick.shutdown();
        scanner.shutdown();
    }
}
