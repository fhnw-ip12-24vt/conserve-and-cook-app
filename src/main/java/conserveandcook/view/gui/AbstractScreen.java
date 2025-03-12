package conserveandcook.view.gui;

import ch.trick17.gui.Gui;
import conserveandcook.misc.Config;
import conserveandcook.model.Application;

public abstract class AbstractScreen {
    protected final Gui gui;
    public static double SCALE = Double.parseDouble(Config.get("screen.scale"));

    protected AbstractScreen(Gui gui) {
        this.gui = gui;
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
     * Draws a given frame on the full width & height of the window.
     * @param frame Path to the image. Avoid leading /
     */
    protected void drawBackground(String frame) {
        gui.drawImage(frame, 0, 0, SCALE);
    }
}
