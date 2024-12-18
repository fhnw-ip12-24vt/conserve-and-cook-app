package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.model.Application;
import conserveandcook.view.gui.screens.GameScreen;
import conserveandcook.view.gui.screens.LanguageScreen;
import conserveandcook.view.gui.screens.ResultScreen;
import conserveandcook.view.gui.screens.StartScreen;

public class Screen extends GuiBase<Application, ApplicationController> {

    public static final int HEIGHT = 540;
    public static final int WIDTH = 960;

    protected final ApplicationController controller;
    private final StartScreen startScreen;
    private final LanguageScreen languageScreen;
    private final GameScreen gameScreen;
    private final ResultScreen resultScreen;

    public Screen(ApplicationController controller) {
        super(controller, "Conserve & Cook", WIDTH, HEIGHT);
        this.controller = controller;
        this.startScreen = new StartScreen(this);
        this.languageScreen = new LanguageScreen(this);
        this.gameScreen = new GameScreen(this);
        this.resultScreen = new ResultScreen(this);
    }

    @Override
    protected void redraw(Application model) {
        String frame = "";
        String language = model.getSelectedLanguage();

        if (model.getCurrentScreen() == "game") {
            gameScreen.draw(model);
            return;
        }

        switch (model.getCurrentScreen()) {
            case "start" -> startScreen.draw(model);
            case "game" -> gameScreen.draw(model);
            case "language" -> languageScreen.draw(model);
            case "result" -> resultScreen.draw(model);
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
                case "right" -> controller.nextScreen();
                case "left" -> controller.prevScreen();
                case "up" -> controller.up();
                case "down" -> controller.down();
            }
        });
    }
}
