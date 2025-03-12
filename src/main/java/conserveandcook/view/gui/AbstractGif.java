package conserveandcook.view.gui;

import ch.trick17.gui.Gui;

public abstract class AbstractGif extends AbstractScreen {
    // GIF
    private int currentFrame = 0;
    private final String baseFrame = "frame_";
    private final String imageFormat = ".png";
    private long lastGifFrameDrawnAt;
    protected int frames;
    protected long gifInterval;

    protected AbstractGif(Gui gui, int frames, long gifInterval) {
        super(gui);
        this.frames = frames;
        this.gifInterval = gifInterval;
    }

    /**
     * Calculating next frame
     *
     * @param basePath Path of the screen, usually img/{screen-name}
     * @return String of the next frame
     */
    protected String getGifFrame(String basePath) {
        long now = System.nanoTime();

        if (now - lastGifFrameDrawnAt > gifInterval) {
            lastGifFrameDrawnAt = now;
            currentFrame = currentFrame + 1 < frames ? currentFrame + 1 : 0;
        }
        String formattedFrame = String.format("%01d", currentFrame);
        return basePath + baseFrame + formattedFrame + imageFormat;
    }

    /**
     * Calculating next frame number
     */
    protected String getGifNumber() {
        long now = System.nanoTime();

        if (now - lastGifFrameDrawnAt > gifInterval) {
            lastGifFrameDrawnAt = now;
            currentFrame = currentFrame + 1 < frames ? currentFrame + 1 : 0;
        }
        return "/" + baseFrame + String.format("%01d", currentFrame);
    }
}
