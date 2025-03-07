package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.Window;

public class RegionScreen extends AbstractScreen {
    public RegionScreen(Gui gui) {
        super(gui);
    }

    @Override
    public void draw(Application model) {
        String backgroundPath = "img/regions/";
        String path = backgroundPath + model.getSelectedRegion().toString().toLowerCase() + ".png";
        drawBackground(path);

        gui.setFontSize((int) (100 * SCALE));
        gui.drawString("Select Region", (Window.WIDTH / 2) - 150, 80);
    }

    @Override
    public void up(Application model) {
        model.nextRegion();
    }

    @Override
    public void down(Application model) {
        model.previousRegion();
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.GAME;
    }

    @Override
    public AvailableScreens prev() {
        return AvailableScreens.LANGUAGE;
    }
}
