package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.misc.Config;
import conserveandcook.misc.Environments;
import conserveandcook.model.Application;
import conserveandcook.view.gui.screens.*;

public class Window extends GuiBase<Application, ApplicationController> {

    public static int HEIGHT = Integer.parseInt(Config.get("screen.height"));
    public static int WIDTH = Integer.parseInt(Config.get("screen.width"));
    public static int TIMEOUT = Integer.parseInt(Config.get("screen.timeout"));

    private final long startTime = System.currentTimeMillis();
    private long idleTime = 0;

    protected final ApplicationController controller;

    public Window(ApplicationController controller) {
        super(controller, "Conserve & Cook", WIDTH, HEIGHT);
        this.controller = controller;

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
        model.getActiveScreen().draw(model);
        checkIdle(model);
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

    private void checkIdle(Application model) {
        int idleTime = model.getIdleTime();
        if (System.currentTimeMillis() - startTime > 1000) {
            model.setIdleTime(idleTime++);
        }

        if (idleTime > TIMEOUT && model.getCurrentScreen() != AvailableScreens.START) {
            model.timeoutScreen();
        }
    }
}
