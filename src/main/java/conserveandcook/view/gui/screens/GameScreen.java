package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;

public class GameScreen extends AbstractScreen {
    private int gameDuration = 120; // 2 minutes in seconds
    private long lastUpdateTime;

    public GameScreen(Gui g) {
        super(g);
    }

    public void draw(Application model) {
        drawBackground("img/game/frame_0.png");

        Ingredient[] ingredients = model.getSelectedIngredients();
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                drawBorder(ingredient.getCategory());
            }
        }
        gameTimer();
    }

    private void drawBorder(int category) {
        String borderPath = "img/game/border.png";
        double x, y;
        switch (category) {
            case 0:
                x = (double) 690;
                y = (double) 175;
                break;
            case 1:
                x = (double) 930;
                y = (double) 535;
                break;
            case 2:
                x = (double) 1210;
                y = (double) 190;
                break;
            default:
                return;
        }
        gui.drawImage(borderPath, x * SCALE, y * SCALE, SCALE);
    }

    public void gameTimer() {
        long currentTime = System.nanoTime();
        long goneTime = currentTime - lastUpdateTime;

        if (gameDuration <= 0) {
            gameOver();
            return;
        }
        if (goneTime >= 1000000000L) {
            gameDuration--;
            lastUpdateTime = currentTime;
        }


        drawTime();
    }

    private void drawTime() {
        int minutes = gameDuration / 60;
        int seconds = gameDuration % 60;
        String time = String.format("%02d:%02d", minutes, seconds);

        gui.drawString(time, 50, 50);
    }

    private void gameOver() {
        gui.drawString("Game Over", 50, 50);
    }
}