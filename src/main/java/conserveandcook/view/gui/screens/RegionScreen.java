package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.I18n;
import conserveandcook.misc.Regions;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Button;
import conserveandcook.view.gui.components.ConfirmButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegionScreen extends AbstractScreen {
    ConfirmButton confirmButton;
    private ExecutorService executor;
    private Runnable preloadGamescreen;

    public RegionScreen(Gui gui) {
        super(gui);
        confirmButton = new ConfirmButton(
                Button.States.IDLE,
                (int) (680 * SCALE),
                (int) (840 * SCALE)
        );
        executor = Executors.newSingleThreadExecutor();
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
        executor.submit(getPreloadGamescreen(model));
    }

    @Override
    public void right(Application model) {
        if (confirmButton.isActive()) return;
        model.previousRegion();
        executor.submit(getPreloadGamescreen(model));
    }

    @Override
    public void left(Application model) {
        if (confirmButton.isActive()) return;
        model.nextRegion();
        executor.submit(getPreloadGamescreen(model));
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

    private Runnable getPreloadGamescreen(Application model) {
        return () -> {
            GameScreen gameScreen = (GameScreen) (Application.screens.get(AvailableScreens.GAME));
            gameScreen.preload(model);
        };
    }
}
