package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.misc.Config;
import conserveandcook.model.Application;
import conserveandcook.view.gui.screens.*;

import java.util.Objects;

public class Window extends GuiBase<Application, ApplicationController> {

    public static final int HEIGHT = Integer.parseInt(Config.get("screen.height"));
    public static final int WIDTH = Integer.parseInt(Config.get("screen.width"));

    protected final ApplicationController controller;

    public Window(ApplicationController controller) {
        super(controller, "Conserve & Cook", WIDTH, HEIGHT);
        this.controller = controller;

        Application.screens.put(AvailableScreens.START, new StartScreen(this));
        Application.screens.put(AvailableScreens.LANGUAGE, new LanguageScreen(this));
        Application.screens.put(AvailableScreens.REGION, new RegionScreen(this));
        Application.screens.put(AvailableScreens.GAME, new GameScreen(this));
        Application.screens.put(AvailableScreens.RESULT, new ResultScreen(this));
        Application.screens.put(AvailableScreens.NAME, new NameScreen(this));

        // Fullscreen when running on local doesn't work
        boolean runningOnPi = Objects.equals(Config.get("environment"), "production");
        this.setFullScreen(runningOnPi);
    }

    @Override
    protected void redraw(Application model) {
        model.getActiveScreen().draw(model);
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
