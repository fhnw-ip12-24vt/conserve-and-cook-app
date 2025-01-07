package conserveandcook.model;

public class Application {
    private int currentScreen = 0;
    private static final String[] screens = {"start", "language", "region"};

    private int selectedLanguage = 0;
    private static final String[] languages = {"de", "fr", "it", "en"};

    private int selectedRegion = 0;
    private static final String[] regions = {"Amerika","Asien","Europa"};

    public void incrementLanguage() {
        selectedLanguage = selectedLanguage -1 >= 0 ? selectedLanguage - 1 : languages.length - 1;
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
    public void nextRegion(){
        this.selectedRegion = this.selectedRegion + 1 >= regions.length ? 0 : selectedRegion + 1;
    }

    public void previousRegion(){
        this.selectedRegion = this.selectedRegion - 1 >= 0 ? this.selectedRegion - 1 : regions.length - 1;
    }

    public String getCurrentScreen() {
        return screens[currentScreen];
    }

    public String getSelectedLanguage() {
        return languages[selectedLanguage];
    }
    public String getSelectedRegion(){
        return regions[selectedRegion];
    }

}
