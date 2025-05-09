package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Highscore;
import conserveandcook.model.Application;
import conserveandcook.model.Recipe;
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
        gui.setFontSize((int) (140 * SCALE));
        gui.drawString(score + " Punkte", 1000 * SCALE, 200 * SCALE);

        // wählt je nach score den richtigen Resultat Screen
        String path = getFrameName(score);

        drawBackground(path);
        gui.setFontSize((int) (80 * SCALE));
        gui.drawString(score + " Punkte", 1175 * SCALE, 400 * SCALE);

        drawComment(model);
        saveHighscore(model.getScore(), Arrays.stream(model.getName()).mapToObj(String::valueOf).collect(Collectors.joining()));

    }

    private static String getFrameName(int score) {
        String path = "";

        if (score <= 1500) {
            path = "img/result/frame_0.png";  //resultat sehr schlecht
        } else if (score <= 3500) {
            path = "img/result/frame_1.png";
        } else if (score <= 5500) {
            path = "img/result/frame_2.png";
        } else if (score <= 7500) {
            path = "img/result/frame_3.png";
        } else if (score <= 10000) {
            path = "img/result/frame_4.png";
        } else if (score <= 12000){
            path = "img/result/frame_5.png";  //resultat sehr gut
        }
        return path;
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

    private void drawComment(Application model) {
        Recipe selectedRecipe = model.getSelectedRecipe();
        String comment = selectedRecipe.getComment(model.getSelectedIngredients());
        if (comment == null) return;

        //position of the comment
        int commentX = (int) (720 * SCALE);
        int commentY = (int) (630 * SCALE);
        int lineHeight = (int) (45 * SCALE);
        gui.setFontSize((int) (50 * SCALE));

        // Zeilenumbruch
        StringBuilder line = new StringBuilder();
        for (String word : comment.split(" ")) {
            if (line.length() + word.length() > 36) {
                gui.drawString(line.toString().trim(), commentX, commentY);
                commentY += lineHeight;
                line = new StringBuilder();
            }
            line.append(word).append(" ");
        }
        gui.drawString(line.toString().trim(), commentX, commentY);
    }
}
