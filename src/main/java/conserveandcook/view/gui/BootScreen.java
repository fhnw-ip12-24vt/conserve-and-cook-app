package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import conserveandcook.controller.BootController;
import conserveandcook.misc.Config;
import conserveandcook.model.Boot;

import java.util.List;

public class BootScreen extends GuiBase<Boot, BootController> {

    public static int HEIGHT = Integer.parseInt(Config.get("screen.height"));
    public static int WIDTH = Integer.parseInt(Config.get("screen.width"));
    public static double SCALE = 0.5;

    protected final BootController controller;

    public BootScreen(BootController controller) {
        super(controller, "Conserve & Cook | Boot Screen", (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE));
        this.controller = controller;

        this.setResizable(true);
    }

    @Override
    protected void redraw(Boot model) {
        List<String> logs = model.getLogs();
        for (int i = 0; i < logs.size(); i++) {
            int x = 20;
            int y = 40 + (i * 40);

            this.setFontSize(20);
            this.drawString(logs.get(i), x * SCALE, y * SCALE);
        }

    }

    @Override
    public void initializeComponents(Boot model) {

    }
}
