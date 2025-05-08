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
    public void up(Application model) {}
    public void down(Application model) {}
    public void press(Application model) {
        model.incrementScreen();
    }

    /**
     * Called on any interaction: button press, joystick, barcode scan.
     * Useful for actions that are the same, no matter the interaction.
     * @param model
     * @return True if the interaction's method (press, up, down, scan, etc.) should be called.
     * False if not
     */
    public boolean interaction(Application model) {
        System.out.println("abs.interation");
        return true;
    }

    /**
     * Call this method to reset the state of the view.
     * @param model
     */
    public void init(Application model) {}

    /**
     * Draws a given frame on the full width & height of the window.
     * @param frame Path to the image. Avoid leading /
     */
    protected void drawBackground(String frame) {
        gui.drawImage(frame, 0, 0, SCALE);
    }

}
