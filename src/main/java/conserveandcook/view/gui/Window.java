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
    private final StartScreen startScreen;
    private final LanguageScreen languageScreen;
    private final RegionScreen regionScreen;
    private final GameScreen gameScreen;
    private final ResultScreen resultScreen;
    private final NameScreen nameScreen;

    public Window(ApplicationController controller) {
        super(controller, "Conserve & Cook", WIDTH, HEIGHT);
        this.controller = controller;
        this.startScreen = new StartScreen(this);
        this.languageScreen = new LanguageScreen(this);
        this.regionScreen = new RegionScreen(this);
        this.gameScreen = new GameScreen(this);
        this.resultScreen = new ResultScreen(this);
        this.nameScreen = new NameScreen(this);

        // Fullscreen when running on local doesn't work
        boolean runningOnPi = Objects.equals(Config.get("environment"), "production");
        this.setFullScreen(runningOnPi);
    }

    @Override
    protected void redraw(Application model) {
        switch (model.getCurrentScreen()) {
            case START -> startScreen.draw(model);
            case LANGUAGE -> languageScreen.draw(model);
            case REGION -> regionScreen.draw(model);
            case GAME -> gameScreen.draw(model);
            case NAME -> nameScreen.draw(model);
            case RESULT -> resultScreen.draw(model);
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
