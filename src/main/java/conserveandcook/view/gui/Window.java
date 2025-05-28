package conserveandcook.view.gui;

import ch.mvcbase.GuiBase;
import conserveandcook.controller.ApplicationController;
import conserveandcook.misc.Config;
import conserveandcook.misc.Environments;
import conserveandcook.model.Application;
import conserveandcook.view.gui.screens.*;

import javax.swing.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.stream.Stream;

public class Window extends GuiBase<Application, ApplicationController> {

    public static int HEIGHT = Integer.parseInt(Config.get("screen.height"));
    public static int WIDTH = Integer.parseInt(Config.get("screen.width"));
    public static int TIMEOUT = Integer.parseInt(Config.get("screen.timeout"));
    private long startTime = System.currentTimeMillis();

    protected final ApplicationController controller;

    public Window(ApplicationController controller) {
        super(controller, "Conserve & Cook", WIDTH, HEIGHT);
        this.controller = controller;

        // Register screens in the model
        Application.screens.put(AvailableScreens.START, new StartScreen(this));
        Application.screens.put(AvailableScreens.LANGUAGE, new LanguageScreen(this));
        Application.screens.put(AvailableScreens.TUTORIAL, new TutorialScreen(this));
        Application.screens.put(AvailableScreens.REGION, new RegionScreen(this));
        Application.screens.put(AvailableScreens.GAME, new GameScreen(this));
        Application.screens.put(AvailableScreens.GAMEOVER, new GameoverScreen(this));
        Application.screens.put(AvailableScreens.RESULT, new ResultScreen(this));
        Application.screens.put(AvailableScreens.NAME, new NameScreen(this));

        WIDTH = (int) this.getWidth();
        HEIGHT = (int) this.getHeight();
        this.setResizable(true);

        // Fullscreen when running on local doesn't work
        boolean runningOnPi = Environments.get() == Environments.PRODUCTION;
        this.setFullScreen(runningOnPi);

        preloadImages();
    }

    @Override
    protected void redraw(Application model) {
        AbstractScreen activeScreen = model.getActiveScreen();
        activeScreen.draw(model);
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
                case "space" -> controller.press();
                case "f4", "escape" -> controller.shutdown();
            }
        });
    }

    private void checkIdle(Application model) {
        // Return early for performance reasons
        if (model.getCurrentScreen() == AvailableScreens.START) return;

        int idleTime = model.getIdleTime();
        if (System.currentTimeMillis() - startTime > 1000) {
            idleTime++;
            model.setIdleTime(idleTime);
            startTime = System.currentTimeMillis();
        }

        if (idleTime > TIMEOUT) model.timeoutScreen();
    }

    private void preloadImages() {
        String path = "src/main/resources/img/";
        String userDirectory = FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString();

        System.out.println("working dir: "+ userDirectory);
        File directory = new File(path);
        System.out.println(directory.getAbsolutePath());

        if(directory.listFiles() == null) return;
        List.of(directory.listFiles())
                .parallelStream()
                .forEach(this::preload);
    }

    private void preload(File file) {
//        System.out.println("preloading " + file.getAbsolutePath());
        if (file.isDirectory()) {
            Stream.of(file.listFiles())
                    .forEach(this::preload);
        } else {
            System.out.println("drawing: " + file.getAbsolutePath());
            SwingUtilities.invokeLater(() -> {
                String path = file.getPath();
                drawImage(path, 0, 0, 0);
            });
        }
    }
}
