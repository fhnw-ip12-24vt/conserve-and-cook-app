package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;

public class GameoverScreen extends AbstractScreen {

    private long time;
    private final long IDLE_TIME = 5_000;
    public GameoverScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        String lang = model.getSelectedLanguage().toString().toLowerCase();
        drawBackground("img/gameover/" + lang + ".png");

        long currentTime = System.currentTimeMillis();
        if(currentTime - time >= IDLE_TIME) {
            model.incrementScreen();
        }
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.RESULT;
    }

    @Override
    public void init(Application model) {
        time = System.currentTimeMillis();
    }
}