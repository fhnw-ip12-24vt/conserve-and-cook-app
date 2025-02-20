package conserveandcook.view.gui.screens;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Config;
import conserveandcook.model.Application;
import conserveandcook.view.gui.AvailableScreens;

public abstract class AbstractScreen {
    protected final Gui gui;
    private static AbstractScreen instance;

    public static double SCALE = Double.parseDouble(Config.get("screen.scale"));

    // GIF
    private int currentFrame = 0;
    private String baseFrame = "frame_";
    private String imageFormat = ".png";
    private long lastGifFrameDrawnAt;
    private int frames;
    private long gifInterval;

    protected AbstractScreen(Gui gui) {
        this.gui = gui;
    }

    protected AbstractScreen(Gui gui, int frames, long gifInterval) {
        this(gui);
        this.frames = frames;
        this.gifInterval = gifInterval;
    }

    public abstract void draw(Application model);

    public AvailableScreens next(){
        return null;
    }
    public AvailableScreens prev(){
        return null;
    }
    public void left(Application model) {}
    public void right(Application model) {}
    public void scan(Application model, String barcode) {}
    public void up(Application model) {};
    public void down(Application model) {};

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

    protected void drawBackground(String frame) {
        gui.drawImage(frame, 0, 0, SCALE);
    }
}
