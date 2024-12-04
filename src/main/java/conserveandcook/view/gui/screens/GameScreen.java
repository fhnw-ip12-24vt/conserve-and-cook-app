package conserveandcook.view.gui.screens;

public class GameScreen implements Screen {
    private int currentFrame = 0;
    private int gameDuration = 120;
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
            lastUpdateTime = currentTime;
            //updateFrame() // TODO: (AS) Methode muss noch geschrieben werden + GUI Anbindung
        }
        if (gameDuration <=0) {
            GameOver();
        }
        // private void updateFrame () // TODO: (AS) Methode & Umwandlung in Format XX:XX
    }
    private void GameOver (){
        System.out.println("GAME OVER");
    }
}
