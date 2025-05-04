package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Highscore;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;

import java.util.Arrays;
import java.util.stream.Collectors;

import static ch.mvcbase.MvcLogger.LOGGER;


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
        // TODO (Elena, 4.5) : The following Arrays.toString does not convert to a 3 letter name
        // saveHighscore(model.getScore(), Arrays.toString(model.getName()));
        saveHighscore(model.getScore(), Arrays.stream(model.getName()).mapToObj(String::valueOf).collect(Collectors.joining()));

    }


    private void saveHighscore(int score, String name) {
        Highscore highscore = Highscore.getInstance();
        // TODO: Save Highscore (only once)
        try {
            highscore.saveHighscore(score, name);
        } catch (IllegalArgumentException e) {
            // TODO: (Elena, 4.5) What do i need to do with IllegalArgumentException's
            LOGGER.logError(String.format("We are ignoring IllegalArgumentException for now. Score is : %s, name %s, with length %s", score,name, name.length()));
        }
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.START;
    }
}
