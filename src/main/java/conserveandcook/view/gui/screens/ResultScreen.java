package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Highscore;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;

import java.util.Arrays;

public class ResultScreen extends AbstractScreen {

    public ResultScreen(Gui gui) {
        super(gui);
    }

    @Override
    public void draw(Application model) {
        model.setScore(model.getSelectedRecipe().calculateScore(model.getSelectedIngredients()));
        int score = model.getScore();
        drawBackground("img/result/frame_0.png");
        gui.setFontSize(70);
        gui.drawString(score + " Punkte", 500, 100);
        saveHighscore(model.getScore(), Arrays.toString(model.getName()));
    }

    private void saveHighscore(int score, String name) {
        Highscore highscore = Highscore.getInstance();
        // TODO: Save Highscore (only once)
        highscore.saveHighscore(score, name);
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.START;
    }
}
