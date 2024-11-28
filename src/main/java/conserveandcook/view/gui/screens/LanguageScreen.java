package conserveandcook.view.gui.screens;

public class LanguageScreen implements conserveandcook.view.gui.screens.Screen {
    private long lastGifFrameDrawnAt;
    private int currentFrame;

    final int FRAMES = 4;
    final long GIF_INTERVAL = 2200 * 100000; // 0.25 seconds

    @Override
    public String getTitle() {
        return "language";
    }

    public String getCurrentFrame(String language) {
        String backgroundPath = "img/language/";

        long now = System.nanoTime();

        if (now - lastGifFrameDrawnAt > GIF_INTERVAL) {
            lastGifFrameDrawnAt = now;
            currentFrame = currentFrame + 1 < FRAMES ? currentFrame + 1 : 0;
        }
        String frameName = String.format("%01d", currentFrame);
        return backgroundPath + language + "/frame_" + frameName + ".png";
    }
}
