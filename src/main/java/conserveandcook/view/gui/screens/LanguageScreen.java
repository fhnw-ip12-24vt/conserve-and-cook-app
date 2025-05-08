package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Languages;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractGif;
import conserveandcook.view.gui.AvailableScreens;

public class LanguageScreen extends AbstractGif {
    static final int FRAMES = 4;
    static final long GIF_INTERVAL = 2200 * 100000; // 0.25 seconds

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
        System.out.println("lang.init");
        model.setLanguage(Languages.DE);
    }

    @Override
    public void press(Application model) {
        System.out.println("lang.press");
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
        System.out.println("lang.next");
        return AvailableScreens.TUTORIAL;
    }

    @Override
    public AvailableScreens prev() {
        return AvailableScreens.START;
    }
}
