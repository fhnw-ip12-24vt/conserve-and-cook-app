package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.misc.Config;
import conserveandcook.misc.Environments;
import conserveandcook.model.Application;
import conserveandcook.model.Boot;
import conserveandcook.view.gui.screens.*;

public class Window extends GuiBase<Application, ApplicationController> {

    public static int HEIGHT = Integer.parseInt(Config.get("screen.height"));
    public static int WIDTH = Integer.parseInt(Config.get("screen.width"));

    protected final ApplicationController controller;
    private final Boot bootModel;

    public Window(ApplicationController controller, Boot bootModel) {
        super(controller, "Conserve & Cook", WIDTH, HEIGHT);
        this.controller = controller;
        this.bootModel = bootModel;

        // Register screens in the model
        Application.screens.put(AvailableScreens.START, new StartScreen(this));
        Application.screens.put(AvailableScreens.LANGUAGE, new LanguageScreen(this));
        Application.screens.put(AvailableScreens.TUTORIAL, new TutorialScreen(this));
        Application.screens.put(AvailableScreens.REGION, new RegionScreen(this));
        Application.screens.put(AvailableScreens.GAME, new GameScreen(this, controller));
        Application.screens.put(AvailableScreens.GAMEOVER, new GameoverScreen(this));
        Application.screens.put(AvailableScreens.RESULT, new ResultScreen(this));
        Application.screens.put(AvailableScreens.NAME, new NameScreen(this));

        WIDTH = (int) this.getWidth();
        HEIGHT = (int) this.getHeight();
        this.setResizable(true);

        // Fullscreen when running on local doesn't work
        boolean runningOnPi = Environments.get() == Environments.PRODUCTION;
        this.setFullScreen(runningOnPi);
    }

    @Override
    protected void redraw(Application model) {
        try {
            model.getActiveScreen().draw(model);
        } catch (Exception e) {
            bootModel.log(e.getMessage());
        }
    }

    @Override
    public void initializeComponents(Application model) {
    }

    @Override
    public void setupEventHandler(ApplicationController controller) {
        super.setupEventHandler(controller);

        setOnKeyReleased(key -> {
            switch (key) {
                case "right" -> controller.right();
                case "left" -> controller.left();
                case "up" -> controller.up();
                case "down" -> controller.down();
                case "d" -> controller.nextScreen();
                case "a" -> controller.prevScreen();
            }
        });
    }
}
