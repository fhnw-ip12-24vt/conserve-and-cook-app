package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractGif;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Button;

public class StartScreen extends AbstractGif {
    static final int FRAMES = 4;
    static final long GIF_INTERVAL = 2500 * 100000; // 0.25 seconds
    private Application model;
    Button startButton;

    public StartScreen(Gui gui) {
        super(gui, FRAMES, GIF_INTERVAL);

        startButton = new Button(Button.Type.NO_GUI);
        components.add(startButton);
    }

    @Override
    public void init(Application model) {
        startButton.setOnPress(model::incrementScreen);
    }

    @Override
    public void draw(Application model) {
        String currentFrame = getGifFrame("img/start/");
        drawBackground(currentFrame);
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.LANGUAGE;
    }
}
