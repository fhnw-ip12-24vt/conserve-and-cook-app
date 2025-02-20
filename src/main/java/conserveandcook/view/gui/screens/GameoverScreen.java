package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;

public class GameoverScreen extends AbstractScreen  {

    private GameoverScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        drawBackground("img/gameover/frame_0.png");

    }

}