package conserveandcook.view.gui.screens;

public class ResultScreen implements Screen {
    private int currentFrame = 0;

    @Override
    public String getTitle() {
        return "result";
    }

    @Override
    public String getCurrentFrame(String language) {
        String backgroundPath = "img/result";
        String frameName = String.format("%01d", currentFrame);
        return backgroundPath + "/frame_" + frameName + ".png";
    }
}
