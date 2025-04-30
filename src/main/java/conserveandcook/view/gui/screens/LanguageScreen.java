package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractGif;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.components.Button;

import java.util.List;

public class LanguageScreen extends AbstractGif {
    static final int FRAMES = 4;
    static final long GIF_INTERVAL = 2200 * 100000; // 0.25 seconds

    private final Button[] buttons;

    public LanguageScreen(Gui gui) {
        super(gui, FRAMES, GIF_INTERVAL);

        buttons = new Button[4];
        for (int i = 0; i < 4; i++) {
            buttons[i] = new Button(Button.Type.NO_GUI);
        }
        components.addMany(List.of(buttons));
    }

    @Override
    public void init(Application model) {
        for (int i = 0; i < 4; i++) {
            buttons[i].setOnPress(model::incrementScreen);
        }
    }

    @Override
    public void draw(Application model) {
        String lang = model.getSelectedLanguage().name().toLowerCase();
        String gifNumber = getGifNumber();
        String currentFrame = "img/language/" + lang + gifNumber + ".png";
        drawBackground(currentFrame);
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
