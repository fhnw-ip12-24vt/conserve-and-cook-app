package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;

public class RegionScreen extends AbstractScreen {
    public RegionScreen(Gui gui) {
        super(gui);
    }

    @Override
    public void draw(Application model) {
        String backgroundPath = "img/regions/";
        String path = backgroundPath + model.getSelectedRegion() + ".png";
        drawBackground(path);
    }
}
