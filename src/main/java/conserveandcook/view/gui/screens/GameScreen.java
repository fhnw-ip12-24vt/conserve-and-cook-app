package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.model.Application;
import conserveandcook.model.Ingredient;

public class GameScreen implements Screen {
    private int currentFrame = 0;
    private int gameDuration = 120; //2 Minuten
    private long lastUpdateTime;

public class GameScreen extends AbstractScreen {

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

    }

    private void drawBorder(int category) {
        String borderPath = "img/game/border.png";
        double x, y;
        switch (category) {
            case 0:
                x = (double) 690 ;
                y = (double) 175 ;
                break;
            case 1:
                x = (double) 930 ;
                y = (double) 535 ;
                break;
            case 2:
                x = (double) 1210;
                y = (double) 190 ;
                break;
            default:
                return;
        }
        gui.drawImage(borderPath, x * SCALE, y * SCALE, SCALE);
    }

    public void startGame() {
        lastUpdateTime = System.nanoTime();
    }

    public void gameTimer() {
        long currentTime = System.nanoTime();
        long goneTime = currentTime - lastUpdateTime;

        if (goneTime >= 1000000000L) {
            gameDuration--;
            currentFrame = (currentFrame + 1) % 10;
            lastUpdateTime = currentTime;
        }

        if (gameDuration <= 0) {
            GameOver();
        }

        drawTime();
    }

    private void drawTime() {
        int minutes = gameDuration / 60;
        int seconds = gameDuration % 60;
        String time = String.format("%02d:%02d", minutes, seconds);
        // 50, 50 habe ich mal als Platzhalter für die Position
        System.out.println("Draw String: " + time + " at (50, 50)");
    }

    private void GameOver() {
        System.out.println("GAME OVER");
    }
}