package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.Window;
import conserveandcook.view.gui.components.Button;

public class  RegionScreen extends AbstractScreen {
    Button confirmButton;
    public RegionScreen(Gui gui) {
        super(gui);
        confirmButton = new Button(Button.Type.CONFIRM);
        confirmButton.setX((int) (((Window.WIDTH * SCALE) / 2) + 100));
        confirmButton.setY((int) (((Window.HEIGHT * SCALE) / 2) + 260));
        components.add(confirmButton);
    }

    @Override
    public void draw(Application model) {
        String backgroundPath = "img/regions/";
        String path = backgroundPath + model.getSelectedRegion().toString().toLowerCase() + ".png";
        drawBackground(path);

        confirmButton.setLanguages(model.getSelectedLanguage());

        gui.setTextAlignCenter();
        gui.setFontSize((int) (100 * SCALE));
        gui.drawString("Select Region", gui.getWidth() / 2, 160 * SCALE);
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
