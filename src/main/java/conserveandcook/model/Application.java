package conserveandcook.model;

public class Application {
    private int currentScreen = 0;
    private static final String[] screens = {"start", "language", "game"};

    private int selectedLanguage = 0;
    private static final String[] languages = {"de", "fr", "it", "en"};

    private final Ingredient[] selectedIngredients = new Ingredient[3];

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

    public String getCurrentScreen() {
        return screens[currentScreen];
    }

    public String getSelectedLanguage() {
        return languages[selectedLanguage];
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
}
