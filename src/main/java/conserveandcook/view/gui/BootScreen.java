package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import ch.trick17.gui.widget.Button;
import conserveandcook.controller.BootController;
import conserveandcook.model.Boot;

import java.util.List;

public class BootScreen extends GuiBase<Boot, BootController> {

    protected final BootController controller;

    private Button bootAppButton;

    public BootScreen(BootController controller) {
        super(controller, "Conserve & Cook | Boot Screen", 800, 400);
        this.controller = controller;

        this.setResizable(true);
    }

    @Override
    protected void redraw(Boot model) {
        List<String> logs = model.getLogs();
        for (int i = 0; i < logs.size(); i++) {
            int x = 10;
            int y = 20 + (i * 20);

            this.setFontSize(20);
            this.drawString(logs.get(i), x, y);
        }

        this.setFontSize(10);
        bootAppButton.draw(this);
    }

    @Override
    public void initializeComponents(Boot model) {
        bootAppButton = new Button("Boot App", false, 20, 250, 160, 50);
        bootAppButton.setOnClick((Double x, Double y) -> {
            try {
                model.log("Trying to open application");
                model.openApplication();
            } catch (Exception e) {
                model.log(e.getMessage());
            }
        });

        this.addComponents(bootAppButton);
    }
}
