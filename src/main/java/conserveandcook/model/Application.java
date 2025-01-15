package conserveandcook.model;

import conserveandcook.view.gui.AvailableScreens;

public class Application {
    private int currentScreen = 0;
    private static final AvailableScreens[] screens = {
            AvailableScreens.START,
            AvailableScreens.LANGUAGE,
            AvailableScreens.REGION,
            AvailableScreens.GAME,
            AvailableScreens.NAME,
            AvailableScreens.RESULT
    };

    private int selectedLanguage = 0;
    private static final String[] languages = {"de", "fr", "it", "en"};

    private final Ingredient[] selectedIngredients = new Ingredient[3];
    private Recipe selectedRecipe;

    private int selectedRegion = 0;
    private static final String[] regions = {"Amerika", "Asien", "Europa"};

    private final int[] name = {0, 0, 0};
    private final char[] nameCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    private int selectedChar = 0;

    public void incrementLanguage() {
        selectedLanguage = selectedLanguage - 1 >= 0 ? selectedLanguage - 1 : languages.length - 1;
    }

    public void decrementLanguage() {
        selectedLanguage = selectedLanguage + 1 < languages.length ? selectedLanguage + 1 : 0;
    }

    public void incrementScreen() {
        this.currentScreen = this.currentScreen + 1 >= screens.length ? 0 : this.currentScreen + 1;
    }

    public void decrementScreen() {
        this.currentScreen = Math.max(this.currentScreen - 1, 0);
    }

    public void nextRegion() {
        this.selectedRegion = this.selectedRegion + 1 >= regions.length ? 0 : selectedRegion + 1;
    }

    public void previousRegion() {
        this.selectedRegion = this.selectedRegion - 1 >= 0 ? this.selectedRegion - 1 : regions.length - 1;
    }

    public AvailableScreens getCurrentScreen() {
        return screens[currentScreen];
    }

    public String getSelectedLanguage() {
        return languages[selectedLanguage];
    }

    public String getSelectedRegion() {
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

    public static String[] getLanguages() {
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

    public char[] getNameCharacters() {
        return nameCharacters;
    }

    public void decrementName() {
        this.name[this.selectedChar] = decrementWrapped(this.name[this.selectedChar], this.nameCharacters.length - 1);
    }

    public void incrementName() {
        this.name[this.selectedChar] = incrementWrapped(this.name[this.selectedChar], this.nameCharacters.length - 1);
    }

    public int incrementWrapped(int current, int max) {
        return current + 1 > max ? 0 : current + 1;
    }

    public int decrementWrapped(int current, int max) {
        return current - 1 >= 0 ? current - 1 : max;
    }
}
