package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.model.Application;
import conserveandcook.view.gui.screens.LanguageScreen;
import conserveandcook.view.gui.screens.RegionScreen;
import conserveandcook.view.gui.screens.StartScreen;

public class Screen extends GuiBase<Application, ApplicationController> {

    public static final int HEIGHT = 1080;
    public static final int WIDTH = 1920;

    protected final ApplicationController controller;
    private final StartScreen startScreen;
    private final LanguageScreen languageScreen;
    private final RegionScreen regionScreen;

    public Screen(ApplicationController controller) {
        super(controller, "Conserve & Cook", WIDTH, HEIGHT);
        this.controller = controller;
        this.startScreen = new StartScreen();
        this.languageScreen = new LanguageScreen();
        this.regionScreen = new RegionScreen();
    }

    @Override
    protected void redraw(Application model) {
        String frame = "";
        String language = model.getSelectedLanguage();
        String region = model.getSelectedRegion();




        frame = switch (model.getCurrentScreen()) {
            case "start" -> startScreen.getCurrentFrame(language);
            case "language" -> languageScreen.getCurrentFrame(language);
            case "region" -> regionScreen.getCurrentFrame(region);
            default -> frame;
        };


        drawImage(frame, 0, 0);
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
