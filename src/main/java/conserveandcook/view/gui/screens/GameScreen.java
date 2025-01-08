package conserveandcook.view.gui.screens;

public class GameScreen implements Screen {
    private int currentFrame = 0;
    private int gameDuration = 120; //2 Minuten
    private long lastUpdateTime;

    @Override
    public String getTitle() {
        return "game";
    }

    public String getCurrentFrame(String language) {
        String backgroundPath = "img/game";
        String frameName = String.format("%01d", currentFrame);
        return backgroundPath + "/frame_" + frameName + ".png";
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