package conserveandcook.view.gui.screens;

public class GameScreen implements Screen {
    private int currentFrame = 0;

    @Override
    public String getTitle() {
        return "game";
    }

    public String getCurrentFrame(String language) {
        String backgroundPath = "img/game";
        String frameName = String.format("%01d", currentFrame);
        return backgroundPath + "/frame_" + frameName + ".png";
    }
}
