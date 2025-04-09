package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractGif;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Button;

public class StartScreen extends AbstractGif {
    static final int FRAMES = 4;
    static final long GIF_INTERVAL = 2500 * 100000; // 0.25 seconds

    public StartScreen(Gui gui) {
        super(gui, FRAMES, GIF_INTERVAL);
        components.add(new Button(this::next, 100, 100));
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
