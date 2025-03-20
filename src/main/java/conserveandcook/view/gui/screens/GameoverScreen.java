package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;

public class GameoverScreen extends AbstractScreen {

    public GameoverScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        String lang = model.getSelectedLanguage().toString().toLowerCase();
        drawBackground("img/gameover/" + lang + ".png");
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.RESULT;
    }
}