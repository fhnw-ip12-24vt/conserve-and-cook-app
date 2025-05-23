package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Highscore;
import conserveandcook.misc.I18n;
import conserveandcook.model.Application;
import conserveandcook.model.Recipe;
import conserveandcook.view.gui.AbstractScreen;
import conserveandcook.view.gui.AvailableScreens;

import static ch.mvcbase.MvcLogger.LOGGER;


public class ResultScreen extends AbstractScreen {
    private String comment = "";
    private String pointsTranslation;
    private int score = 0;

    public ResultScreen(Gui gui) {
        super(gui);
    }

    @Override
    public void draw(Application model) {
        // wählt je nach score den richtigen Resultat Screen
        String path = getFrameName(score);
        drawBackground(path);

        gui.setFontSize((int) (80 * SCALE));
        gui.drawString(score + " " + pointsTranslation , 1175 * SCALE, 400 * SCALE);

        drawComment(model);
    }

    @Override
    public void init(Application model) {
        score = 0;
        Recipe recipe = model.getSelectedRecipe();
        score = recipe.calculateScore(model.getSelectedIngredients());
        model.setScore(score);
        pointsTranslation = I18n.translate("points");
        comment = "";
//        saveHighscore(score, Arrays.stream(model.getName()).mapToObj(String::valueOf).collect(Collectors.joining()));
    }

    private static String getFrameName(int score) {
        String path;

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
        } else {
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
            LOGGER.logError(String.format("We are ignoring IllegalArgumentException for now. Score is : %s, name %s, with length %s", score, name, name.length()));
        }
    }

    @Override
    public void init(Application model) {
        comment = "";
    }

    @Override
    public AvailableScreens next() {
        return AvailableScreens.START;
    }

    private void drawComment(Application model) {
        if (comment == null || comment.isEmpty()) {
            Recipe selectedRecipe = model.getSelectedRecipe();
            comment = selectedRecipe.getComment(model.getSelectedIngredients());
            return;
        }

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
