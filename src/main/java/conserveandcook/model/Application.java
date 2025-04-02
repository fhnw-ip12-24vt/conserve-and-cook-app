package conserveandcook.model;

import conserveandcook.misc.I18n;
import conserveandcook.misc.Languages;
import conserveandcook.misc.Regions;
import conserveandcook.view.gui.AvailableScreens;
import conserveandcook.view.gui.screens.AbstractScreen;

import java.util.HashMap;
import java.util.Map;

public class Application {
    private int currentScreen = 0;
    private static final AvailableScreens[] availableScreens = AvailableScreens.values();

    public static final Map<AvailableScreens, AbstractScreen> screens = new HashMap<>();
    public AbstractScreen activeScreen = screens.get(AvailableScreens.START);

    private int selectedLanguage = 0;
    private static final Languages[] languages = Languages.values();
    private static Languages language;

    private final Ingredient[] selectedIngredients = new Ingredient[3];
    private Recipe selectedRecipe;

    private int selectedRegion = 0;
    private static final Regions[] regions = Regions.values();

    public static final int NAME_LENGTH = 3;
    private final int[] name = new int[NAME_LENGTH];
    private final char[] nameCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    private int selectedChar = 0;

    public AbstractScreen getActiveScreen() {
        if (activeScreen == null) {
            activeScreen = screens.get(AvailableScreens.START);
        }
        return activeScreen;
    }

    public void incrementLanguage() {
        selectedLanguage = decrementWrapped(selectedLanguage, languages.length - 1);
        language = languages[selectedLanguage];
        I18n.setLanguage(language);
    }

    public void decrementLanguage() {
        selectedLanguage = incrementWrapped(selectedLanguage, languages.length - 1);
        language = languages[selectedLanguage];
        I18n.setLanguage(language);
    }

    public Languages getLanguage() {
        return language;
    }

    public void incrementScreen() {
        this.currentScreen = incrementWrapped(this.currentScreen, availableScreens.length - 1);
        if (activeScreen == null) {
            return;
        }
        AvailableScreens next = activeScreen.next();
        if (next == null) {
            return;
        }
        activeScreen = screens.get(next);
    }

    public void decrementScreen() {
        this.currentScreen = Math.max(this.currentScreen - 1, 0);
        if (activeScreen == null) {
            return;
        }
        AvailableScreens prev = activeScreen.prev();
        if (prev == null) {
            return;
        }
        activeScreen = screens.get(prev);
    }

    public AvailableScreens getCurrentScreen() {
        return availableScreens[currentScreen];
    }

    public void nextRegion() {
        this.selectedRegion = incrementWrapped(this.selectedRegion, regions.length - 1);
    }

    public void previousRegion() {
        this.selectedRegion = decrementWrapped(this.selectedRegion, regions.length - 1);
    }


    public Languages getSelectedLanguage() {
        return languages[selectedLanguage];
    }

    public Regions getSelectedRegion() {
        return regions[selectedRegion];
    }

    public void addSelectedIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            return;
        }
        /*
            The position in the selectedIngredient Array is equal to the category_id.
            This works because for each category, you can only select one ingredient.
            By selecting another, you overwrite the first.
         */
        selectedIngredients[ingredient.getCategory()] = ingredient;
    }

    public Ingredient[] getSelectedIngredients() {
        return selectedIngredients;
    }

    public static Languages[] getLanguages() {
        return languages;
    }

    public Recipe getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }

    public int[] getName() {
        return name;
    }

    public int getSelectedChar() {
        return selectedChar;
    }

    public void incrementSelectedChar() {
        selectedChar = incrementWrapped(selectedChar, name.length - 1);
    }

    public void decrementSelectedChar() {
        selectedChar = decrementWrapped(selectedChar, name.length - 1);
    }

    public char[] getNameCharacters() {
        return nameCharacters;
    }

    public void decrementLetter() {
        this.name[this.selectedChar] = decrementWrapped(this.name[this.selectedChar], this.nameCharacters.length - 1);
    }

    public void incrementLetter() {
        this.name[this.selectedChar] = incrementWrapped(this.name[this.selectedChar], this.nameCharacters.length - 1);
    }

    /**
     * This function returns the next int, or wraps around to 0 if the next int exceeds the max
     *
     * @param current int to increment
     * @param max     int that represents the maximum value after which to wrap around
     * @return int result
     */
    public int incrementWrapped(int current, int max) {
        return current + 1 > max ? 0 : current + 1;
    }

    /**
     * This function returns the previous int, or wraps around to the max if the next int is below 0
     *
     * @param current int to decrement
     * @param max     int to which the function wraps in case the previous int is below 0
     * @return int result
     */
    public int decrementWrapped(int current, int max) {
        return current - 1 >= 0 ? current - 1 : max;
    }
}
