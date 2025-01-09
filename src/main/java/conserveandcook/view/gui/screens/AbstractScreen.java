package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Config;
import conserveandcook.model.Application;

public abstract class AbstractScreen {
    protected final Gui gui;
    private int currentFrame = 0;
    private String baseFrame = "frame_";
    private String imageFormat = ".png";
    private long lastGifFrameDrawnAt;
    private int frames;
    private long gifInterval;
    public static double SCALE = Double.parseDouble(Config.get("screen.scale"));

    public AbstractScreen(Gui gui) {
        this.gui = gui;
    }

    public AbstractScreen(Gui gui, int frames, long gifInterval) {
        this.gui = gui;
        this.frames = frames;
        this.gifInterval = gifInterval;
    }

    public abstract void draw(Application model);

    protected String getGifFrame(String basePath) {
        long now = System.nanoTime();

        if (now - lastGifFrameDrawnAt > gifInterval) {
            lastGifFrameDrawnAt = now;
            currentFrame = currentFrame + 1 < frames ? currentFrame + 1 : 0;
        }
        String formattedFrame = String.format("%01d", currentFrame);
        return basePath + baseFrame + formattedFrame + imageFormat;
    }

    protected String getGifNumber() {
        long now = System.nanoTime();

        if (now - lastGifFrameDrawnAt > gifInterval) {
            lastGifFrameDrawnAt = now;
            currentFrame = currentFrame + 1 < frames ? currentFrame + 1 : 0;
        }
        return "/" + baseFrame + String.format("%01d", currentFrame);
    }

    protected void drawBackground(String frame) {
        gui.drawImage(frame, 0, 0, SCALE);
    }
}
