package conserveandcook.view.gui.screens;

public class RegionScreen implements conserveandcook.view.gui.screens.Screen {
    private long lastGifFrameDrawnAt;
    private int currentFrame;

    final int FRAMES = 4;
    final long GIF_INTERVAL = 2200 * 100000; // 0.25 seconds

    @Override
    public String getTitle() {
        return "regions";
    }

    @Override
    public String getCurrentFrame(String regions) {
        String backgroundPath = "img/regions/Amerika";
        long now = System.nanoTime();

        String frameName = String.format("%01d", currentFrame);
        return backgroundPath + frameName + ".png";
    }
}
