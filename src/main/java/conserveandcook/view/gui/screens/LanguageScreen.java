package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Languages;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractGif;
import conserveandcook.view.gui.AvailableScreens;

public class LanguageScreen extends AbstractGif {
    static final int FRAMES = 4;
    static final long GIF_INTERVAL = 5000 * 100000; // 0.5 seconds

    public LanguageScreen(Gui gui) {
        super(gui, FRAMES, GIF_INTERVAL);
    }

    @Override
    public void draw(Application model) {
        String lang = model.getSelectedLanguage().name().toLowerCase();
        String gifNumber = getGifNumber();
        String currentFrame = "img/language/" + lang + gifNumber + ".png";
        drawBackground(currentFrame);
    }

    @Override
    public void init(Application model) {
        model.setLanguage(Languages.DE);
    }

    @Override
    public void press(Application model) {
        model.incrementScreen();
    }

    @Override
    public void up(Application model) {
        model.incrementLanguage();
    }

    @Override
    public void down(Application model) {
        model.decrementLanguage();
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.TUTORIAL;
    }

    @Override
    public AvailableScreens prev() {
        return AvailableScreens.START;
    }
}
