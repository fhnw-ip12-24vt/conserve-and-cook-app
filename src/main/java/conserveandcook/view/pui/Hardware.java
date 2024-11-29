package conserveandcook.view.pui;

import ch.mvcbase.PuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.model.Application;
import conserveandcook.view.pui.components.Joystick;

public class Hardware extends PuiBase<Application, ApplicationController> {
    private Joystick joystick;

    public Hardware(ApplicationController controller, int frameRate) {
        super(controller, frameRate);
    }

    @Override
    public void setupEventHandler(ApplicationController controller) {
        joystick.onNorth(controller::up);
        joystick.onEast(controller::nextScreen);
        joystick.onWest(controller::prevScreen);
        joystick.onSouth(controller::down);
    }

    @Override
    public void updateComponents(Application model) {
        // nothing to do in first place
    }

    @Override
    public void initializeComponents(Application model) {
        joystick = new Joystick("/dev/input/js0");
    }

    @Override
    public void shutdown(){
        super.shutdown();
        joystick.shutdown();
        // shutdown logic
    }
}
