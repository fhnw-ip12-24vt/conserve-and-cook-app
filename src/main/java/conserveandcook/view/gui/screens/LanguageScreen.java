package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;

import java.util.Locale;

public class LanguageScreen extends AbstractScreen {
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
}
