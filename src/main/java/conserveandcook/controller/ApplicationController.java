package conserveandcook.controller;

import ch.mvcbase.ControllerBase;
import conserveandcook.Database;
import conserveandcook.model.Application;

import java.util.HashMap;
import java.util.Map;

public class ApplicationController extends ControllerBase<Application> {
    /**
     * The Controller needs a Model.
     *
     * @param model Model managed by this Controller
     */

    private static Map<Integer, String> screens = new HashMap<Integer, String>();
    private int currentScreen = 0;

    private int selectedLanguage = 0;
    private static String[] languages = {"de", "fr", "en", "it"};

    public ApplicationController(Application model) {
        super(model);
        screens.put(0, "start");
        screens.put(1, "language");
    }

    public String getCurrentScreen() {
        return screens.get(currentScreen);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    public void nextScreen() {
        this.currentScreen = this.currentScreen + 1 >= screens.size() ? 0 : this.currentScreen + 1;
    }

    public void prevScreen() {
        this.currentScreen = Math.max(this.currentScreen - 1, 0);
    }

    public String getLanguage() {
        return languages[selectedLanguage];
    }

    public void up() {
        switch (this.getCurrentScreen()) {
            case "language":
                selectedLanguage = selectedLanguage - 1 >= 0 ? selectedLanguage - 1 : 0;
        }
    }

    public void down() {
        switch (this.getCurrentScreen()) {
            case "language":
                selectedLanguage = selectedLanguage + 1 < languages.length ? selectedLanguage + 1 : 0;
        }
    }
}
