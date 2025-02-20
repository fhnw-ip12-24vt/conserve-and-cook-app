package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AvailableScreens;

public class ResultScreen extends AbstractScreen {

    public ResultScreen(Gui gui) {
        super(gui);
    }

    @Override
    public void draw(Application model) {
        drawBackground("img/result/frame_0.png");
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.START;
    }
}
