package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.I18n;
import conserveandcook.misc.Regions;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Button;
import conserveandcook.view.gui.components.ConfirmButton;

public class RegionScreen extends AbstractScreen {
    ConfirmButton confirmButton;

    public RegionScreen(Gui gui) {
        super(gui);
        confirmButton = new ConfirmButton(
                Button.States.IDLE,
                (int) (680 * SCALE),
                (int) (840 * SCALE)
        );
    }

    @Override
    public void draw(Application model) {
        String backgroundPath = "img/regions/";
        String path = backgroundPath + model.getSelectedRegion().toString().toLowerCase() + ".png";
        drawBackground(path);

        gui.setTextAlignCenter();
        gui.setFontSize((int) (100 * SCALE));
        // TODO (SK): Translate this string
        gui.drawString(getText(), gui.getWidth() / 2, 160 * SCALE);

        confirmButton.setLanguage(model.getLanguage());
        confirmButton.draw(gui);
    }

    private static String getText() {
        return I18n.translate("region");
    }

    @Override
    public void init(Application model) {
        confirmButton.setLanguage(model.getLanguage());
        confirmButton.setState(Button.States.IDLE);
        model.setRegion(Regions.EUROPE);
    }

    @Override
    public void right(Application model) {
        if (confirmButton.isActive()) return;
        model.previousRegion();
    }

    @Override
    public void left(Application model) {
        if (confirmButton.isActive()) return;
        model.nextRegion();
    }

    @Override
    public void up(Application model) {
        confirmButton.setState(Button.States.IDLE);
    }

    @Override
    public void down(Application model) {
        confirmButton.setState(Button.States.ACTIVE);
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
